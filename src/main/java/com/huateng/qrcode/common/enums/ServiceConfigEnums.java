package com.huateng.qrcode.common.enums;

import com.huateng.qrcode.qrserver.manager.impl.QrGenerateManager;
import com.huateng.qrcode.qrserver.manager.impl.QrParserManager;
import com.huateng.qrcode.qrserver.manager.QrServerManager;

/**
 * 定义服务码和处理接口类枚举（根据配置添加）
 *
 * @author qinyupeng
 * @since 2018-11-16 14:04:34
 */
public enum ServiceConfigEnums {
    //服务码对应处理类
    GEN_QRCODE("001", QrGenerateManager.class),
    PARSER_QRCODE("002", QrParserManager.class);


    private String serviceCode;

    private Class clazz;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    ServiceConfigEnums(String serviceCode, Class<? extends QrServerManager> clazz) {
        this.serviceCode = serviceCode;
        this.clazz = clazz;
    }

    public static Class getByServiceCode(String serviceCode) {
        for (ServiceConfigEnums enums : values()) {
            if (serviceCode.equals(enums.getServiceCode())) {
                return enums.getClazz();
            }
        }

        return null;
    }
}
