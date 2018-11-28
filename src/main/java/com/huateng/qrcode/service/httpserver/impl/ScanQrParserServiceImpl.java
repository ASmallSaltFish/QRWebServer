package com.huateng.qrcode.service.httpserver.impl;


import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.enums.QrCodeTxnStatusMenu;
import com.huateng.qrcode.common.enums.QrExpiryStatusEnum;
import com.huateng.qrcode.common.enums.UseTypeEnum;
import com.huateng.qrcode.common.model.DisplayQrcode;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.service.form.BlackListInfoService;
import com.huateng.qrcode.service.form.DisplayQrcodeService;
import com.huateng.qrcode.service.form.QrcodeTxnService;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import com.huateng.qrcode.utils.DateUtil;
import com.huateng.qrcode.utils.QrUtil;
import com.huateng.qrcode.utils.StringUtil;
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
        ResponseVo responseVo = new ResponseVo();
        //http请求客户端app
        String httpReqSys = paraMap.get(Constants.HTTP_REQ_SYS);
        //获取到二维码串
        String qrCode = paraMap.get(Constants.REQ_PARAM_QR_CODE);
        //请求系统
        String reqSys = qrCode.substring(10, 14);
        logger.debug("请求系统参数，第11~14位：reqSys=" + reqSys);
        //token（13位）
        String token = qrCode.substring(14, 17) + qrCode.substring(18, 20) + qrCode.substring(26);
        //用途 第18位，确认二维码类型
        String actionScope = qrCode.substring(17, 18);
        logger.debug("用途参数，第18位：actionScope=" + actionScope);

        //黑名单校验，校验请求客户端系统
        if (StringUtils.isNotBlank(httpReqSys)) {
            boolean isValidHttpReqSys = blackListInfoService.validInBlackListInfo(httpReqSys, Constants.BLACK_TYPE_SYS);
            if (isValidHttpReqSys) {
                logger.error("当前请求客户端系统已经加入黑名单！");
                throw new RuntimeException("当前请求客户端系统已经加入黑名单！");
            }
        }

        //黑名单校验，校验请求系统
        boolean isValidSys = blackListInfoService.validInBlackListInfo(reqSys, Constants.BLACK_TYPE_SYS);
        if (isValidSys) {
            logger.error("当前请求系统已经加入黑名单！");
            throw new RuntimeException("当前请求系统已经加入黑名单！");
        }

        if (UseTypeEnum.USE_TYPE_IDENTITY.getCode().equals(actionScope)) {
            DisplayQrcode displayQrcode = displayQrcodeService.findByToken(token);
            if (displayQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new RuntimeException("二维码信息不存在！");
            }

            //二维码串码
            String qrcodeId = displayQrcode.getQrcodeId();
            //二维码模版
            String templateId = displayQrcode.getTempletId();
            //行业应用
            String industryApp = qrcodeId.substring(14, 17);
            logger.debug("行业应用参数，第15~17位：industryApp=" + industryApp);
            //场景
            String scene = qrcodeId.substring(18, 20);
            logger.debug("场景参数，第19~20位：scene=" + scene);
            //二维码校验位
            String checkFlag = qrcodeId.substring(33);
            logger.debug("场景参数，第33位：checkFlag=" + checkFlag);

            //黑名单校验，校验二维码模板是否加入黑名单
            boolean isValidByModule = blackListInfoService.validInBlackListInfo(templateId, Constants.BLACK_TYPE_MODULE);
            if (isValidByModule) {
                logger.error("当前二维码生成模版已经加入黑名单！");
                throw new RuntimeException("当前二维码生成模版已经加入黑名单！");
            }

            //黑名单校验，校验二维码是是否加入黑名单
            boolean isValidByQrCode = blackListInfoService.validInBlackListInfo(qrcodeId, Constants.BLACK_TYPE_QRCODE);
            if (isValidByQrCode) {
                logger.error("当前二维码已经加入黑名单！");
                throw new RuntimeException("当前二维码已经加入黑名单！");
            }

            //二维码是否失效
            String expiryStatus = displayQrcode.getExpiryStatus();
            String expiryDateTime = displayQrcode.getExpiryDateTime();
            if (StringUtils.isBlank(expiryStatus) || StringUtils.isBlank(expiryDateTime)) {
                logger.error("二维码失效状态或失效时间未设置！");
                throw new RuntimeException("二维码时效校验错误！");
            }

            if (QrExpiryStatusEnum.INVALID.getCode().equals(expiryStatus) || QrUtil.isQrExpire(expiryDateTime)) {
                logger.error("二位码已失效！");
                throw new RuntimeException("二维码已失效！");
            }

            //检验通过，二维码流水入库
            QrcodeTxn qrcodeTxn = new QrcodeTxn();
            qrcodeTxn.setTxnId(IdWorker.getIdStr());
            qrcodeTxn.setQrcodeId(qrcodeId);
            qrcodeTxn.setApplication(industryApp);
            qrcodeTxn.setPurpose(actionScope);
            qrcodeTxn.setScene(scene);
            qrcodeTxn.setReceiveDate(paraMap.get(Constants.RECEIVE_DATE));
            qrcodeTxn.setReceiveTime(paraMap.get(Constants.RECEIVE_TIME));
            qrcodeTxn.setCrtDate(DateUtil.formatCurrentDate());
            qrcodeTxn.setCrtTime(DateUtil.formatCurrentTime());
            qrcodeTxn.setRemark("二维码解析成功！");
            qrcodeTxn.setStatus(QrCodeTxnStatusMenu.VALID.getCode());
            logger.debug("开始插入二维码流水表记录");
            qrcodeTxnService.insert(qrcodeTxn);
            logger.debug("二维码流水表记录插入成功");
        } else {
            logger.error("无法识别的用途场景二维码！");
            throw new RuntimeException("无法识别的用途场景二维码！");
        }

        //返回响应报文对象
        BusRespBody busRespBody = new BusRespBody();
        busRespBody.setProcessCode(ErrorCodeEnum.SUCCESS.getCode());
        busRespBody.setProcessStatus(ErrorCodeEnum.SUCCESS.name());
        busRespBody.setMsg(ErrorCodeEnum.SUCCESS.getDesc());
        responseVo.setBusRespBody(busRespBody);
        return responseVo;
    }
}
