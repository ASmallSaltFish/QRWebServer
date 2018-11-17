package com.huateng.qrcode.controller;

import com.huateng.qrcode.controller.base.BaseController;
import com.huateng.qrcode.model.entity.User;
import com.huateng.qrcode.qrserver.manager.ServiceConfigMapping;
import com.huateng.qrcode.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 测试控制器类
 *
 * @author qinyupeng
 * @since 2018-11-14 14:56:37
 */
@Controller
public class TestController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 测试链接：http://localhost:8085/qrcode/index.do?userId=1111
     *
     * @param userId 参数userId
     * @param model  数据模型对象，返回数据到前端
     */
    @RequestMapping(value = {"/", "/index"})
    public String test(String userId, Model model) {
        if (StringUtils.isBlank(userId)) {
            userId = "1111";
        }

        User user = userService.findUserByUserId(userId);
        System.out.println("user=" + user);
        model.addAttribute("user", user);
        return "index";
    }


    @RequestMapping(value = "/testUser")
    @ResponseBody
    public User testUser() {
        return userService.selectById("1111");
    }
}
