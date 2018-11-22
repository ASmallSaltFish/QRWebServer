package com.huateng.test.testQrParser;

import com.huateng.qrcode.common.model.QrModule;
import com.huateng.qrcode.service.form.QrModuleService;
import com.huateng.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试二维码解析
 */
public class Test extends BaseTest {

    @Autowired
    private QrModuleService qrModuleService;

    @org.junit.Test
    public void testSaveQrModule(){
        QrModule qrModule = new QrModule();
//        qrModule.setQrcodeVersion();
//        qrModuleService.insert();
    }
}
