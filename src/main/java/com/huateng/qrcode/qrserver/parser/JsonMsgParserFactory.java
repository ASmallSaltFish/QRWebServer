package com.huateng.qrcode.qrserver.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.qrserver.parser.factory.MsgParserFactory;

import java.io.IOException;
import java.io.Serializable;

/**
 * 定义json报文通用方式的解析工厂类
 *
 * @author qinyupeng
 * @since 2018-11-15 16:30:55
 */
public class JsonMsgParserFactory<T extends Serializable> implements MsgParserFactory<T> {

    private Class<T> clazz;

    public JsonMsgParserFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parser(String msg) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object object = objectMapper.readValue(msg, clazz);
        return (T) object;
    }
}
