package com.huateng.test.msgParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.model.vo.RequestVo;
import com.huateng.qrcode.qrserver.parser.JsonMsgParserFactory;
import org.junit.Test;

import java.io.IOException;

public class JsonMsgTest {


    /**
     * {"reqSys":"医疗云平台","industryapp":"001","useType":"1","scene":"01"}
     */
    @Test
    public void testObj2Json() throws IOException {
        RequestVo requestVo = new RequestVo();
        requestVo.setIndustryapp("001");
        requestVo.setReqSys("医疗云平台");
        requestVo.setScene("01");
        requestVo.setUseType("1");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestVo);
        System.out.println(json);
    }


    @Test
    public void testJson2Obj() throws IOException {
        String json = "{\"reqSys\":\"医疗云平台\",\"industryapp\":\"001\",\"useType\":\"1\",\"scene\":\"01\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        //配置忽略未知的属性（User类中只定义了getter方法，没有定义属性，解析json为pojo时，该属性会被忽略）
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestVo requestVo = objectMapper.readValue(json, RequestVo.class);
        System.out.println("==>>requestVo=" + requestVo);
    }


    /**
     * 测试json报文解析工厂
     */
    @Test
    public void testJsonParser() throws IOException {
        String msg = "{\"reqSys\":\"医疗云平台\",\"industryapp\":\"001\",\"useType\":\"1\",\"scene\":\"01\"}";
        JsonMsgParserFactory<RequestVo> factory = new JsonMsgParserFactory<>(RequestVo.class);
        RequestVo requestVo = factory.parser(msg);
        System.out.println("==>>requestVo=" + requestVo);
    }
}
