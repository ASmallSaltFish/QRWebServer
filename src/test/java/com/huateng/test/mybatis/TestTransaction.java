package com.huateng.test.mybatis;


import com.huateng.qrcode.base.parser.MsgParser;
import com.huateng.qrcode.base.parser.impl.XMLRequestVoParser;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class TestTransaction extends BaseTest {

    private String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<requestVo>\n" +
            "    <appHeader>\n" +
            "        <industryApp>010</industryApp>\n" +
            "        <reqSys>医疗云</reqSys>\n" +
            "        <scene>100</scene>\n" +
            "        <useType>002</useType>\n" +
            "    </appHeader>\n" +
            "    <busBody>\n" +
            "        <resultMap>\n" +
            "            <productNo>111000111</productNo>\n" +
            "            <validDate>5000</validDate>\n" +
            "            <version>2.0.1</version>\n" +
            "        </resultMap>\n" +
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
    private ApplicationContext ctx;

    @Test
    public void test() throws Exception {
        QrServerManager serverManager = (QrServerManager) ctx.getBean("qrParserManager");
        MsgParser<RequestVo> msgParser = new XMLRequestVoParser();
        RequestVo requestVo = msgParser.parser(data);
        serverManager.handler(requestVo);
    }


}
