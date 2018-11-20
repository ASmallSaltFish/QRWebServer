package com.huateng.qrcode.base.parser.param.base;

/**
 * 应用头信息参数
 */
public class AppParamHeader {

    //生成方式
    private String generationMode;

    //类别域
    private String actionScope;

    //读取方式
    private String readMode;

    //二维码版本
    private String ewmVersion;

    //响应系统
    private String rspSys;

    //二维码规则主键
    private String ewmMsgId;

    //请求系统名
    private String reqSys;

    //行业应用
    private String industryApp;

    //使用类型
    private String useType;

    //二维码场景
    private String scene;

    public String getGenerationMode() {
        return generationMode;
    }

    public void setGenerationMode(String generationMode) {
        this.generationMode = generationMode;
    }

    public String getActionScope() {
        return actionScope;
    }

    public void setActionScope(String actionScope) {
        this.actionScope = actionScope;
    }

    public String getReadMode() {
        return readMode;
    }

    public void setReadMode(String readMode) {
        this.readMode = readMode;
    }

    public String getEwmVersion() {
        return ewmVersion;
    }

    public void setEwmVersion(String ewmVersion) {
        this.ewmVersion = ewmVersion;
    }

    public String getRspSys() {
        return rspSys;
    }

    public void setRspSys(String rspSys) {
        this.rspSys = rspSys;
    }

    public String getEwmMsgId() {
        return ewmMsgId;
    }

    public void setEwmMsgId(String ewmMsgId) {
        this.ewmMsgId = ewmMsgId;
    }

    public String getReqSys() {
        return reqSys;
    }

    public void setReqSys(String reqSys) {
        this.reqSys = reqSys;
    }

    public String getIndustryApp() {
        return industryApp;
    }

    public void setIndustryApp(String industryApp) {
        this.industryApp = industryApp;
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
}
