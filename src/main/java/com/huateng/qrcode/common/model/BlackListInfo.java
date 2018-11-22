package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 *  黑名单表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_BLACK_LIST")
public class BlackListInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("BLANK_DESC")
    private String blankDesc;
    @TableField("BLANK_ID")
    private String blankId;
    @TableField("BLANK_NO")
    private String blankNo;
    @TableField("BLANK_TYPE")
    private String blankType;
    @TableField("CRT_TIME")
    private String crtTime;
    @TableField("CRT_USER")
    private String crtUser;
    @TableField("EXPIRY_TIME")
    private String expiryTime;
    @TableField("IS_USE")
    private String isUse;
    @TableField("MISC1")
    private String misc1;
    @TableField("MISC2")
    private String misc2;
    @TableField("MISC3")
    private String misc3;
    @TableField("MISC4")
    private String misc4;
    @TableField("UPDATE_TIME")
    private String updateTime;
    @TableField("UPDATE_USER")
    private String updateUser;


    public String getBlankDesc() {
        return blankDesc;
    }

    public void setBlankDesc(String blankDesc) {
        this.blankDesc = blankDesc;
    }

    public String getBlankId() {
        return blankId;
    }

    public void setBlankId(String blankId) {
        this.blankId = blankId;
    }

    public String getBlankNo() {
        return blankNo;
    }

    public void setBlankNo(String blankNo) {
        this.blankNo = blankNo;
    }

    public String getBlankType() {
        return blankType;
    }

    public void setBlankType(String blankType) {
        this.blankType = blankType;
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

    public String getExpireTime() {
        return expiryTime;
    }

    public void setExpireTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "BlackList{" +
        "blankDesc=" + blankDesc +
        ", blankId=" + blankId +
        ", blankNo=" + blankNo +
        ", blankType=" + blankType +
        ", crtTime=" + crtTime +
        ", crtUser=" + crtUser +
        ", expiryTime=" + expiryTime +
        ", isUse=" + isUse +
        ", misc1=" + misc1 +
        ", misc2=" + misc2 +
        ", misc3=" + misc3 +
        ", misc4=" + misc4 +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
