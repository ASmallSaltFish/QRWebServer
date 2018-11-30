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

    SUCCESS("000000", "处理成功"),

    //--------------------------1 常规异常 -------------------------
    E100000("100000", "字段为空"),


    //--------------------------2 二维码生成异常 -------------------------
    E200000("200000", "二维码生成规则不存在"),

    //--------------------------3 二维码解析成异常 -------------------------
    QR_PARSER_FAIL("300000", "二维码解析失败"),
    ILLEGAL_PARAM("300001", "二维码解析参数非法"),
    QRCODE_NOT_FOUNT("300002", "二维码信息不存在"),
    QRCODE_STATUS_ILLEGAL("300003", "二维码状态非法"),
    BLACK_CHECK_ILLEGAL("300004", "黑名单校验失败"),
    EXPIRY_CHECK_ILLEGAL("300005", "有效期校验失败"),

    //--------------------------4 二维码其他成异常 -------------------------

    //--------------------------5 定时任务异常 -------------------------

    //--------------------------9 系统异常 -------------------------

    FAIL("999999", "处理失败");

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
