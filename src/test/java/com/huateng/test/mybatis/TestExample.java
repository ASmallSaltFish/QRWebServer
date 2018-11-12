package com.huateng.test.mybatis;


import com.huateng.qrcode.dao.UserDao;
import com.huateng.qrcode.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试类：测试mybatis接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestExample {

    @Autowired
    private UserDao userDao;


    @Test
    public void testFindByExample() {
        User user = new User();
        user.setUserId("1111");
        userDao.findByUser(user);
    }
}
