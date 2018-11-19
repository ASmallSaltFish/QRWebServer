package com.huateng.qrcode.base.parser.param.base;

/**
 * 应用头信息参数
 */
public class AppParamHeader {

    //请求系统名
    private String reqSys;

    //行业应用
    private String industryapp;

    //使用类型
    private String useType;

    //二维码场景
    private String scene;

    public String getReqSys() {
        return reqSys;
    }

    public void setReqSys(String reqSys) {
        this.reqSys = reqSys;
    }

    public String getIndustryapp() {
        return industryapp;
    }

    public void setIndustryapp(String industryapp) {
        this.industryapp = industryapp;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }


    @Override
    public String toString() {
        return "BaseVo{" +
                "reqSys='" + reqSys + '\'' +
                ", industryapp='" + industryapp + '\'' +
                ", useType='" + useType + '\'' +
                ", scene='" + scene + '\'' +
                '}';
    }
}
