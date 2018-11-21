package com.huateng.qrcode.qrserver.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @class: RegisterQrGenerateImpl
 * @title: 注册类二维码生成服务
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "RegisterQrGenerateImpl")
public class RegisterQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(RegisterQrGenerateImpl.class);

    //todo 实际处理业务操作的方法
    @Override
    public ResponseVo handler(RequestVo requestVo) {
        return null;
    }
}
