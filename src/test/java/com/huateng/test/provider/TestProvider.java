package com.huateng.test.provider;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.test.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 单元测试，使用策略模式处理根据服务号，调用对应类，提供对应服务
 */
public class TestProvider extends BaseTest {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, String> serviceCodeMap = new HashMap<>();
    private String serviceCode;

    @Before
    public void before() {
        serviceCode = "001";
        serviceCodeMap.put("001", "com.huateng.qrcode.qrserver.QrServerManager");
    }

    @Test
    public void testServiceProvider() throws Exception {
        String classFullName = serviceCodeMap.get(serviceCode);
        Class<?> clazz = Class.forName(classFullName);
        QrServerManager manager = (QrServerManager) ctx.getBean(clazz);
        manager.handler(new RequestVo());
    }
}
