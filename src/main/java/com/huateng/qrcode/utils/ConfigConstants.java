package com.huateng.qrcode.utils;

import java.util.ResourceBundle;

public class ConfigConstants {

    private static String CONSTANTS_FILE = "config";

    //读取classpath下面"config.xml"属性文件（jdk rt.jar中提供）
    private static ResourceBundle BUNDLE = ResourceBundle.getBundle(CONSTANTS_FILE);

    /**
     * 获取config.properties属性文件配置
     *
     * @param key 属性名
     * @return 返回key对应的属性值
     */
    public static String getParam(String key) {
        return BUNDLE.getString(key);
    }
}
