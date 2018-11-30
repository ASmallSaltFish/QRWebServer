package com.huateng.qrcode.common.exception.base;

import com.huateng.qrcode.common.enums.ErrorCodeEnum;

/**
 * 自定义异常父类
 *
 * @author qinyupeng
 * @since 2018-11-30 09:50:54
 */
public class BaseException extends RuntimeException {
    private String errorCode;

    public BaseException() {

    }

    public BaseException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc());
        this.errorCode = errorCodeEnum.getCode();
    }

    public BaseException(ErrorCodeEnum errorCodeEnum, String message) {
        super(message);
        this.errorCode = errorCodeEnum.getCode();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
