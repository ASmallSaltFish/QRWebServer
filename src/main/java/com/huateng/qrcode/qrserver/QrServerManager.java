package com.huateng.qrcode.qrserver;

import com.huateng.qrcode.base.parser.param.RequestVo;

public interface QrServerManager {

    String handler(RequestVo requestVo);
}
