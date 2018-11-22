package com.huateng.qrcode.service.httpserver.impl;


import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.service.httpserver.ScanQrParserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScanQrParserServiceImpl implements ScanQrParserService {

    @Override
    public ResponseVo handler(Map<String, String> paraMap) {
        ResponseVo responseVo = new ResponseVo();
        return responseVo;
    }
}
