package com.huateng.test.msgParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.base.AppParamHeader;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.SysParamHeader;
import com.huateng.qrcode.base.parser.impl.JsonMsgParser;
import com.huateng.qrcode.base.parser.MsgParser;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMsgTest {

    private String json = "{\"sysHeader\":{\"serviceCode\":\"001\",\"version\":\"1.0.0\",\"sender\":\"zhangsan\",\"receiver\":\"lisi\",\"sendTime\":\"2018-10-10\",\"sendMsgId\":\"11111\",\"teller\":\"zhangsan\",\"chlSendTime\":\"2018-10-10 22:10:10\",\"chlMsgId\":\"2222222\"},\"appHeader\":{\"generationMode\":null,\"actionScope\":null,\"readMode\":null,\"ewmVersion\":null,\"rspSys\":null,\"ewmMsgId\":null,\"reqSys\":\"医疗云\",\"industryApp\":\"010\",\"useType\":\"002\",\"scene\":\"100\"},\"busBody\":{\"qrCode\":\"1234567891234567891234567891234567\",\"paramMap\":{\"validDate\":\"5000\",\"version\":\"1.0.0\",\"productNo\":\"20\"}}}";


    @Test
    public void testObj2Json() throws IOException {
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
        busBody.setQrCode("1234567891234567891234567891234567");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("version", "1.0.0");
        paramMap.put("productNo", "20");
        paramMap.put("validDate", "5000");
        busBody.setParamMap(paramMap);

        requestVo.setBusBody(busBody);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestVo);
        System.out.println(json);
    }


    @Test
    public void testJson2Obj() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //配置忽略未知的属性（User类中只定义了getter方法，没有定义属性，解析json为pojo时，该属性会被忽略）
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestVo requestVo = objectMapper.readValue(json, RequestVo.class);
        System.out.println("==>>requestVo=" + requestVo);
        BusParamBody busBody = requestVo.getBusBody();
        busBody.setQrCode("1234567891234567891234567891234567");
        Map<String, String> paramMap = busBody.getParamMap();
        System.out.println(paramMap.get("version"));
        System.out.println(paramMap.get("productNo"));
        System.out.println(paramMap.get("validDate"));
    }


    /**
     * 测试json报文解析工厂
     */
    @Test
    public void testJsonParser() throws Exception {
        MsgParser<RequestVo> parser = new JsonMsgParser<>(RequestVo.class);
        RequestVo requestVo = parser.parser(json);
        System.out.println("==>>requestVo=" + requestVo);
    }
}
