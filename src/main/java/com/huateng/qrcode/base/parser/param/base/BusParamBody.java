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

    //
//    //版本
//    private String version;
//
//    //产品编号
//    private String productNo;
//
//
//    public String getValidDate() {
//        return validDate;
//    }
//
//    public void setValidDate(String validDate) {
//        this.validDate = validDate;
//    }
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    public String getProductNo() {
//        return productNo;
//    }
//
//    public void setProductNo(String productNo) {
//        this.productNo = productNo;
//    }
//
//    @Override
//    public String toString() {
//        return "BusParamBody{" +
//                "validDate='" + validDate + '\'' +
//                ", version='" + version + '\'' +
//                ", productNo='" + productNo + '\'' +
//                '}';
//    }
}
