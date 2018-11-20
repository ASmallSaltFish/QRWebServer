package com.huateng.qrcode.qrserver.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "RegisterQrGenerateImpl")
public class RegisterQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(RegisterQrGenerateImpl.class);

    //todo 实际处理业务操作的方法
    @Override
    public String handler(RequestVo requestVo) {
        return null;
    }
}
