package com.huateng.test.msgParser;

import com.huateng.qrcode.model.vo.RequestVo;
import com.huateng.qrcode.qrserver.parser.XMLMsgParserFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.*;

public class XmlMsgTest {


    /**
     * 使用JDK将javaBean转成xml
     */
    @Test
    public void testObj2XmlForJdk() throws JAXBException {
        RequestVo requestVo = new RequestVo();
        requestVo.setIndustryapp("001");
        requestVo.setReqSys("医疗云平台");
        requestVo.setScene("01");
        requestVo.setUseType("1");

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


    /**
     * 使用XStream解析xml，生成bean
     */
    @Test
    public void testXml2Obj() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><requestVo><industryapp>001</industryapp><reqSys>医疗云平台</reqSys><scene>01</scene><useType>1</useType></requestVo>";
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("requestVo", RequestVo.class);
        RequestVo requestVo = (RequestVo) xStream.fromXML(xml);
        System.out.println(requestVo);
    }


    /**
     * 测试通用xml解析工厂（解析简单对象）
     */
    @Test
    public void testXml2ObjUseParserFactory() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><requestVo><industryapp>001</industryapp><reqSys>医疗云平台</reqSys><scene>01</scene><useType>1</useType></requestVo>";
        Map<String, Class> aliasMap = new HashMap<>();
        aliasMap.put("requestVo", RequestVo.class);
        XMLMsgParserFactory<RequestVo> factory = new XMLMsgParserFactory<>(aliasMap);
        RequestVo requestVo = factory.parser(xml);
        System.out.println(requestVo);
    }
}
