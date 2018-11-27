package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 黑名单表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_BLACK_LIST")
public class BlackListInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @TableId("BLACK_ID")
    @TableField("BLACK_ID")
    private String blackId;

    //黑名单描述
    @TableField("BLACK_DESC")
    private String blackDesc;

    //关联编号
    @TableField("BLACK_NO")
    private String blackNo;

    //黑名单类型（SYS-系统黑名单, QRCODE-二维码黑名单，MODULE-模板黑名单
    @TableField("BLACK_TYPE")
    private String blackType;

    //是否可用
    @TableField("IS_USE")
    private String isUse;

    //失效时间
    @TableField("EXPIRY_TIME")
    private String expiryTime;

    //创建时间
    @TableField("CRT_TIME")
    private String crtTime;

    //创建人
    @TableField("CRT_USER")
    private String crtUser;

    //更新时间
    @TableField("UPDATE_TIME")
    private String updateTime;

    //更新人员
    @TableField("UPDATE_USER")
    private String updateUser;

    @TableField("MISC1")
    private String misc1;

    @TableField("MISC2")
    private String misc2;

    @TableField("MISC3")
    private String misc3;

    @TableField("MISC4")
    private String misc4;

    public String getBlackId() {
        return blackId;
    }

    public void setBlackId(String blackId) {
        this.blackId = blackId;
    }

    public String getBlackDesc() {
        return blackDesc;
    }

    public void setBlackDesc(String blackDesc) {
        this.blackDesc = blackDesc;
    }

    public String getBlackNo() {
        return blackNo;
    }

    public void setBlackNo(String blackNo) {
        this.blackNo = blackNo;
    }

    public String getBlackType() {
        return blackType;
    }

    public void setBlackType(String blackType) {
        this.blackType = blackType;
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

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
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
        return "BlackListInfo{" +
                "blackId='" + blackId + '\'' +
                ", blackDesc='" + blackDesc + '\'' +
                ", blackNo='" + blackNo + '\'' +
                ", blackType='" + blackType + '\'' +
                ", isUse='" + isUse + '\'' +
                ", expiryTime='" + expiryTime + '\'' +
                ", crtTime='" + crtTime + '\'' +
                ", crtUser='" + crtUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", misc1='" + misc1 + '\'' +
                ", misc2='" + misc2 + '\'' +
                ", misc3='" + misc3 + '\'' +
                ", misc4='" + misc4 + '\'' +
                '}';
    }
}
