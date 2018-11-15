package com.huateng.qrcode.model.param;

import com.huateng.qrcode.model.param.base.AppParamHeader;
import com.huateng.qrcode.model.param.base.BusParamBody;
import com.huateng.qrcode.model.param.base.SysParamHeader;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 组装报文类，将系统头信息、应用头信息、业务头信息对象组合
 *
 * @author qinyupeng
 * @since 2018-11-14 19:29:50
 */
@XmlRootElement
public class RequestVo {

    //系统头信息
    private SysParamHeader sysHeader;

    //应用头信息
    private AppParamHeader appHeader;

    //业务体
    private BusParamBody busBody;


    public SysParamHeader getSysHeader() {
        return sysHeader;
    }

    public void setSysHeader(SysParamHeader sysHeader) {
        this.sysHeader = sysHeader;
    }

    public AppParamHeader getAppHeader() {
        return appHeader;
    }

    public void setAppHeader(AppParamHeader appHeader) {
        this.appHeader = appHeader;
    }

    public BusParamBody getBusBody() {
        return busBody;
    }

    public void setBusBody(BusParamBody busBody) {
        this.busBody = busBody;
    }

    @Override
    public String toString() {
        return "RequestVo{" +
                "sysHeader=" + sysHeader +
                ", appHeader=" + appHeader +
                ", busBody=" + busBody +
                '}';
    }
}
