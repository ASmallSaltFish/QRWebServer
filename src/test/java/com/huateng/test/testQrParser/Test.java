package com.huateng.test.testQrParser;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.enums.QrExpiryStatusEnum;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.common.model.QrModule;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.service.form.QrModuleService;
import com.huateng.qrcode.utils.DateUtil;
import com.huateng.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import sun.tools.java.Identifier;

/**
 * 测试二维码解析
 */
public class Test extends BaseTest {

    @Autowired
    private QrModuleService qrModuleService;

    @Autowired
    private IdentityQrcodeService identityQrcodeService;

    /**
     * 测试生成二维码模版数据
     */
    @org.junit.Test
    public void testSaveQrModule() {
        QrModule qrModule = new QrModule();
        qrModule.setQrModId(IdWorker.getIdStr());

        //8位TIN
        qrModule.setQrcodeVersion("1");
        qrModule.setEncodeMode("1");
        qrModule.setGenerationMode("1");
        qrModule.setExpiryDate("1");
        qrModule.setActionScope("1");
        qrModule.setReadMode("1");
        qrModule.setRiskLevel("2");
        qrModule.setBackCode("0");
        qrModule.setEntryFlag("1");

        //12为自定义
        qrModule.setKeyVersion("0");
        qrModule.setReqSys("8888");
        qrModule.setIndustryApp("011");
        qrModule.setUseType("1");
        qrModule.setUseScene("03");

        qrModule.setQrModCode("11111120108888011103");

        qrModule.setCreateTime(DateUtil.getCurrentDateTimeStr());
        qrModuleService.insert(qrModule);
    }


    /**
     * 测试生成识别类二维码
     */
    @org.junit.Test
    public void testSaveIdentityQrcode() {
        EntityWrapper<QrModule> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("qr_mod_code={0}", "11111120108888011103");
        QrModule qrModule = qrModuleService.selectOne(entityWrapper);
        if (qrModule == null) {
            throw new RuntimeException("二维码模板不存在");
        }

        IdentityQrcode identityQrcode = new IdentityQrcode();
        //生成日期（6位）
        identityQrcode.setCustomModDate(DateUtil.getYYMMDD());
        //token前的值
        identityQrcode.setBeforeToken("1234567");
        //token后的值
        identityQrcode.setToken("7654321");
        //关联二维码模版id
        identityQrcode.setTempletId(qrModule.getQrModId());
        //二维码有效期
        identityQrcode.setExpiryStatus(QrExpiryStatusEnum.VALID.getCode());

        identityQrcode.setData("测试数据");
        identityQrcode.setCustomModTime(DateUtil.getCurrentTimeStr());
        identityQrcode.setQrUrl("http://www.baidu.com");

        //二维码主键=模版编码(20) + 产生日期（6位）+ Token令牌(7) +  校验位(1) = 34
        String qrCodeId = qrModule.getQrModCode() + identityQrcode.getCustomModDate() + identityQrcode.getBeforeToken() + "1";
        identityQrcode.setQrcodeId(qrCodeId);

        identityQrcode.setCrtDate(DateUtil.getCurrentDateStr());

        identityQrcodeService.insert(identityQrcode);
        System.out.println("---->>>识别类二维码生成成功！");
    }

    @org.junit.Test
    public void test() {
        String qrCode = "1111112010888801110318112212345671";
        System.out.println("--->>>actionScope=" + qrCode.substring(17, 18));
        System.out.println("--->>>token=" + qrCode.substring(26, 33));
        System.out.println("--->>>flag=" + qrCode.substring(33));
        System.out.println("--->>>qrcodeFromDb=" + qrCode.substring(0,26));
    }
}
