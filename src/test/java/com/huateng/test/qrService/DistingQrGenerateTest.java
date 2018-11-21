package com.huateng.test.qrService;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.base.AppParamHeader;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.SysParamHeader;
import com.huateng.qrcode.common.enums.ServiceConfigEnums;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DistingQrGenerateTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testServiceProvider() throws Exception {
        Class<?> serviceClass = ServiceConfigEnums.getByServiceCode("003");
        QrServerManager qrServerManager = (QrServerManager) applicationContext.getBean(serviceClass);
        RequestVo requestVo = new RequestVo();
        SysParamHeader sysParamHeader = new SysParamHeader();
        AppParamHeader appParamHeader = new AppParamHeader();
        BusParamBody busParamBody = new BusParamBody();
        requestVo.setSysHeader(sysParamHeader);
        requestVo.setAppHeader(appParamHeader);
        requestVo.setBusBody(busParamBody);
        qrServerManager.handler(requestVo);
    }
}
