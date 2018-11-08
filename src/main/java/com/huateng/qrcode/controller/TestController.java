package com.huateng.qrcode.controller;

import com.huateng.qrcode.entity.User;
import com.huateng.qrcode.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController {

    @Resource
    private UserService userService;


    @RequestMapping(value = {"/", "/index"})
    public String test(String userId, Model model) {
        User user = userService.findUserByUserId(userId);
        System.out.println("user=" + user);
        model.addAttribute("user", user);
        return "index";
    }


    @RequestMapping(value = "/testUser")
    @ResponseBody
    public User testUser() {
        return userService.findUserByUserId("1111");
    }
}
