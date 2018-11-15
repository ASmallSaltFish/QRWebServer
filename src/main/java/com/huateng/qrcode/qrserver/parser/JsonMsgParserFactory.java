package com.huateng.qrcode.qrserver.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.model.vo.BaseVo;
import com.huateng.qrcode.qrserver.parser.factory.MsgParserFactory;

import java.io.IOException;

/**
 * 定义json报文通用方式的解析抽象类
 *
 * @param <T> 泛型限定，解析报文生成的对象必须为BaseVo类的子类
 */
public class JsonMsgParserFactory<T extends BaseVo> implements MsgParserFactory<T> {

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
