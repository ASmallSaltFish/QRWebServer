package com.huateng.qrcode.common.enums;

/**
 * 二维码有效状态枚举
 */
public enum QrExpiryStatusEnum {

    VALID("1", "有效"),
    INVALID("2", "失效");

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

    QrExpiryStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
