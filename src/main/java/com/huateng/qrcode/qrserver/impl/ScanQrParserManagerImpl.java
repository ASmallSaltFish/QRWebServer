package com.huateng.qrcode.qrserver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.springframework.stereotype.Component;

/**
 * 扫码类二维码解析处理类
 */
@Component("scanQrParserManagerImpl")
public class ScanQrParserManagerImpl implements QrServerManager {

    @Override
    public String handler(RequestVo requestVo) throws Exception {
        //todo 检查二维码是否存在

        //todo 二维码状态、时效、黑名单设置

        //todo 风险等级校验

        //todo 二维码校验位校验

        //todo 生成响应报文
        ResponseVo responseVo = new ResponseVo();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestVo);
    }
}
