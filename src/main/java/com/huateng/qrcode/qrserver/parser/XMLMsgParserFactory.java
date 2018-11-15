package com.huateng.qrcode.qrserver.parser;

import com.huateng.qrcode.model.vo.BaseVo;
import com.huateng.qrcode.qrserver.parser.factory.MsgParserFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Map;

/**
 * 定义xml报文通用解析实现
 *
 * @param <T> 泛型限定，解析报文生成的对象必须为BaseVo类的子类
 * @author qinyupeng
 * @since 2018-11-14 19:55:33
 */
public class XMLMsgParserFactory<T extends BaseVo> implements MsgParserFactory {

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
