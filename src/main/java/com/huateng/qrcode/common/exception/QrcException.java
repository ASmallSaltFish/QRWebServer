package com.huateng.qrcode.common.exception;

public class QrcException extends Exception {
    private String errCode;
    private String errMsg;

    public QrcException() {
        super();
    }

    public QrcException(String message, Throwable cause) {
        super(message, cause);
    }

    public QrcException(String message) {
        super(message);
    }

    public QrcException(Throwable cause) {
        super(cause);
    }

    public QrcException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
