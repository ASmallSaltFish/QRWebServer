package com.huateng.qrcode.qrserver;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;

public interface QrServerManager {

    ResponseVo handler(RequestVo requestVo) throws Exception;
}
