package com.huateng.qrcode.base.parser.param.base;

import java.util.Map;

public class BusParamBody {

    //有效时间
    private Map<String, String> resultMap;

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }
}
