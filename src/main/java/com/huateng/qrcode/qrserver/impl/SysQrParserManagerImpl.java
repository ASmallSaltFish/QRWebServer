package com.huateng.qrcode.qrserver.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.*;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.form.BlackListInfoService;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.service.form.QrcodeTxnService;
import com.huateng.qrcode.utils.DateUtil;
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
            logger.error("请求报文业务体对象为空！");
            throw new RuntimeException("报文参数校验错误！");
        }

        //请求报文业务体中的map集合
        Map<String, String> paramMap = busBody.getParamMap();
        if (MapUtils.isEmpty(paramMap)) {
            logger.error("请求报文业务体paramMap集合为空！");
            throw new RuntimeException("报文参数校验错误！");
        }

        String qrCode = busBody.getQrCode();
        if (StringUtils.isBlank(qrCode)) {
            logger.error("请求报文中qrCode参数为空！");
            throw new RuntimeException("报文参数校验错误！");
        }

        //请求系统
        String reqSys = qrCode.substring(10, 14);
        logger.debug("请求系统参数，第11~14位：reqSys=" + reqSys);
        //token（13位）
        String token = qrCode.substring(14, 17) + qrCode.substring(18, 20) + qrCode.substring(26);
        //用途 第18位，确认二维码类型
        String actionScope = qrCode.substring(17, 18);
        logger.debug("用途参数，第18位：actionScope=" + actionScope);

        //黑名单校验，校验请求系统
        boolean isValidSys = blackListInfoService.validInBlackListInfo(reqSys, Constants.BLACK_TYPE_SYS);
        if (isValidSys) {
            logger.error("当前请求系统已经加入黑名单！");
            throw new RuntimeException("当前请求系统已经加入黑名单！");
        }

        if (UseTypeEnum.USE_TYPE_IDENTITY.getCode().equals(actionScope)) {
            IdentityQrcode identityQrcode = identityQrcodeService.findByToken(token);
            if (identityQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new RuntimeException("二维码信息不存在！");
            }

            //二维码串码
            String qrcodeId = identityQrcode.getQrcodeId();
            //二维码模版
            String templateId = identityQrcode.getTempletId();
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
            String expiryStatus = identityQrcode.getExpiryStatus();
            String expiryDateTime = identityQrcode.getExpiryDateTime();
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
            qrcodeTxn.setReceiveDate(paramMap.get("receiveDate"));
            qrcodeTxn.setReceiveTime(paramMap.get("receiveTime"));
            qrcodeTxn.setCrtDate(DateUtil.formatCurrentDate());
            qrcodeTxn.setCrtTime(DateUtil.formatCurrentTime());
            qrcodeTxn.setRemark("二维码解析成功！");
            qrcodeTxn.setStatus(QrCodeTxnStatusMenu.VALID.getCode());
            logger.debug("开始插入二维码流水表记录");
            qrcodeTxnService.insert(qrcodeTxn);
            logger.debug("二维码流水表记录插入成功");
        }

        BusRespBody busRespBody = new BusRespBody();
        busRespBody.setProcessCode(ErrorCodeEnum.SUCCESS.getCode());
        busRespBody.setProcessStatus(ErrorCodeEnum.SUCCESS.name());
        busRespBody.setMsg(ErrorCodeEnum.SUCCESS.getDesc());
        responseVo.setBusRespBody(busRespBody);
        return responseVo;
    }
}
