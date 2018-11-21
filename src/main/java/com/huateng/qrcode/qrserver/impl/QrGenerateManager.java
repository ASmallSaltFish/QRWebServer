package com.huateng.qrcode.qrserver.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.model.User;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "qrGenerateManager")
public class QrGenerateManager implements QrServerManager {

    @Autowired
    private UserService userService;

    //todo 实际处理业务操作的方法
    @Override
    public ResponseVo handler(RequestVo requestVo) {
        System.out.println("===>>>生成二维码服务。。");
        User user = userService.findUserByUserId("1111");
        System.out.println(user);
        System.out.println("===>>>生成二维码服务完成。。");
        return null;
    }
}
