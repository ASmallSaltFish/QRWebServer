package com.huateng.qrcode.common.enums;

/**
 * 二维码使用次数标识
 *
 * @author qinyupeng
 * @since 2018-11-29 09:21:35
 */
public enum QrUseTimesFlagEnum {

    USE_ONECE("1", "一次使用生效"),
    USE_MORE("99", "有效期内使用生效");

    private String code;
    private String desc;

    QrUseTimesFlagEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
