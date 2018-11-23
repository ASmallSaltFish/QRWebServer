package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Clob;

/**
 * <p>
 * 二维码交易流水表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_QRCODE_TXN")
public class QrcodeTxn implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @TableField("TXN_ID")
    private String txnId;

    //原交易流水号
    @TableField("ORG_TXN_ID")
    private String orgTxnId;

    //二维码编号
    @TableField("QRCODE_ID")
    private String qrcodeId;

    //渠道号
    @TableField("CHANNEL_ID")
    private String channelId;

    //渠道流水号
    @TableField("CHANNEL_NO")
    private String channelNo;

    //渠道交易日期
    @TableField("CHANNEL_DATE")
    private String channelDate;

    //渠道交易时间
    @TableField("CHANNEL_TIME")
    private String channelTime;

    //请求系统
    @TableField("REQ_SYS_NO")
    private String reqSysNo;

    //应答系统
    @TableField("RESP_SYS_NO")
    private String respSysNo;

    //行内应用
    @TableField("APPLICATION")
    private String application;

    //用途
    @TableField("PURPOSE")
    private String purpose;

    //场景
    @TableField("SCENE")
    private String scene;

    //请求报文
    @TableField("REQUEST_DATA")
    private String requestData;

    //交易类型
    @TableField("TRANS_TYPE")
    private String transType;

    //接收日期
    @TableField("RECEIVE_DATE")
    private String receiveDate;

    //接收时间
    @TableField("RECEIVE_TIME")
    private String receiveTime;

    //交易状态
    @TableField("STATUS")
    private String status;

    //备注
    @TableField("REMARK")
    private String remark;

    //返回码
    @TableField("RETURN_CODE")
    private String returnCode;

    //返回日期
    @TableField("RETURN_DATE")
    private String returnDate;

    //返回描述
    @TableField("RETURN_DESC")
    private String returnDesc;

    //返回时间
    @TableField("RETURN_TIME")
    private String returnTime;

    //机构编号
    @TableField("ORG_ID")
    private String orgId;

    @TableField("UPDATE_USER")
    private String updateUser;

    @TableField("UPD_DATE")
    private String updDate;

    @TableField("CRT_DATE")
    private String crtDate;

    @TableField("CRT_TIME")
    private String crtTime;

    @TableField("CRT_USER")
    private String crtUser;

    @TableField("MISC1")
    private String misc1;

    @TableField("MISC2")
    private String misc2;

    @TableField("MISC3")
    private String misc3;

    @TableField("MISC4")
    private String misc4;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getChannelDate() {
        return channelDate;
    }

    public void setChannelDate(String channelDate) {
        this.channelDate = channelDate;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelTime() {
        return channelTime;
    }

    public void setChannelTime(String channelTime) {
        this.channelTime = channelTime;
    }

    public String getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getMisc1() {
        return misc1;
    }

    public void setMisc1(String misc1) {
        this.misc1 = misc1;
    }

    public String getMisc2() {
        return misc2;
    }

    public void setMisc2(String misc2) {
        this.misc2 = misc2;
    }

    public String getMisc3() {
        return misc3;
    }

    public void setMisc3(String misc3) {
        this.misc3 = misc3;
    }

    public String getMisc4() {
        return misc4;
    }

    public void setMisc4(String misc4) {
        this.misc4 = misc4;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgTxnId() {
        return orgTxnId;
    }

    public void setOrgTxnId(String orgTxnId) {
        this.orgTxnId = orgTxnId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getReqSysNo() {
        return reqSysNo;
    }

    public void setReqSysNo(String reqSysNo) {
        this.reqSysNo = reqSysNo;
    }

    public String getRespSysNo() {
        return respSysNo;
    }

    public void setRespSysNo(String respSysNo) {
        this.respSysNo = respSysNo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdDate() {
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        return "QrcodeTxn{" +
                "application=" + application +
                ", channelDate=" + channelDate +
                ", channelId=" + channelId +
                ", channelNo=" + channelNo +
                ", channelTime=" + channelTime +
                ", crtDate=" + crtDate +
                ", crtTime=" + crtTime +
                ", crtUser=" + crtUser +
                ", misc1=" + misc1 +
                ", misc2=" + misc2 +
                ", misc3=" + misc3 +
                ", misc4=" + misc4 +
                ", orgId=" + orgId +
                ", orgTxnId=" + orgTxnId +
                ", purpose=" + purpose +
                ", qrcodeId=" + qrcodeId +
                ", receiveDate=" + receiveDate +
                ", receiveTime=" + receiveTime +
                ", remark=" + remark +
                ", requestData=" + requestData +
                ", reqSysNo=" + reqSysNo +
                ", respSysNo=" + respSysNo +
                ", returnCode=" + returnCode +
                ", returnDate=" + returnDate +
                ", returnDesc=" + returnDesc +
                ", returnTime=" + returnTime +
                ", scene=" + scene +
                ", status=" + status +
                ", transType=" + transType +
                ", txnId=" + txnId +
                ", updateUser=" + updateUser +
                ", updDate=" + updDate +
                "}";
    }
}
