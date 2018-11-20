package com.huateng.test.msgParser;

import com.huateng.qrcode.base.parser.impl.XMLRequestVoParser;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.base.AppParamHeader;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.SysParamHeader;
import com.huateng.qrcode.base.parser.MsgParser;
import com.huateng.qrcode.base.parser.impl.XMLMsgParser;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class XmlMsgTest {

    private String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
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


    @Test
    public void testObj2XmlForXstream() {
        RequestVo requestVo = getRequestVo();
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("requestVo", RequestVo.class);
        xStream.alias("sysHeader", SysParamHeader.class);
        xStream.alias("appHeader", AppParamHeader.class);
        xStream.alias("busBody", BusParamBody.class);
        String xml = xStream.toXML(requestVo);
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        String result = sb.append(xml).toString();
        System.out.println(result);
    }


    /**
     * 使用JDK将javaBean转成xml
     */
    @Test
    public void testObj2XmlForJdk() throws JAXBException {
        RequestVo requestVo = getRequestVo();

        JAXBContext jaxbContext = JAXBContext.newInstance(RequestVo.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        //是否格式化xml
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //将xml的头文件去除
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        //重新添加xml通用头文件
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        marshaller.marshal(requestVo, writer);
        String result = writer.toString();
        System.out.println(sb.append(result).toString());
    }

    private RequestVo getRequestVo() {
        RequestVo requestVo = new RequestVo();
        //系统头信息
        SysParamHeader sysHeader = new SysParamHeader();
        sysHeader.setServiceCode("001");
        sysHeader.setVersion("1.0.0");
        sysHeader.setSender("zhangsan");
        sysHeader.setReceiver("lisi");
        sysHeader.setSendTime("2018-10-10");
        sysHeader.setSendMsgId("11111");
        sysHeader.setTeller("zhangsan");
        sysHeader.setChlSendTime("2018-10-10 22:10:10");
        sysHeader.setChlMsgId("2222222");
        requestVo.setSysHeader(sysHeader);

        //应用头信息
        AppParamHeader appHeader = new AppParamHeader();
        appHeader.setIndustryApp("010");
        appHeader.setReqSys("医疗云");
        appHeader.setScene("100");
        appHeader.setUseType("002");
        requestVo.setAppHeader(appHeader);

        //业务体
        BusParamBody busBody = new BusParamBody();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("version", "1.0.0");
        resultMap.put("productNo", "20");
        resultMap.put("validDate", "5000");
        busBody.setResultMap(resultMap);

        requestVo.setBusBody(busBody);
        return requestVo;
    }


    /**
     * 使用XStream解析xml，生成bean
     */
    @Test
    public void testXml2Obj() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("requestVo", RequestVo.class);
        RequestVo requestVo = (RequestVo) xStream.fromXML(xml);
        System.out.println(requestVo);
    }


    /**
     * 测试通用xml解析工厂（解析简单对象）
     */
    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void testXml2ObjUseParserFactory() throws Exception {
        MsgParser<RequestVo> parser = new XMLRequestVoParser();
        RequestVo requestVo = parser.parser(xml);
        System.out.println(requestVo);
        System.out.println(requestVo.getBusBody().getResultMap().get("productNo"));
        System.out.println(requestVo.getBusBody().getResultMap().get("version"));
        System.out.println(requestVo.getBusBody().getResultMap().get("validDate"));
    }
}
