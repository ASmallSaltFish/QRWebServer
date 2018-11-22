package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 *    系统参数表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_SYS_PARAM")
public class SysParam implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @TableField("ORG_ID")
    private String orgId;
    @TableField("PARAM_CODE")
    private String paramCode;
    @TableField("PARAM_DESC")
    private String paramDesc;
    @TableField("PARAM_ID")
    private String paramId;
    @TableField("PARAM_VALUE")
    private String paramValue;
    @TableField("SORT_ORDER")
    private String sortOrder;
    @TableField("TYPE_CODE")
    private String typeCode;
    @TableField("TYPE_NAME")
    private String typeName;
    @TableField("UPDATE_USER")
    private String updateUser;
    @TableField("UPD_DATE")
    private String updDate;
    @TableField("UPD_TIME")
    private String updTime;
    @TableField("VALID_FLAG")
    private String validFlag;


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

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    @Override
    public String toString() {
        return "SysParam{" +
        "crtDate=" + crtDate +
        ", crtTime=" + crtTime +
        ", crtUser=" + crtUser +
        ", misc1=" + misc1 +
        ", misc2=" + misc2 +
        ", misc3=" + misc3 +
        ", misc4=" + misc4 +
        ", orgId=" + orgId +
        ", paramCode=" + paramCode +
        ", paramDesc=" + paramDesc +
        ", paramId=" + paramId +
        ", paramValue=" + paramValue +
        ", sortOrder=" + sortOrder +
        ", typeCode=" + typeCode +
        ", typeName=" + typeName +
        ", updateUser=" + updateUser +
        ", updDate=" + updDate +
        ", updTime=" + updTime +
        ", validFlag=" + validFlag +
        "}";
    }
}
