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

public class XMLRequestVoParser extends XMLMsgParser<RequestVo> {

    @Override
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
    private void parserAndWrapData(Element element, BusParamBody busParamBody) {
        List<Element> elements = element.elements();
        if (CollectionUtils.isEmpty(elements)) {
            throw new RuntimeException("解析报文失败，业务体参数不能为空！");
        }

        Class<? extends BusParamBody> clazz = busParamBody.getClass();
        Element mapElement = elements.get(0);
        List<Element> mapPropElements = mapElement.elements();
        if (CollectionUtils.isEmpty(mapPropElements)) {
            throw new RuntimeException("解析报文失败，业务体中map参数为空！");
        }

        Map<String, String> resultMap = new HashMap<>();
        for (Element elem : mapPropElements) {
            resultMap.put(elem.getName(), elem.getStringValue());
        }

        busParamBody.setResultMap(resultMap);
    }


    //根据属性名，获取对应的set方法
    private String getSetMethodName(String propertyName) {
        return "set" + (Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1));
    }
}
