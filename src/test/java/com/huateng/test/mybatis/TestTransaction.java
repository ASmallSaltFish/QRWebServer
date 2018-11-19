package com.huateng.test.mybatis;


import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class TestTransaction extends BaseTest {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void test() {
        QrServerManager serverManager = (QrServerManager) ctx.getBean("qrParserManager");
        serverManager.handler("001");
    }


}
