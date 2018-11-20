package com.huateng.qrcode.base.parser.param.base;

import java.util.Map;

/**
 * 业务响应体
 */
public class BusRespBody {

    private Map<String, String> resultMap;


    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    @Override
    public String toString() {
        return "BusRespBody{" +
                "resultMap=" + resultMap +
                '}';
    }
}
