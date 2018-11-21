package com.huateng.qrcode.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @class: ErrorCodeEnum
 * @title: 错误代码
 * @desc: 各渠道服务使用的错误代码
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
public enum ErrorCodeEnum {

    SUCCESS("000000","处理成功"),
    FAIL("999999","处理失败");

    private String code;
    private String desc;

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setMessage(String desc){
        this.desc = desc;
    }

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorCodeEnum getByKey(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ErrorCodeEnum type : values()) {
            if (type.getCode().equals(code))
                return type;
        }
        return null;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (ErrorCodeEnum key : values()) {
            enumDataMap.put(key.getCode(), key.getDesc());
        }
        return enumDataMap;
    }
}
