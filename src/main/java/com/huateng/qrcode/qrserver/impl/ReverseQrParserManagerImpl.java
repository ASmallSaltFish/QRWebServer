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
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 二维码冲正
 *
 * @author qinyupeng
 * @since 2018-11-29 09:46:58
 */
public class ReverseQrParserManagerImpl implements QrServerManager {

    private static Logger logger = LoggerFactory.getLogger(ReverseQrParserManagerImpl.class);

    @Autowired
    private BlackListInfoService blackListInfoService;

    @Autowired
    private IdentityQrcodeService identityQrcodeService;

    @Autowired
    private QrcodeTxnService qrcodeTxnService;

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

        //获取冲正流水号
        String qrTxnId = paramMap.get(Constants.QR_TXN_ID);
        if (StringUtils.isBlank(qrTxnId)) {
            logger.error("冲正流水号qrTxnId参数为空！");
            throw new RuntimeException("报文参数校验错误！");
        }

        //根据冲正流水号，获取冲正流水
        QrcodeTxn qrcodeTxn = qrcodeTxnService.selectById(qrTxnId);
        if (qrcodeTxn == null) {
            logger.error("冲正二维码流水不存在！");
            throw new RuntimeException("冲正二维码流水不存在！");
        }

        //校验当前冲正二维码流水状态
        String txnStatus = qrcodeTxn.getStatus();
        if (StringUtils.isBlank(txnStatus) || QrCodeTxnStatusMenu.INVALID.getCode().equals(txnStatus)) {
            logger.error("冲正二维码流水状态错误！");
            throw new RuntimeException("冲正二维码流水状态错误");
        }

        if (QrCodeTxnStatusMenu.REVERSED.getCode().equals(txnStatus)) {
            logger.error("当前二维码流水状态为已冲正！");
            throw new RuntimeException("当前二维码流水状态为已冲正！");
        }

        //获取二维码
        String qrcodeId = qrcodeTxn.getQrcodeId();
        if (StringUtils.isBlank(qrcodeId)) {
            logger.error("根据冲正二维码流水记录中qrcodeId为空！");
            throw new RuntimeException("冲正二维码流水记录数据异常！");
        }

        //用途
        String useType = qrcodeId.substring(17, 18);
        //识别类
        if (UseTypeEnum.USE_TYPE_IDENTITY.getCode().equals(useType)) {
            IdentityQrcode identityQrcode = identityQrcodeService.selectById(qrcodeId);
            if (identityQrcode == null) {
                logger.error("当前冲正二维码流水对应的二维码记录不存在！");
                throw new RuntimeException("冲正二维码流水关联二维码记录不存在！");
            }

            //判断二维码是否失效，并将二维码设置为生效，更新即将冲正流水状态，新增冲正流水记录
            String timesFlag = identityQrcode.getTimesFlag();
            String expiryStatus = identityQrcode.getExpiryStatus();
            if (QrUseTimesFlagEnum.USE_ONECE.getCode().equals(timesFlag)
                    && QrExpiryStatusEnum.INVALID.getCode().equals(expiryStatus)) {

                logger.info("冲正操作，开始更新二维码失效状态");
                identityQrcode.setExpiryStatus(QrExpiryStatusEnum.INVALID.getCode());
                identityQrcodeService.update(identityQrcode);
                logger.info("冲正操作，更新二维码状态成功");

                logger.info("冲正操作，更开始新冲正二维码流水记录状态");
                qrcodeTxn.setStatus(QrCodeTxnStatusMenu.REVERSED.getCode());
                qrcodeTxnService.update(qrcodeTxn);
                logger.info("冲正操作，更新冲正二维码流水记录状态成功");

                logger.info("冲正操作，开始新增冲正二维码流水记录成功");
                QrcodeTxn qrCodeTxnNew = qrcodeTxn;
                qrCodeTxnNew.setTxnId(IdWorker.getIdStr());
                qrcodeTxnService.insert(qrCodeTxnNew);
                logger.info("冲正操作，新增冲正二维码流水记录成功");
            } else {
                logger.error("不满足冲正条件或无需冲正！");
                throw new RuntimeException("不满足冲正条件或无需冲正！");
            }
        }

        BusRespBody busRespBody = new BusRespBody();
        busRespBody.setProcessCode(ErrorCodeEnum.SUCCESS.getCode());
        busRespBody.setProcessStatus(ErrorCodeEnum.SUCCESS.name());
        busRespBody.setMsg(ErrorCodeEnum.SUCCESS.getDesc());
        responseVo.setBusRespBody(busRespBody);
        return responseVo;
    }

    //插入二维码流水记录
    private void insertQrCodeTxnInfo(Map<String, String> paramMap, String actionScope, String qrcodeId, String industryApp, String scene) {
        logger.info("开始插入二维码流水表记录");
        QrcodeTxn qrcodeTxn = new QrcodeTxn();
        qrcodeTxn.setTxnId(IdWorker.getIdStr());
        qrcodeTxn.setQrcodeId(qrcodeId);
        qrcodeTxn.setApplication(industryApp);
        qrcodeTxn.setPurpose(actionScope);
        qrcodeTxn.setScene(scene);
        qrcodeTxn.setReceiveDate(paramMap.get(Constants.RECEIVE_DATE));
        qrcodeTxn.setReceiveTime(paramMap.get(Constants.RECEIVE_TIME));
        qrcodeTxn.setCrtDate(DateUtil.formatCurrentDate());
        qrcodeTxn.setCrtTime(DateUtil.formatCurrentTime());
        qrcodeTxn.setRemark("二维码解析成功！");
        qrcodeTxn.setStatus(QrCodeTxnStatusMenu.VALID.getCode());
        qrcodeTxnService.insert(qrcodeTxn);
        logger.info("二维码流水表记录插入成功");
    }
}
