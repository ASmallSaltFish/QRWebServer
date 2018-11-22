package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 密钥管理表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_KEY_LIST")
public class KeyListInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CRT_DATE")
    private String crtDate;
    @TableField("CRT_TIME")
    private String crtTime;
    @TableField("CRT_USER")
    private String crtUser;
    @TableField("ID")
    private String id;
    @TableField("IS_IN_USE")
    private String isInUse;
    @TableField("KEYT")
    private String keyt;
    @TableField("KEYT_METHOD")
    private String keytMethod;
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
    @TableField("UPDATE_USER")
    private String updateUser;
    @TableField("UPD_DATE")
    private String updDate;
    @TableField("VERSION")
    private String version;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(String isInUse) {
        this.isInUse = isInUse;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public String getKeytMethod() {
        return keytMethod;
    }

    public void setKeytMethod(String keytMethod) {
        this.keytMethod = keytMethod;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "KeyList{" +
        "crtDate=" + crtDate +
        ", crtTime=" + crtTime +
        ", crtUser=" + crtUser +
        ", id=" + id +
        ", isInUse=" + isInUse +
        ", keyt=" + keyt +
        ", keytMethod=" + keytMethod +
        ", misc1=" + misc1 +
        ", misc2=" + misc2 +
        ", misc3=" + misc3 +
        ", misc4=" + misc4 +
        ", orgId=" + orgId +
        ", updateUser=" + updateUser +
        ", updDate=" + updDate +
        ", version=" + version +
        "}";
    }
}
