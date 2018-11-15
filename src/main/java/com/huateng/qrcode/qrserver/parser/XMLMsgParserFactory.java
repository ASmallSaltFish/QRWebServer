package com.huateng.qrcode.qrserver.parser;

import com.huateng.qrcode.qrserver.parser.factory.MsgParserFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Map;

/**
 * 定义xml报文通用解析实现
 *
 * @author qinyupeng
 * @since 2018-11-14 19:55:33
 */
public class XMLMsgParserFactory<T> implements MsgParserFactory {

    //标签名和类名映射map，配置解析xml类和标签的关联关系
    private Map<String, Class> aliasMap;

    public void setAliasMap(Map<String, Class> aliasMap) {
        this.aliasMap = aliasMap;
    }

    public Map<String, Class> getAliasMap() {
        return aliasMap;
    }

    public XMLMsgParserFactory() {

    }

    public XMLMsgParserFactory(Map<String, Class> aliasMap) {
        this.aliasMap = aliasMap;
    }

    @Override
    public T parser(String msg) {
        XStream xStream = new XStream(new DomDriver());
        //设置别名
        for (Map.Entry<String, Class> entry : aliasMap.entrySet()) {
            String aliasName = entry.getKey();
            Class clazz = entry.getValue();
            xStream.alias(aliasName, clazz);
        }

        return (T) xStream.fromXML(msg);
    }
}
