package com.huateng.test.mybatis;

import com.huateng.qrcode.model.entity.User;
import com.huateng.qrcode.service.UserService;
import com.huateng.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 测试mybatis基础提供dao数据接口功能（xml中写sql方式）
 *
 * @author qinyupeng
 * @since 2018-11-14 10:55:22
 */
public class TestMyBatis extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserByUserId() {
        User user = userService.findUserByUserId("1111");
        System.out.println("====>>>" + user);
    }
}
