package com.huateng.qrcode.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum ExpiryStatusEnum {
    EFFECTIVE("1", "有效"),
    INVALID("2", "无效");

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

    ExpiryStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ExpiryStatusEnum getByKey(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ExpiryStatusEnum type : values()) {
            if (type.getCode().equals(code))
                return type;
        }
        return null;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (ExpiryStatusEnum key : values()) {
            enumDataMap.put(key.getCode(), key.getDesc());
        }
        return enumDataMap;
    }
}
