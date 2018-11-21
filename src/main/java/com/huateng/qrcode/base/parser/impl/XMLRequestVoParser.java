package com.huateng.qrcode.base.parser.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.base.AppParamHeader;
import com.huateng.qrcode.base.parser.param.base.BusParamBody;
import com.huateng.qrcode.base.parser.param.base.SysParamHeader;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体解析xml报文类，将请求报文解析为RequestVo对象
 */
public class XMLRequestVoParser extends XMLMsgParser<RequestVo> {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public RequestVo parser(String msg) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(msg.getBytes("UTF-8")));
        Element rootElement = document.getRootElement();
        if (rootElement == null) {
            throw new RuntimeException("报文解析失败，根节点不存在！");
        }

        RequestVo requestVo = new RequestVo();
        List<Element> secElements = rootElement.elements();
        for (Element element : secElements) {
            String name = element.getName();
            if ("appHeader".equals(name)) {
                AppParamHeader appParamHeader = new AppParamHeader();
                parserAndWrapData(element, appParamHeader);
                requestVo.setAppHeader(appParamHeader);
            }

            if ("sysHeader".equals(name)) {
                SysParamHeader sysParamHeader = new SysParamHeader();
                parserAndWrapData(element, sysParamHeader);
                requestVo.setSysHeader(sysParamHeader);
            }

            if ("busBody".equals(name)) {
                BusParamBody busParamBody = new BusParamBody();
                parserAndWrapData(element, busParamBody);
                requestVo.setBusBody(busParamBody);
            }
        }

        return requestVo;
    }

    //解析封装应用头信息
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void parserAndWrapData(Element element, AppParamHeader appParamHeader) throws Exception {
        List<Element> elements = element.elements();
        if (CollectionUtils.isEmpty(elements)) {
            throw new RuntimeException("解析报文失败，应用头参数不能为空！");
        }

        Class<? extends AppParamHeader> clazz = appParamHeader.getClass();
        for (Element elem : elements) {
            String name = elem.getName();
            String value = elem.getStringValue();
            //利用反射，获取类的set方法，解析xml节点数据，设置为实体类属性
            clazz.getMethod(getSetMethodName(name), String.class).invoke(appParamHeader, value);
        }
    }

    //解析封装系统头信息
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void parserAndWrapData(Element element, SysParamHeader sysParamHeader) throws Exception {
        List<Element> elements = element.elements();
        if (CollectionUtils.isEmpty(elements)) {
            throw new RuntimeException("解析报文失败，系统头参数不能为空！");
        }

        Class<? extends SysParamHeader> clazz = sysParamHeader.getClass();
        for (Element elem : elements) {
            String name = elem.getName();
            String value = elem.getStringValue();
            //利用反射，获取类的set方法，解析xml节点数据，设置为实体类属性
            clazz.getMethod(getSetMethodName(name), String.class).invoke(sysParamHeader, value);
        }
    }


    //解析封装业务体信息
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void parserAndWrapData(Element element, BusParamBody busParamBody) throws Exception {
        List<Element> elements = element.elements();
        if (CollectionUtils.isEmpty(elements)) {
            throw new RuntimeException("解析报文失败，业务体参数不能为空！");
        }

        Class<? extends BusParamBody> clazz = busParamBody.getClass();
        for (Element elem : elements) {
            String elemName = elem.getName();
            String elemValue = elem.getStringValue();
            //普通的属性，找到属性set方法，反射设置属性值
            if (!"paramMap".equals(elemName)) {
                clazz.getMethod(getSetMethodName(elemName), String.class).invoke(busParamBody, elemValue);
            } else {
                List<Element> mapPropElements = elem.elements();
                if (CollectionUtils.isEmpty(mapPropElements)) {
                    throw new RuntimeException("解析报文失败，业务体中map参数为空！");
                }

                Map<String, String> paramMap = new HashMap<>();
                for (Element mapkeyElem : mapPropElements) {
                    paramMap.put(mapkeyElem.getName(), mapkeyElem.getStringValue());
                }

                busParamBody.setParamMap(paramMap);
            }
        }
    }


    //根据属性名，获取对应的set方法
    private String getSetMethodName(String propertyName) {
        return "set" + (Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1));
    }
}
