package com.huateng.qrcode.qrserver.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.*;
import com.huateng.qrcode.common.exception.QrParserException;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.form.BlackListInfoService;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.service.form.QrcodeTxnService;
import com.huateng.qrcode.utils.QrUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 系统类二维码解析处理类
 */
@Component("sysQrParserManagerImpl")
public class SysQrParserManagerImpl implements QrServerManager {

    private static Logger logger = LoggerFactory.getLogger(SysQrParserManagerImpl.class);

    @Autowired
    private IdentityQrcodeService identityQrcodeService;

    @Autowired
    private QrcodeTxnService qrcodeTxnService;

    @Autowired
    private BlackListInfoService blackListInfoService;

    @Override
    public ResponseVo handler(RequestVo requestVo) throws Exception {
        ResponseVo responseVo = new ResponseVo();
        BusParamBody busBody = requestVo.getBusBody();
        if (busBody == null) {
            logger.error("请求报文busBody对象为空！");
            throw new QrParserException(ErrorCodeEnum.ILLEGAL_PARAM, "请求报文busBody对象为空");
        }

        //请求报文业务体中的map集合
        Map<String, String> paramMap = busBody.getParamMap();
        if (MapUtils.isEmpty(paramMap)) {
            logger.error("请求报文业务体paramMap集合为空！");
            throw new QrParserException(ErrorCodeEnum.ILLEGAL_PARAM, "请求报文业务体paramMap集合为空");
        }

        String qrCode = busBody.getQrCode();
        if (StringUtils.isBlank(qrCode)) {
            logger.error("请求报文中qrCode参数为空！");
            throw new QrParserException(ErrorCodeEnum.ILLEGAL_PARAM, "请求报文中qrCode参数为空");
        }

        //请求系统
        String reqSys = qrCode.substring(10, 14);
        logger.info("请求系统参数，第11~14位：reqSys=" + reqSys);
        //token（13位）
        String token = qrCode.substring(14, 17) + qrCode.substring(18, 20) + qrCode.substring(26);
        //用途 第18位，确认二维码类型
        String useType = qrCode.substring(17, 18);
        logger.info("用途参数，第18位：useType=" + useType);

        //黑名单校验，校验请求系统
        boolean isValidSys = blackListInfoService.validInBlackListInfo(reqSys, Constants.BLACK_TYPE_SYS);
        if (isValidSys) {
            logger.error("当前请求系统已经加入黑名单！");
            throw new RuntimeException("当前请求系统已经加入黑名单！");
        }

        if (UseTypeEnum.USE_TYPE_IDENTITY.getCode().equals(useType)) {
            IdentityQrcode identityQrcode = identityQrcodeService.findByToken(token);
            if (identityQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new QrParserException(ErrorCodeEnum.QRCODE_NOT_FOUNT);
            }

            //二维码生效标识，1-表示一次生效，99-表示按照按照有效期设置生效
            String timesFlag = identityQrcode.getTimesFlag();
            if (StringUtils.isBlank(timesFlag) || QrUseTimesFlagEnum.USE_ONECE.getCode().equals(timesFlag)
                    || QrUseTimesFlagEnum.USE_MORE.getCode().equals(timesFlag)) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new QrParserException(ErrorCodeEnum.QRCODE_STATUS_ILLEGAL, "二维码生效标识未设置或设置值不不合法");
            }

            //二维码串码
            String qrcodeId = identityQrcode.getQrcodeId();
            //二维码模版
            String templateId = identityQrcode.getTempletId();
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
                throw new QrParserException(ErrorCodeEnum.BLACK_CHECK_ILLEGAL, "当前二维码生成模版已经加入黑名单");
            }

            //黑名单校验，校验二维码是是否加入黑名单
            boolean isValidByQrCode = blackListInfoService.validInBlackListInfo(qrcodeId, Constants.BLACK_TYPE_QRCODE);
            if (isValidByQrCode) {
                logger.error("当前二维码已经加入黑名单！");
                throw new QrParserException(ErrorCodeEnum.BLACK_CHECK_ILLEGAL, "当前二维码已经加入黑名单");
            }

            //二维码是否失效
            String expiryStatus = identityQrcode.getExpiryStatus();
            String expiryDateTime = identityQrcode.getExpiryDateTime();
            if (StringUtils.isBlank(expiryStatus) || StringUtils.isBlank(expiryDateTime)) {
                logger.error("二维码失效状态或失效时间未设置！");
                throw new QrParserException(ErrorCodeEnum.EXPIRY_CHECK_ILLEGAL, "二维码时效状态有误或失效时间未设置");
            }

            if (QrExpiryStatusEnum.INVALID.getCode().equals(expiryStatus) || QrUtil.isQrExpire(expiryDateTime)) {
                logger.error("二位码已失效！");
                throw new QrParserException(ErrorCodeEnum.EXPIRY_CHECK_ILLEGAL, "二维码已失效");
            }

            //检验通过，二维码流水入库
            String receiveDate = paramMap.get(Constants.RECEIVE_DATE);
            String receiveTime = paramMap.get(Constants.RECEIVE_TIME);
            QrcodeTxn qrcodeTxn = QrUtil.getQrcodeTxn(qrcodeId, industryApp, useType, scene, receiveDate,
                    receiveTime, "二维码解析成功");
            logger.info("开始插入二维码流水表记录");
            qrcodeTxnService.insert(qrcodeTxn);
            logger.info("二维码流水表记录插入成功");

            //判断二维码使用次数，如果只能使用一次，则解析完成后，需要置为失效
            if (QrUseTimesFlagEnum.USE_ONECE.getCode().equals(timesFlag)) {
                logger.info("开始更新二位失效状态,将当前二维码设置为失效");
                identityQrcode.setExpiryStatus(ExpiryStatusEnum.INVALID.getCode());
                identityQrcodeService.update(identityQrcode);
                logger.info("二维码失效状态更新成功");
            }
        }

        return QrUtil.wrapResponseVo(ErrorCodeEnum.SUCCESS.getCode(),
                ErrorCodeEnum.SUCCESS.name(), ErrorCodeEnum.SUCCESS.getDesc());
    }


}
