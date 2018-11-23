package com.huateng.qrcode.qrserver.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.enums.QrExpiryStatusEnum;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.utils.QrUtil;
import com.huateng.qrcode.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统类二维码解析处理类
 */
@Component("sysQrParserManagerImpl")
public class SysQrParserManagerImpl implements QrServerManager {

    private static Logger logger = LoggerFactory.getLogger(SysQrParserManagerImpl.class);

    @Autowired
    private IdentityQrcodeService identityQrcodeService;

    @Override
    public ResponseVo handler(RequestVo requestVo) throws Exception {
        ResponseVo responseVo = new ResponseVo();
        BusParamBody busBody = requestVo.getBusBody();
        if (busBody == null) {
            logger.error("请求报文业务体对象为空！");
            throw new RuntimeException("报文参数校验错误！");
        }

        String qrCode = busBody.getQrCode();
        if (StringUtils.isBlank(qrCode)) {
            logger.error("请求报文中qrCode参数为空！");
            throw new RuntimeException("报文参数校验错误！");
        }

        //第18位，确认二维码类型
        String actionScope = qrCode.substring(17, 18);
        //27-33 7位token值
        String token = qrCode.substring(26, 33);
        //校验位
        String checkFlag = qrCode.substring(33);

        //todo 可以根据配置，更好的实现根据类别域确定类别域方式
        if ("1".equals(actionScope)) {
            EntityWrapper<IdentityQrcode> entityWrapper = new EntityWrapper<>();
            entityWrapper.where("token={0}", token);
            IdentityQrcode identityQrcode = identityQrcodeService.selectOne(entityWrapper);
            if (identityQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new RuntimeException("二维码信息不存在！");
            }

            //token前值
            String beforeToken = identityQrcode.getBeforeToken();
            //除校验位外，二维码码串实际明文
            String qrCodeFromDB = identityQrcode.getQrcodeId().substring(0, 26) + beforeToken;
            //获取二维码校验位
            String actualCheckFlag = QrUtil.getCheckFlag(StringUtil.toBinaryString(qrCodeFromDB));

            //检验位校验
            if (!actualCheckFlag.equals(checkFlag)) {
                logger.error("校验位校验不通过！");
                throw new RuntimeException("二维码不合法，校验错误！");
            }

            //todo 黑名单校验

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

            BusRespBody busRespBody = new BusRespBody();
            busRespBody.setProcessCode(ErrorCodeEnum.SUCCESS.getCode());
            busRespBody.setProcessStatus(ErrorCodeEnum.SUCCESS.name());
            busRespBody.setMsg(ErrorCodeEnum.SUCCESS.getDesc());
            responseVo.setBusRespBody(busRespBody);
        }

        return responseVo;
    }
}
