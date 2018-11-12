package com.huateng.test.mybatis;


import com.huateng.qrcode.dao.UserDao;
import com.huateng.qrcode.entity.User;
import com.huateng.test.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试类：测试mybatis接口
 *
 * @author qinyupeng
 * @since 2018-11-12 14:25:48
 */
public class TestExample extends BaseTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void testFindByExample() {
        User user = new User();
        user.setUserId("1111");
        userDao.findByUser(user);
    }
}
