package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 序列服务信息表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-22
 */
@TableName("TBL_SEQ_INFO")
public class SeqInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SEQ_KEY")
    @TableField("SEQ_KEY")
    private String seqKey;

    @TableField("SYS_ID")
    private String sysId;

    @TableField("UP_LIMIT")
    private BigDecimal upLimit;

    @TableField("DOWN_LIMIT")
    private BigDecimal downLimit;

    @TableField("INC_RULE_VALUE")
    private BigDecimal incRuleValue;

    @TableField("INIT_VALUE")
    private BigDecimal initValue;

    @TableField("MAX_VALUE")
    private BigDecimal maxValue;

    @TableField("RULE_DEF")
    private String ruleDef;

    @TableField("MISC1")
    private String misc1;

    @TableField("MISC2")
    private String misc2;

    @TableField("MISC3")
    private String misc3;

    @TableField("MISC4")
    private String misc4;


    public BigDecimal getDownLimit() {
        return downLimit;
    }

    public void setDownLimit(BigDecimal downLimit) {
        this.downLimit = downLimit;
    }

    public BigDecimal getIncRuleValue() {
        return incRuleValue;
    }

    public void setIncRuleValue(BigDecimal incRuleValue) {
        this.incRuleValue = incRuleValue;
    }

    public BigDecimal getInitValue() {
        return initValue;
    }

    public void setInitValue(BigDecimal initValue) {
        this.initValue = initValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
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

    public String getRuleDef() {
        return ruleDef;
    }

    public void setRuleDef(String ruleDef) {
        this.ruleDef = ruleDef;
    }

    public String getSeqKey() {
        return seqKey;
    }

    public void setSeqKey(String seqKey) {
        this.seqKey = seqKey;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public BigDecimal getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(BigDecimal upLimit) {
        this.upLimit = upLimit;
    }

    @Override
    public String toString() {
        return "SeqInfo{" +
                "downLimit=" + downLimit +
                ", incRuleValue=" + incRuleValue +
                ", initValue=" + initValue +
                ", maxValue=" + maxValue +
                ", misc1=" + misc1 +
                ", misc2=" + misc2 +
                ", misc3=" + misc3 +
                ", misc4=" + misc4 +
                ", ruleDef=" + ruleDef +
                ", seqKey=" + seqKey +
                ", sysId=" + sysId +
                ", upLimit=" + upLimit +
                "}";
    }
}
