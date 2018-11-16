package com.huateng.qrcode.qrserver.manager;

import com.huateng.qrcode.model.entity.User;
import com.huateng.qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QrGenerateManager implements QrServerManager {

    @Autowired
    private UserService userService;

    //todo 实际处理业务操作的方法
    @Override
    public void handler(String serviceCode) {
        System.out.println("===>>>生成二维码服务。。");
        User user = userService.findUserByUserId("1111");
        System.out.println(user);
        System.out.println("===>>>生成二维码服务完成。。");
    }
}
