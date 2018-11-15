package com.huateng.qrcode.qrserver.parser.factory;

/**
 * 定义解析报文工厂类
 */
public interface MsgParserFactory<T> {

    T parser(String msg) throws Exception;
}
