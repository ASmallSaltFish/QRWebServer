package com.huateng.qrcode.base.parser.param.base;

/**
 * 应用头信息参数
 */
public class AppParamHeader {

    //生成方式
    private String generationmode;

    //类别域
    private String actionscope;

    //读取方式
    private String readmode;

    //二维码版本
    private String ewmversion;

    //响应系统
    private String rspsys;

    //二维码规则主键
    private String ewmmsgid;

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

    public String getGenerationmode() {
        return generationmode;
    }

    public void setGenerationmode(String generationmode) {
        this.generationmode = generationmode;
    }

    public String getActionscope() {
        return actionscope;
    }

    public void setActionscope(String actionscope) {
        this.actionscope = actionscope;
    }

    public String getReadmode() {
        return readmode;
    }

    public void setReadmode(String readmode) {
        this.readmode = readmode;
    }

    public String getEwmversion() {
        return ewmversion;
    }

    public void setEwmversion(String ewmversion) {
        this.ewmversion = ewmversion;
    }

    public String getRspsys() {
        return rspsys;
    }

    public void setRspsys(String rspsys) {
        this.rspsys = rspsys;
    }

    public String getEwmmsgid() {
        return ewmmsgid;
    }

    public void setEwmmsgid(String ewmmsgid) {
        this.ewmmsgid = ewmmsgid;
    }


    @Override
    public String toString() {
        return "AppParamHeader{" +
                "generationmode='" + generationmode + '\'' +
                ", actionscope='" + actionscope + '\'' +
                ", readmode='" + readmode + '\'' +
                ", ewmversion='" + ewmversion + '\'' +
                ", rspsys='" + rspsys + '\'' +
                ", ewmmsgid='" + ewmmsgid + '\'' +
                ", reqSys='" + reqSys + '\'' +
                ", industryapp='" + industryapp + '\'' +
                ", useType='" + useType + '\'' +
                ", scene='" + scene + '\'' +
                '}';
    }
}
