package com.huateng.qrcode.model.param.base;

public class BusParamBody {

    //有效时间
    private String validDate;

    //版本
    private String version;

    //产品编号
    private String productNo;


    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        return "BusParamBody{" +
                "validDate='" + validDate + '\'' +
                ", version='" + version + '\'' +
                ", productNo='" + productNo + '\'' +
                '}';
    }
}
