package com.huateng.qrcode.common.enums;

/**
 * 二维码用途枚举
 */
public enum UseTypeEnum {

    USE_TYPE_IDENTITY("1", "识别类"),
    USE_TYPE_DISPLAY("2", "展示类"),
    USE_TYPE_PAYMENT("3", "支付类"),
    USE_TYPE_OPERATION("4", "运营类"),
    USE_TYPE_QUEUE("5", "排队类");

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

    UseTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

