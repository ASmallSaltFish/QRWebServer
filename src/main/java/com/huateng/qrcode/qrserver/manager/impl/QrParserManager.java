package com.huateng.qrcode.qrserver.manager.impl;

import com.huateng.qrcode.common.model.User;
import com.huateng.qrcode.qrserver.manager.QrServerManager;
import com.huateng.qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QrParserManager implements QrServerManager {

    @Autowired
    private UserService userService;

    //todo 实际处理业务操作的方法
    @Override
    public void handler(String serviceCode) {
        System.out.println("===>>>解析二维码服务。。");
        User user = userService.findUserByUserId("1111");
        System.out.println(user);
        System.out.println("===>>>解析二维码服务完成。。");
    }
}
