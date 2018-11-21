package com.huateng.qrcode.qrserver.impl;

import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 排队类二维码生成服务
 *
 * @author xuzhangsheng
 * @since 2018-11-20
 */
@Service(value = "LineUpQrGenerateImpl")
public class LineUpQrGenerateImpl implements QrServerManager {

    private static final Logger logger = LoggerFactory.getLogger(LineUpQrGenerateImpl.class);

    //todo 实际处理业务操作的方法
    @Override
    public ResponseVo handler(RequestVo requestVo) {
        return null;
    }
}
