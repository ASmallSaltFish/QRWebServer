package com.huateng.qrcode.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 二维码模块表
 * </p>
 *
 * @author auto generator
 * @since 2018-11-21
 */
@TableName("TBL_QR_MODULE")
public class QrModule implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @TableField("QR_MOD_ID")
    private String qrModId;

    //二维码模版编码：二维码版本+二维码生成方式+时效+类别域+保留位+请求系统+行业类型+用途+使用场景（15位）
    @TableField("QR_MOD_CODE")
    private String qrModCode;

    //二维码版本编号
    @TableField("QRCODE_VERSION")
    private String qrcodeVersion;

    //编码原理（1-QR Code，。。。）
    @TableField("ENCODE_MODE")
    private String encodeMode;

    //二维码生成方式（1-在线实时生成，2-在线定时生成，3-离线定时生成，4-离线定时生成）
    @TableField("GENERATION_MODE")
    private String generationMode;

    //时效（1-动态二维码，2-静态二维码）
    @TableField("EXPIRY_DATE")
    private String expiryDate;

    //类别域（1-开放式条码资金类，2-开放式条码非资金类，3-系统内条码资金类，4-系统内条码非资金类，5-公共条码资金类，6-公共条码非资金类）
    @TableField("ACTION_SCOPE")
    private String actionScope;

    //读取方式（1-主读，2-被读，3-主被读）
    @TableField("READ_MODE")
    private String readMode;

    //风险等级（1-低，2-中低，3-中，4-中高，5-高）
    @TableField("RISK_LEVEL")
    private String riskLevel;

    //保留位（0-默认）
    @TableField("BACK_CODE")
    private String backCode;

    //加密标识（1-不加密(默认)）
    @TableField("ENTRY_FLAG")
    private String entryFlag;

    //密钥版本（0-默认）
    @TableField("KEY_VERSION")
    private String keyVersion;

    //请求系统（4位）
    @TableField("REQ_SYS")
    private String reqSys;

    //行业类型（3位）
    @TableField("INDUSTRY_APP")
    private String industryApp;

    //用途（1位）（1-识别类，2-展示类，3-支付类，4-运营类，5-排队类）
    @TableField("USE_TYPE")
    private String useType;

    //使用场景（2位）
    @TableField("USE_SCENE")
    private String useScene;

    /************************/

    @TableField("ORG_ID")
    private String orgId;

    //二维码模板名称
    @TableField("QR_MOD_NAME")
    private String qrModName;

    @TableField("QR_URL")
    private String qrUrl;

    //模板状态
    @TableField("CUS_MOD_STATUS")
    private String cusModStatus;

    //时效类型（1-动态二维码，2-静态二维码）
    @TableField("EXPIRY_TYPE")
    private String expiryType;



    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("CRT_OPER_ID")
    private String crtOperId;

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

    public String getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(String expiryType) {
        this.expiryType = expiryType;
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
                ", expiryType=" + expiryType +
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
