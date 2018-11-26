package com.huateng.test.testQrParser;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.common.constants.Constants;
import com.huateng.qrcode.common.enums.QrExpiryStatusEnum;
import com.huateng.qrcode.common.model.IdentityQrcode;
import com.huateng.qrcode.common.model.QrModule;
import com.huateng.qrcode.common.model.SeqInfo;
import com.huateng.qrcode.service.form.IdentityQrcodeService;
import com.huateng.qrcode.service.form.PaymentQrcodeService;
import com.huateng.qrcode.service.form.QrModuleService;
import com.huateng.qrcode.service.form.SeqInfoService;
import com.huateng.qrcode.utils.DateUtil;
import com.huateng.qrcode.utils.SeqGeneratorUtil;
import com.huateng.qrcode.utils.http.UpopHttpClient;
import com.huateng.test.BaseTest;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试二维码解析
 */
public class Test extends BaseTest {

    private String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<requestVo>\n" +
            "    <appHeader>\n" +
            "        <industryApp>010</industryApp>\n" +
            "        <reqSys>医疗云</reqSys>\n" +
            "        <scene>100</scene>\n" +
            "        <useType>002</useType>\n" +
            "    </appHeader>\n" +
            "    <busBody>\n" +
            "        <qrCode>1111112010888801110318112276543210</qrCode>\n" +
            "        <paramMap>\n" +
            "            <productNo>111000111</productNo>\n" +
            "            <validDate>5000</validDate>\n" +
            "            <version>2.0.1</version>\n" +
            "        </paramMap>\n" +
            "    </busBody>\n" +
            "    <sysHeader>\n" +
            "        <chlMsgId>2222222</chlMsgId>\n" +
            "        <chlSendTime>2018-10-10 22:10:10</chlSendTime>\n" +
            "        <receiver>lisi</receiver>\n" +
            "        <sendMsgId>11111</sendMsgId>\n" +
            "        <sendTime>2018-10-10</sendTime>\n" +
            "        <sender>zhangsan</sender>\n" +
            "        <serviceCode>001</serviceCode>\n" +
            "        <teller>zhangsan</teller>\n" +
            "        <version>1.0.0</version>\n" +
            "    </sysHeader>\n" +
            "</requestVo>";

    @Autowired
    private QrModuleService qrModuleService;

    @Autowired
    private IdentityQrcodeService identityQrcodeService;

    @Autowired
    private PaymentQrcodeService paymentQrcodeService;

    @Autowired
    private SeqInfoService seqInfoService;

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
    public void testSaveSeqInfo() {
        SeqInfo seqInfo = new SeqInfo();
        //序列的联合主键
        seqInfo.setSeqKey(Constants.SEQ_KEY);
        //序列初始值
        seqInfo.setInitValue(new BigDecimal(0));
        //序列最大值
        seqInfo.setMaxValue(new BigDecimal(0));
        //序列每次增长值
        seqInfo.setIncRuleValue(new BigDecimal(10));
        //序列的下限
        seqInfo.setDownLimit(new BigDecimal(0));
        //序列的上限
        seqInfo.setUpLimit(new BigDecimal(100));
        //定义成成7位的序列
        seqInfo.setRuleDef("7");
        seqInfoService.insert(seqInfo);
        System.out.println("--->>>新增序列规则成功！");
    }


    @org.junit.Test
    public void test() {
        String qrCode = "1111112010888801110318112212345671";
        System.out.println("--->>>actionScope=" + qrCode.substring(17, 18));
        System.out.println("--->>>token=" + qrCode.substring(26, 33));
        System.out.println("--->>>flag=" + qrCode.substring(33));
        System.out.println("--->>>qrcodeFromDb=" + qrCode.substring(0, 26));
    }


    @org.junit.Test
    public void testSelectById() {
        QrModule qrModule = qrModuleService.selectById("1065513003460046849");
        System.out.println(qrModule);
    }


    /**
     * 测试生成序列
     */
    @org.junit.Test
    public void testSeqGenerate() {
        for (int i = 0; i < 100; i++) {
            String sequenceNo = SeqGeneratorUtil.getInstance().getSequenceNo(Constants.SEQ_KEY);
            System.out.println("===>>>" + sequenceNo);
        }
    }


    @org.junit.Test
    public void testIsSingleInstance() {
        SeqGeneratorUtil instance = SeqGeneratorUtil.getInstance();
        SeqGeneratorUtil instance2 = SeqGeneratorUtil.getInstance();
        System.out.println("=====>>>>>是否是单例：" + (instance == instance2));
    }


    /**
     * 扫码类二维码解析：http://127.0.0.1:8082/qrcode/scan/handler.do?qrCode=1111112010888801110318112212345671
     */
    @org.junit.Test
    public void testParserForScan() throws Exception {
        String requestUrl = "http://127.0.0.1:8082/qrcode/scan/handler.do";
        UpopHttpClient httpClient = new UpopHttpClient(requestUrl, 10000, 10000);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("qrCode", "1111112010888801110318112276543211");
        int responseCode = httpClient.send(paramMap, "utf-8");
        System.out.println("==>>responseCode=" + responseCode);
        String result = httpClient.getResult();
        System.out.println("result=" + result);
    }

    /**
     * 系统类二维码解析：http://127.0.0.1:8082
     */
    @org.junit.Test
    public void testParserForSystem() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write(data);
        pw.flush();

        //读取字节流
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while (-1 != (len = (inputStream.read(buff)))) {
            System.out.println(new String(buff, 0, len, CharsetUtil.UTF_8));
        }

        inputStream.close();
        pw.close();
        outputStream.close();
        socket.close();
    }
}
