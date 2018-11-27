package com.huateng.qrcode.common.enums;

/**
 * 黑名单有效枚举
 */
public enum BlackInfoUseEnum {
    VALID("1", "启用"),
    INVALID("2", "停用");

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

    BlackInfoUseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
