package com.huateng.qrcode.utils;

import java.util.Map;

/**
 * ajax请求响应json结果
 */
public class JsonResult {

    private String msg;
    private JsonStatus status = JsonStatus.ERROR;
    private Object data;
    private Map<String, Object> resultMap;

    public JsonResult() {

    }

    public JsonResult(JsonStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JsonResult(JsonStatus status, String msg, Map<String, Object> resultMap) {
        this.status = status;
        this.msg = msg;
        this.resultMap = resultMap;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonStatus getStatus() {
        return status;
    }

    public void setStatus(JsonStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
