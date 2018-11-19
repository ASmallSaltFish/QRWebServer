package com.huateng.qrcode.parser;

/**
 * 定义解析报文工厂类
 */
public interface MsgParser<T> {

    T parser(String msg) throws Exception;
}
