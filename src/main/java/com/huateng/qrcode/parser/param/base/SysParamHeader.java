package com.huateng.qrcode.parser.param.base;

/**
 * 系统头信息参数
 */
public class SysParamHeader {

    //服务代码
    private String serviceCode;

    //版本号
    private String version;

    //发送者
    private String sender;

    //接收者
    private String receiver;

    //发送时间
    private String sendTime;

    //发起方流水号
    private String sendMsgId;

    //操作员
    private String teller;

    //渠道发送时间
    private String chlSendTime;

    //渠道交易流水
    private String chlMsgId;


    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendMsgId() {
        return sendMsgId;
    }

    public void setSendMsgId(String sendMsgId) {
        this.sendMsgId = sendMsgId;
    }

    public String getTeller() {
        return teller;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public String getChlSendTime() {
        return chlSendTime;
    }

    public void setChlSendTime(String chlSendTime) {
        this.chlSendTime = chlSendTime;
    }

    public String getChlMsgId() {
        return chlMsgId;
    }

    public void setChlMsgId(String chlMsgId) {
        this.chlMsgId = chlMsgId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "SysParamHeader{" +
                "serviceCode='" + serviceCode + '\'' +
                ", version='" + version + '\'' +
                ", sender='" + sender + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", sendMsgId='" + sendMsgId + '\'' +
                ", teller='" + teller + '\'' +
                ", chlSendTime='" + chlSendTime + '\'' +
                ", chlMsgId='" + chlMsgId + '\'' +
                '}';
    }
}
