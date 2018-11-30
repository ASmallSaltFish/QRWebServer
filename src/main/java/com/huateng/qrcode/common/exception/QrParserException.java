package com.huateng.qrcode.common.exception;

import com.huateng.qrcode.common.enums.ErrorCodeEnum;
import com.huateng.qrcode.common.exception.base.BaseException;

/**
 * 自定义二维码解析异常
 *
 * @author qinyupeng
 * @since 2018-11-30 09:51:57
 */
public class QrParserException extends BaseException {

    public QrParserException() {
    }

    public QrParserException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public QrParserException(ErrorCodeEnum errorCodeEnum, String message) {
        super(errorCodeEnum, message);
    }
}
