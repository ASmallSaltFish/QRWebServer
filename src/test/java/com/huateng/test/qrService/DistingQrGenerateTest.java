package com.huateng.test.qrService;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.AppParamHeader;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.SysParamHeader;
import com.huateng.qrcode.common.enums.ServiceConfigEnums;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.service.algorithm.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DistingQrGenerateTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TokenService tokenService;

    @Resource(name = "DisplayQrGenerateImpl")
    private QrServerManager qrServerManager;

    @Test
    public void testServiceProvider() throws Exception {
        /*Class<? extends QrServerManager> serviceClass = ServiceConfigEnums.getByServiceCode("003");
        QrServerManager qrServerManager = applicationContext.getBean(serviceClass);*/
        RequestVo requestVo = new RequestVo();
        SysParamHeader sysParamHeader = new SysParamHeader();
        AppParamHeader appParamHeader = new AppParamHeader();
        BusParamBody busParamBody = new BusParamBody();


        appParamHeader.setIndustryApp("001");
        appParamHeader.setReqSys("0001");
        appParamHeader.setScene("01");
        appParamHeader.setEwmVersion("1");
        appParamHeader.setUseType("1");
        appParamHeader.setActionScope("1");
        appParamHeader.setGenerationMode("1");
        appParamHeader.setPrescription("1");

        requestVo.setSysHeader(sysParamHeader);
        requestVo.setAppHeader(appParamHeader);
        requestVo.setBusBody(busParamBody);

        ResponseVo out = qrServerManager.handler(requestVo);
        System.out.println("信息代码："+out.getResultCode()+"信息描述："+out.getResultMsg());
        out.getResultCode();
        out.getResultMsg();
    }

    @Test
    public void getTokenTest() throws Exception {
        System.out.println(tokenService.getToken());;
    }
}
