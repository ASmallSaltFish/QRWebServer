package com.huateng.qrcode.service.httpserver.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.enums.QrExpiryStatusEnum;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import com.huateng.qrcode.utils.DateUtil;
import com.huateng.qrcode.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class ScanQrParserServiceImpl implements ScanQrParserService {

    private static Logger logger = LoggerFactory.getLogger(ScanQrParserServiceImpl.class);

    @Resource
    private IdentityQrcodeService identityQrcodeService;

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public ResponseVo handler(Map<String, String> paraMap) throws Exception {
        ResponseVo responseVo = new ResponseVo();
        //获取到二维码串
        String qrCode = paraMap.get("qrCode");
        //第18位，确认二维码类型
        String actionScope = qrCode.substring(17, 18);
        //27-33 7位token值
        String token = qrCode.substring(26, 33);
        //校验位
        String flag = qrCode.substring(33);
        //todo 校验位算法，确定校验位
        if (!"1".equals(flag)) {
            logger.error("校验位校验不通过！");
            throw new RuntimeException("二维码不合法，校验错误！");
        }

        //todo 黑名单校验

        //todo 可以根据配置，更好的实现根据类别域确定类别域方式
        if ("1".equals(actionScope)) {
            EntityWrapper<IdentityQrcode> entityWrapper = new EntityWrapper<>();
            entityWrapper.where("token={0}", token);
            IdentityQrcode identityQrcode = identityQrcodeService.selectOne(entityWrapper);
            if (identityQrcode == null) {
                logger.error("根据token查询，没有获取到二维码信息！");
                throw new RuntimeException("二维码信息不存在！");
            }

            //二维码是否失效
            String expiryStatus = identityQrcode.getExpiryStatus();
            String expiryDateTime = identityQrcode.getExpiryDateTime();
            if (StringUtils.isBlank(expiryStatus) || StringUtils.isBlank(expiryDateTime)) {
                logger.error("二维码失效状态或失效时间未设置！");
                throw new RuntimeException("二维码时效校验错误！");
            }

            if (QrExpiryStatusEnum.INVALID.getCode().equals(expiryStatus) || isQrExpire(expiryDateTime)) {
                logger.error("二位码已失效！");
                throw new RuntimeException("二维码已失效！");
            }

            BusRespBody busRespBody = responseVo.getBusRespBody();
            busRespBody.setProcessCode(ErrorCodeEnum.SUCCESS.getCode());
            busRespBody.setProcessStatus(ErrorCodeEnum.SUCCESS.name());
            busRespBody.setMsg(ErrorCodeEnum.SUCCESS.getDesc());
        }

        return responseVo;
    }

    //判断二维码是否失效
    private boolean isQrExpire(String expiryDateTime) {
        Date expireDate = DateUtil.parserToDate(expiryDateTime);
        return new Date().after(expireDate);
    }
}
