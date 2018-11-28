package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.sql.Clob;

/**
 * <p>
 * 运营类二维码表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_OPERATION_QRCODE")
public class OperationQrcode implements Serializable {

    private static final long serialVersionUID = 1L;

    //二维码明文
    @TableField("QRCODE_ID")
    private String qrcodeId;

    //二维码关联模板id
    @TableField("TEMPLET_ID")
    private String templetId;

    //生成日期
    @TableField("CUSTOM_MOD_DATE")
    private String customModDate;

    //生成时间
    @TableField("CUSTOM_MOD_TIME")
    private String customModTime;

    //token后的值
    @TableField("TOKEN")
    private String token;

    //token前值
    @TableField("BEFORE_TOKEN")
    private String beforeToken;

    //失效时间
    @TableField("EXPIRY_DATE_TIME")
    private String expiryDateTime;

    //失效状态（1-有效，2-失效）
    @TableField("EXPIRY_STATUS")
    private String expiryStatus;

    //图片编号
    @TableField("PICTURE_ID")
    private String pictureId;

    //创建日期
    @TableField("CRT_DATE")
    private String crtDate;

    //创建时间
    @TableField("CRT_TIME")
    private String crtTime;

    //创建人员
    @TableField("CRT_USER")
    private String crtUser;

    //业务数据
    @TableField("DATA")
    private String data;

    //机构编号
    @TableField("ORG_ID")
    private String orgId;

    //二维码跳转url
    @TableField("QR_URL")
    private String qrUrl;

    //更新人员
    @TableField("UPDATE_USER")
    private String updateUser;

    //更新日期（8位）
    @TableField("UPD_DATE")
    private String updDate;

    //更新时间（6位）
    @TableField("UPD_TIME")
    private String updTime;

    //备注字段1
    @TableField("MISC1")
    private String misc1;

    //备注字段2
    @TableField("MISC2")
    private String misc2;

    //备注字段3
    @TableField("MISC3")
    private String misc3;

    //备注字段4
    @TableField("MISC4")
    private String misc4;


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

    public String getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(String expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public String getExpiryStatus() {
        return expiryStatus;
    }

    public void setExpiryStatus(String expiryStatus) {
        this.expiryStatus = expiryStatus;
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

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    @Override
    public String toString() {
        return "IdentityQrcode{" +
                "qrcodeId='" + qrcodeId + '\'' +
                ", templetId='" + templetId + '\'' +
                ", customModDate='" + customModDate + '\'' +
                ", customModTime='" + customModTime + '\'' +
                ", token='" + token + '\'' +
                ", beforeToken='" + beforeToken + '\'' +
                ", expiryDateTime='" + expiryDateTime + '\'' +
                ", expiryStatus='" + expiryStatus + '\'' +
                ", pictureId='" + pictureId + '\'' +
                ", crtDate='" + crtDate + '\'' +
                ", crtTime='" + crtTime + '\'' +
                ", crtUser='" + crtUser + '\'' +
                ", data='" + data + '\'' +
                ", orgId='" + orgId + '\'' +
                ", qrUrl='" + qrUrl + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updDate='" + updDate + '\'' +
                ", updTime='" + updTime + '\'' +
                ", misc1='" + misc1 + '\'' +
                ", misc2='" + misc2 + '\'' +
                ", misc3='" + misc3 + '\'' +
                ", misc4='" + misc4 + '\'' +
                '}';
    }
}
