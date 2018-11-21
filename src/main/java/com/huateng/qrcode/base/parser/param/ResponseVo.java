package com.huateng.qrcode.base.parser.param;

import com.huateng.qrcode.base.parser.param.base.AppRespHeader;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.base.parser.param.base.SysRespHeader;

/**
 * 响应报文类
 */
public class ResponseVo {

    private AppRespHeader appRespHeader;

    private SysRespHeader sysRespHeader;

    private BusRespBody busRespBody;

    private String resultCode;

    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public AppRespHeader getAppRespHeader() {
        return appRespHeader;
    }

    public void setAppRespHeader(AppRespHeader appRespHeader) {
        this.appRespHeader = appRespHeader;
    }

    public SysRespHeader getSysRespHeader() {
        return sysRespHeader;
    }

    public void setSysRespHeader(SysRespHeader sysRespHeader) {
        this.sysRespHeader = sysRespHeader;
    }

    public BusRespBody getBusRespBody() {
        return busRespBody;
    }

    public void setBusRespBody(BusRespBody busRespBody) {
        this.busRespBody = busRespBody;
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "appRespHeader=" + (appRespHeader == null ? "null" : appRespHeader.toString()) +
                ", sysRespHeader=" + (sysRespHeader == null ? "null" : sysRespHeader.toString()) +
                ", busRespBody=" + (busRespBody == null ? "null" : busRespBody.toString()) +
                ", resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}
