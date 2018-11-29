package com.huateng.qrcode.common.enums;

/**
 * 二维码交易流水状态枚举
 */
public enum QrCodeTxnStatusMenu {

    VALID("1", "有效"),
    INVALID("2", "失效"),
    REVERSED("3", "已冲正");

    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setMessage(String desc) {
        this.desc = desc;
    }

    QrCodeTxnStatusMenu(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
