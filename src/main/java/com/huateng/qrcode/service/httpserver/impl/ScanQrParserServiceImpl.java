package com.huateng.qrcode.service.httpserver.impl;


import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.*;
import com.huateng.qrcode.common.exception.QrParserException;
import com.huateng.qrcode.common.model.DisplayQrcode;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.service.form.BlackListInfoService;
import com.huateng.qrcode.service.form.DisplayQrcodeService;
import com.huateng.qrcode.service.form.QrcodeTxnService;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import com.huateng.qrcode.utils.QrUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ScanQrParserServiceImpl implements ScanQrParserService {

    private static Logger logger = LoggerFactory.getLogger(ScanQrParserServiceImpl.class);

    @Autowired
    private DisplayQrcodeService displayQrcodeService;

    @Autowired
    private QrcodeTxnService qrcodeTxnService;

    @Autowired
    private BlackListInfoService blackListInfoService;

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public ResponseVo handler(Map<String, String> paraMap) throws Exception {
        //http请求客户端app
        String httpReqSys = paraMap.get(Constants.HTTP_REQ_SYS);
        //获取到二维码串
        String qrCode = paraMap.get(Constants.REQ_PARAM_QR_CODE);
        //请求系统
        String reqSys = qrCode.substring(10, 14);
        logger.info("请求系统参数，第11~14位：reqSys=" + reqSys);
        //token（13位）
        String token = qrCode.substring(14, 17) + qrCode.substring(18, 20) + qrCode.substring(26);
        //用途 第18位，确认二维码类型
        String useType = qrCode.substring(17, 18);
        logger.info("用途参数，第18位：useType=" + useType);

        //黑名单校验，校验请求客户端系统
        if (StringUtils.isNotBlank(httpReqSys)) {
            boolean isValidHttpReqSys = blackListInfoService.validInBlackListInfo(httpReqSys, Constants.BLACK_TYPE_SYS);
            if (isValidHttpReqSys) {
                logger.error("当前请求客户端系统已经加入黑名单！");
                throw new QrParserException(ErrorCodeEnum.BLACK_CHECK_ILLEGAL, "当前请求客户端系统已经加入黑名单");
            }
        }

        //黑名单校验，校验请求系统
        boolean isValidSys = blackListInfoService.validInBlackListInfo(reqSys, Constants.BLACK_TYPE_SYS);
        if (isValidSys) {
            logger.error("当前请求系统已经加入黑名单！");
            throw new QrParserException(ErrorCodeEnum.BLACK_CHECK_ILLEGAL, "当前请求系统已经加入黑名单");
        }

        //展示类
        if (UseTypeEnum.USE_TYPE_DISPLAY.getCode().equals(useType)) {
            DisplayQrcode displayQrcode = displayQrcodeService.findByToken(token);
            if (displayQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new QrParserException(ErrorCodeEnum.QRCODE_NOT_FOUNT);
            }

            //二维码生效标识，1-表示一次生效，99-表示按照按照有效期设置生效
            String timesFlag = displayQrcode.getTimesFlag();
            if (StringUtils.isBlank(timesFlag) || QrUseTimesFlagEnum.USE_ONECE.getCode().equals(timesFlag)
                    || QrUseTimesFlagEnum.USE_MORE.getCode().equals(timesFlag)) {
                logger.error("二维码生效标识设置有误！");
                throw new QrParserException(ErrorCodeEnum.QRCODE_STATUS_ILLEGAL, "二维码生效标识未设置或设置值不不合法");
            }

            //二维码串码
            String qrcodeId = displayQrcode.getQrcodeId();
            //二维码模版
            String templateId = displayQrcode.getTempletId();
            //行业应用
            String industryApp = qrcodeId.substring(14, 17);
            logger.info("行业应用参数，第15~17位：industryApp=" + industryApp);
            //场景
            String scene = qrcodeId.substring(18, 20);
            logger.info("场景参数，第19~20位：scene=" + scene);
            //二维码校验位
            String checkFlag = qrcodeId.substring(33);
            logger.info("场景参数，第33位：checkFlag=" + checkFlag);

            //黑名单校验，校验二维码模板是否加入黑名单
            boolean isValidByModule = blackListInfoService.validInBlackListInfo(templateId, Constants.BLACK_TYPE_MODULE);
            if (isValidByModule) {
                logger.error("当前二维码生成模版已经加入黑名单！");
                throw new QrParserException(ErrorCodeEnum.QRCODE_STATUS_ILLEGAL, "当前二维码生成模版已经加入黑名单");
            }

            //黑名单校验，校验二维码是是否加入黑名单
            boolean isValidByQrCode = blackListInfoService.validInBlackListInfo(qrcodeId, Constants.BLACK_TYPE_QRCODE);
            if (isValidByQrCode) {
                logger.error("当前二维码已经加入黑名单！");
                throw new QrParserException(ErrorCodeEnum.BLACK_CHECK_ILLEGAL, "当前二维码已经加入黑名单");
            }

            //二维码是否失效
            String expiryStatus = displayQrcode.getExpiryStatus();
            String expiryDateTime = displayQrcode.getExpiryDateTime();
            if (StringUtils.isBlank(expiryStatus) || StringUtils.isBlank(expiryDateTime)) {
                logger.error("二维码失效状态或失效时间未设置！");
                throw new QrParserException(ErrorCodeEnum.EXPIRY_CHECK_ILLEGAL, "二维码时效校验错误");
            }

            if (QrExpiryStatusEnum.INVALID.getCode().equals(expiryStatus) || QrUtil.isQrExpire(expiryDateTime)) {
                logger.error("二位码已失效！");
                throw new QrParserException(ErrorCodeEnum.EXPIRY_CHECK_ILLEGAL, "二维码已失效");
            }

            //检验通过，二维码流水入库
            String receiveDate = paraMap.get(Constants.RECEIVE_DATE);
            String receiveTime = paraMap.get(Constants.RECEIVE_TIME);
            QrcodeTxn qrcodeTxn = QrUtil.getQrcodeTxn(qrcodeId, industryApp, useType, scene, receiveDate,
                    receiveTime, "二维码解析成功");
            logger.info("开始插入二维码流水表记录");
            qrcodeTxnService.insert(qrcodeTxn);
            logger.info("二维码流水表记录插入成功");

            //判断二维码使用次数，如果只能使用一次，则解析完成后，需要置为失效
            if (QrUseTimesFlagEnum.USE_ONECE.getCode().equals(timesFlag)) {
                logger.info("开始更新二位失效状态");
                displayQrcode.setExpiryStatus(ExpiryStatusEnum.INVALID.getCode());
                displayQrcodeService.update(displayQrcode);
                logger.info("二维码失效状态更新成功");
            }
        } else {
            logger.error("无法识别的用途场景二维码！");
            throw new QrParserException(ErrorCodeEnum.QR_PARSER_FAIL, "无法识别的用途场景二维码");
        }

        //返回响应报文对象
        return QrUtil.wrapResponseVo(ErrorCodeEnum.SUCCESS.getCode(),
                ErrorCodeEnum.SUCCESS.name(), ErrorCodeEnum.SUCCESS.getDesc());
    }
}
