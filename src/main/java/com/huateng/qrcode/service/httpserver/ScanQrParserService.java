package com.huateng.qrcode.service.httpserver;

import com.huateng.qrcode.base.parser.param.ResponseVo;

import java.util.Map;

/**
 * 提供扫码解析服务
 */
public interface ScanQrParserService {

    /**
     * 根据http请求的业务参数，解析二维码，返回业务数据
     *
     * @param paraMap http请求参数
     * @return 返回响应对象
     */
    ResponseVo handler(Map<String, String> paraMap);
}
