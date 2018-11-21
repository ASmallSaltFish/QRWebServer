package com.huateng.qrcode.base.parser.param.base;

import java.util.Map;

public class BusParamBody {

    //二维码码串
    private String qrCode;

    //参数映射
    private Map<String, String> paramMap;


    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }


    public String getQrCode() {
        return qrCode;
    }

    @Override
    public String toString() {
        return "BusParamBody{" +
                "qrCode='" + qrCode + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
