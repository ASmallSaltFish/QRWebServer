package com.huateng.test;

import com.huateng.qrcode.utils.SpringContextUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试基类
 *
 * @author qinyupeng
 * @since 2018-11-12 14:26:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void before(){
        SpringContextUtil.getInstance().setApplicationContext(applicationContext);
    }

}
