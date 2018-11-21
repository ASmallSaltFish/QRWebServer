package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_QR_PARAM")
public class QrParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CRT_DATE")
    private String crtDate;
    @TableField("CRT_TIME")
    private String crtTime;
    @TableField("CRT_USER")
    private String crtUser;
    @TableField("FLAG_ENABLE")
    private String flagEnable;
    @TableField("ID")
    private String id;
    @TableField("PARAM_CODE")
    private String paramCode;
    @TableField("PARAM_DESC")
    private String paramDesc;
    @TableField("PARAM_NAME")
    private String paramName;
    @TableField("PROP_CODE")
    private String propCode;
    @TableField("PROP_NAME")
    private String propName;
    @TableField("UPDATE_DATE")
    private String updateDate;
    @TableField("UPDATE_TIME")
    private String updateTime;
    @TableField("UPDATE_USER")
    private String updateUser;


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

    public String getFlagEnable() {
        return flagEnable;
    }

    public void setFlagEnable(String flagEnable) {
        this.flagEnable = flagEnable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getPropCode() {
        return propCode;
    }

    public void setPropCode(String propCode) {
        this.propCode = propCode;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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
        return "QrParam{" +
        "crtDate=" + crtDate +
        ", crtTime=" + crtTime +
        ", crtUser=" + crtUser +
        ", flagEnable=" + flagEnable +
        ", id=" + id +
        ", paramCode=" + paramCode +
        ", paramDesc=" + paramDesc +
        ", paramName=" + paramName +
        ", propCode=" + propCode +
        ", propName=" + propName +
        ", updateDate=" + updateDate +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
