package com.huateng.qrcode.common.enums;

import com.huateng.qrcode.common.model.IdentityQrcode;

/**
 * 类别域和二维码类型关联映射表
 */
public enum ActionScopeConfigEnums {

    //系统类二维码解析
    IDENITTY_QRCODE("1", IdentityQrcode.class);

    private String scopeCode;

    private Class clazz;

    public void setScopeCode(String scopeCode) {
        this.scopeCode = scopeCode;
    }

    public String getScopeCode() {
        return scopeCode;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    ActionScopeConfigEnums(String scopeCode, Class clazz) {
        this.scopeCode = scopeCode;
        this.clazz = clazz;
    }

    public static Class getByScopeCode(String serviceCode) {
        for (ActionScopeConfigEnums enums : values()) {
            if (serviceCode.equals(enums.getScopeCode())) {
                return enums.getClazz();
            }
        }

        return null;
    }
}
