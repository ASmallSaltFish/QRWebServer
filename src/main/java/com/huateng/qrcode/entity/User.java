package com.huateng.qrcode.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 *   实体类
 * </p>
 *
 * @author auto generator
 * @since 2018-11-14
 */
@TableName("T_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @TableField("USER_ID")
    private String userId;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("TYPE")
    private String type;

    @TableField("USER_NAME")
    private String userName;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "createTime=" + createTime +
                ", type=" + type +
                ", userId=" + userId +
                ", userName=" + userName +
                "}";
    }
}
