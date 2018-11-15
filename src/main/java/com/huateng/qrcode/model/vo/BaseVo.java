package com.huateng.qrcode.model.vo;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * 定义公共的请求参数，被所有用来解析报文的类继承
 *
 * @author qinyupeng
 * @since 2018-11-14 19:30:04
 */
public class BaseVo implements Serializable {

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
