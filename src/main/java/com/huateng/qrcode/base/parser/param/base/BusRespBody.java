package com.huateng.qrcode.base.parser.param.base;

import java.util.Map;

/**
 * 业务响应体
 */
public class BusRespBody {

    //处理结果码
    private String processCode;

    //状态
    private String processStatus;

    //信息
    private String msg;

    private Map<String, String> resultMap;


    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BusRespBody{" +
                "processCode='" + processCode + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", msg='" + msg + '\'' +
                ", resultMap=" + resultMap +
                '}';
    }
}
