package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Clob;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_PAYMENT_QRCODE_HIS")
public class PaymentQrcodeHis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("BEFORE_TOKEN")
    private String beforeToken;
    @TableField("CRT_DATE")
    private String crtDate;
    @TableField("CRT_TIME")
    private String crtTime;
    @TableField("CRT_USER")
    private String crtUser;
    @TableField("CUSTOM_MOD_DATE")
    private String customModDate;
    @TableField("CUSTOM_MOD_TIME")
    private String customModTime;
    @TableField("DATA")
    private String data;
    @TableField("EXPIRE_DATE_TIME")
    private String expireDateTime;
    @TableField("EXPIRE_STATUS")
    private String expireStatus;
    @TableField("MISC1")
    private String misc1;
    @TableField("MISC2")
    private String misc2;
    @TableField("MISC3")
    private String misc3;
    @TableField("MISC4")
    private String misc4;
    @TableField("ORG_ID")
    private String orgId;
    @TableField("PICTURE_ID")
    private String pictureId;
    @TableField("QRCODE_ID")
    private String qrcodeId;
    @TableField("QR_URL")
    private String qrUrl;
    @TableField("TEMPLET_ID")
    private String templetId;
    @TableField("TOKEN")
    private String token;
    @TableField("UPDATE_USER")
    private String updateUser;
    @TableField("UPD_DATE")
    private String updDate;


    public String getBeforeToken() {
        return beforeToken;
    }

    public void setBeforeToken(String beforeToken) {
        this.beforeToken = beforeToken;
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

    public String getCustomModDate() {
        return customModDate;
    }

    public void setCustomModDate(String customModDate) {
        this.customModDate = customModDate;
    }

    public String getCustomModTime() {
        return customModTime;
    }

    public void setCustomModTime(String customModTime) {
        this.customModTime = customModTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(String expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public String getExpireStatus() {
        return expireStatus;
    }

    public void setExpireStatus(String expireStatus) {
        this.expireStatus = expireStatus;
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

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public String getTempletId() {
        return templetId;
    }

    public void setTempletId(String templetId) {
        this.templetId = templetId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        return "PaymentQrcodeHis{" +
        "beforeToken=" + beforeToken +
        ", crtDate=" + crtDate +
        ", crtTime=" + crtTime +
        ", crtUser=" + crtUser +
        ", customModDate=" + customModDate +
        ", customModTime=" + customModTime +
        ", data=" + data +
        ", expireDateTime=" + expireDateTime +
        ", expireStatus=" + expireStatus +
        ", misc1=" + misc1 +
        ", misc2=" + misc2 +
        ", misc3=" + misc3 +
        ", misc4=" + misc4 +
        ", orgId=" + orgId +
        ", pictureId=" + pictureId +
        ", qrcodeId=" + qrcodeId +
        ", qrUrl=" + qrUrl +
        ", templetId=" + templetId +
        ", token=" + token +
        ", updateUser=" + updateUser +
        ", updDate=" + updDate +
        "}";
    }
}
