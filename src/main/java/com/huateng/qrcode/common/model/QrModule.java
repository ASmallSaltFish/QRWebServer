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
@TableName("TBL_QR_MODULE")
public class QrModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ACTION_SCOPE")
    private String actionScope;
    @TableField("BACK_CODE")
    private String backCode;
    @TableField("CREATE_TIME")
    private String createTime;
    @TableField("CRT_OPER_ID")
    private String crtOperId;
    @TableField("CUS_MOD_STATUS")
    private String cusModStatus;
    @TableField("ENCODE_MODE")
    private String encodeMode;
    @TableField("ENTRY_FLAG")
    private String entryFlag;
    @TableField("EXPIRE_TYPE")
    private String expireType;
    @TableField("EXPIRY_DATE")
    private String expiryDate;
    @TableField("GENERATION_MODE")
    private String generationMode;
    @TableField("INDUSTRY_APP")
    private String industryApp;
    @TableField("KEY_VERSION")
    private String keyVersion;
    @TableField("LAST_UPD_OPER_ID")
    private String lastUpdOperId;
    @TableField("LAST_UPD_TIME")
    private String lastUpdTime;
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
    @TableField("QRCODE_VERSION")
    private String qrcodeVersion;
    @TableField("QR_MOD_CODE")
    private String qrModCode;
    @TableField("QR_MOD_ID")
    private String qrModId;
    @TableField("QR_MOD_NAME")
    private String qrModName;
    @TableField("QR_URL")
    private String qrUrl;
    @TableField("READ_MODE")
    private String readMode;
    @TableField("REQ_SYS")
    private String reqSys;
    @TableField("RISK_LEVEL")
    private String riskLevel;
    @TableField("USE_SCENE")
    private String useScene;
    @TableField("USE_TYPE")
    private String useType;


    public String getActionScope() {
        return actionScope;
    }

    public void setActionScope(String actionScope) {
        this.actionScope = actionScope;
    }

    public String getBackCode() {
        return backCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCrtOperId() {
        return crtOperId;
    }

    public void setCrtOperId(String crtOperId) {
        this.crtOperId = crtOperId;
    }

    public String getCusModStatus() {
        return cusModStatus;
    }

    public void setCusModStatus(String cusModStatus) {
        this.cusModStatus = cusModStatus;
    }

    public String getEncodeMode() {
        return encodeMode;
    }

    public void setEncodeMode(String encodeMode) {
        this.encodeMode = encodeMode;
    }

    public String getEntryFlag() {
        return entryFlag;
    }

    public void setEntryFlag(String entryFlag) {
        this.entryFlag = entryFlag;
    }

    public String getExpireType() {
        return expireType;
    }

    public void setExpireType(String expireType) {
        this.expireType = expireType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getGenerationMode() {
        return generationMode;
    }

    public void setGenerationMode(String generationMode) {
        this.generationMode = generationMode;
    }

    public String getIndustryApp() {
        return industryApp;
    }

    public void setIndustryApp(String industryApp) {
        this.industryApp = industryApp;
    }

    public String getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
    }

    public String getLastUpdOperId() {
        return lastUpdOperId;
    }

    public void setLastUpdOperId(String lastUpdOperId) {
        this.lastUpdOperId = lastUpdOperId;
    }

    public String getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(String lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
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

    public String getQrcodeVersion() {
        return qrcodeVersion;
    }

    public void setQrcodeVersion(String qrcodeVersion) {
        this.qrcodeVersion = qrcodeVersion;
    }

    public String getQrModCode() {
        return qrModCode;
    }

    public void setQrModCode(String qrModCode) {
        this.qrModCode = qrModCode;
    }

    public String getQrModId() {
        return qrModId;
    }

    public void setQrModId(String qrModId) {
        this.qrModId = qrModId;
    }

    public String getQrModName() {
        return qrModName;
    }

    public void setQrModName(String qrModName) {
        this.qrModName = qrModName;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public String getReadMode() {
        return readMode;
    }

    public void setReadMode(String readMode) {
        this.readMode = readMode;
    }

    public String getReqSys() {
        return reqSys;
    }

    public void setReqSys(String reqSys) {
        this.reqSys = reqSys;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getUseScene() {
        return useScene;
    }

    public void setUseScene(String useScene) {
        this.useScene = useScene;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    @Override
    public String toString() {
        return "QrModule{" +
        "actionScope=" + actionScope +
        ", backCode=" + backCode +
        ", createTime=" + createTime +
        ", crtOperId=" + crtOperId +
        ", cusModStatus=" + cusModStatus +
        ", encodeMode=" + encodeMode +
        ", entryFlag=" + entryFlag +
        ", expireType=" + expireType +
        ", expiryDate=" + expiryDate +
        ", generationMode=" + generationMode +
        ", industryApp=" + industryApp +
        ", keyVersion=" + keyVersion +
        ", lastUpdOperId=" + lastUpdOperId +
        ", lastUpdTime=" + lastUpdTime +
        ", misc1=" + misc1 +
        ", misc2=" + misc2 +
        ", misc3=" + misc3 +
        ", misc4=" + misc4 +
        ", orgId=" + orgId +
        ", qrcodeVersion=" + qrcodeVersion +
        ", qrModCode=" + qrModCode +
        ", qrModId=" + qrModId +
        ", qrModName=" + qrModName +
        ", qrUrl=" + qrUrl +
        ", readMode=" + readMode +
        ", reqSys=" + reqSys +
        ", riskLevel=" + riskLevel +
        ", useScene=" + useScene +
        ", useType=" + useType +
        "}";
    }
}
