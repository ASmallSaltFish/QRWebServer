package com.huateng.qrcode.service.httpserver.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.enums.QrCodeTxnStatusMenu;
import com.huateng.qrcode.common.enums.QrExpiryStatusEnum;
import com.huateng.qrcode.common.model.PaymentQrcode;
import com.huateng.qrcode.common.model.QrcodeTxn;
import com.huateng.qrcode.service.form.PaymentQrcodeService;
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
    private PaymentQrcodeService paymentQrcodeService;

    @Autowired
    private QrcodeTxnService qrcodeTxnService;

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public ResponseVo handler(Map<String, String> paraMap) throws Exception {
        ResponseVo responseVo = new ResponseVo();
        QrcodeTxn qrcodeTxn = new QrcodeTxn();
        //获取到二维码串
        String qrCode = paraMap.get("qrCode");
        //行业应用
        String industryApp = qrCode.substring(14, 17);
        //用途 第18位，确认二维码类型
        String actionScope = qrCode.substring(17, 18);
        //场景
        String scene = qrCode.substring(18, 20);
        //27-33 7位token值
        String token = qrCode.substring(26, 33);
        //校验位
        String checkFlag = qrCode.substring(33);

        //todo 黑名单校验

        if (Constants.PAYMENT_ACTION_ACOPE.equals(actionScope)) {
            EntityWrapper<PaymentQrcode> entityWrapper = new EntityWrapper<>();
            entityWrapper.where("token={0}", token);
            PaymentQrcode paymentQrcode = paymentQrcodeService.selectOne(entityWrapper);
            if (paymentQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new RuntimeException("二维码信息不存在！");
            }

            //token前值
            String beforeToken = paymentQrcode.getBeforeToken();

            //二维码串码
            String qrcodeId = paymentQrcode.getQrcodeId();

            //除校验位外，二维码码串实际明文
            String qrCodeStr = qrcodeId.substring(0, 26) + beforeToken;
            //获取二维码校验位
            String actualCheckFlag = QrUtil.getCheckFlag(StringUtil.toBinaryString(qrCodeStr));
            //完整的二维码明文
            String actualQrCode = qrCodeStr + actualCheckFlag;
            //检验位校验
            if (!actualCheckFlag.equals(checkFlag)) {
                logger.error("校验位校验不通过！");
                throw new RuntimeException("二维码不合法，校验错误！");
            }

            //二维码是否失效
            String expiryStatus = paymentQrcode.getExpiryStatus();
            String expiryDateTime = paymentQrcode.getExpiryDateTime();
            if (StringUtils.isBlank(expiryStatus) || StringUtils.isBlank(expiryDateTime)) {
                logger.error("二维码失效状态或失效时间未设置！");
                throw new RuntimeException("二维码时效校验错误！");
            }

            if (QrExpiryStatusEnum.INVALID.getCode().equals(expiryStatus) || QrUtil.isQrExpire(expiryDateTime)) {
                logger.error("二位码已失效！");
                throw new RuntimeException("二维码已失效！");
            }

            //检验通过，二维码流水入库
            qrcodeTxn.setTxnId(IdWorker.getIdStr());
            qrcodeTxn.setQrcodeId(qrcodeId);
            qrcodeTxn.setApplication(industryApp);
            qrcodeTxn.setPurpose(actionScope);
            qrcodeTxn.setScene(scene);
            qrcodeTxn.setReceiveDate(paraMap.get("receiveDate"));
            qrcodeTxn.setReceiveTime(paraMap.get("receiveTime"));
            qrcodeTxn.setCrtDate(DateUtil.getCurrentDateStr());
            qrcodeTxn.setCrtTime(DateUtil.getCurrentTimeStr());
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
