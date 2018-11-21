package com.huateng.qrcode.common.enums;

import com.huateng.qrcode.qrserver.impl.*;
import com.huateng.qrcode.qrserver.QrServerManager;

/**
 * 定义服务码和处理接口类枚举（根据配置添加）
 *
 * @author qinyupeng
 * @since 2018-11-16 14:04:34
 */
public enum ServiceConfigEnums {
    //系统类二维码解析
    SYS_PARSER_QRCODE("001", SysQrParserManagerImpl.class),
    Disting_QRCODE("003", DistingQrGenerateImpl.class);


    private String serviceCode;

    private Class<? extends QrServerManager> clazz;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Class<? extends QrServerManager> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends QrServerManager> clazz) {
        this.clazz = clazz;
    }

    ServiceConfigEnums(String serviceCode, Class<? extends QrServerManager> clazz) {
        this.serviceCode = serviceCode;
        this.clazz = clazz;
    }

    public static Class<? extends QrServerManager> getByServiceCode(String serviceCode) {
        for (ServiceConfigEnums enums : values()) {
            if (serviceCode.equals(enums.getServiceCode())) {
                return enums.getClazz();
            }
        }

        return null;
    }
}
