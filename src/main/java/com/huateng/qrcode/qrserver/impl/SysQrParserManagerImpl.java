package com.huateng.qrcode.qrserver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.springframework.stereotype.Component;

/**
 * 系统类二维码解析处理类
 */
@Component("sysQrParserManagerImpl")
public class SysQrParserManagerImpl implements QrServerManager {
    @Override
    public String handler(RequestVo requestVo) throws Exception {
        // todo 报文参数校验 判空

        // todo 黑名单设置

        // todo 二维码解密

        // todo 反token判断

        // todo 检查二维码是否存在

        // todo 反校验位 判断

        // todo 二维码状态、时效

        // todo 生成响应报文
        ResponseVo responseVo = new ResponseVo();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(requestVo);
    }
}
