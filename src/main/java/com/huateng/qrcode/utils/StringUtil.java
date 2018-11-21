package com.huateng.qrcode.utils;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 字符串常用工具类
 *
 * @author qinyupeng
 * @since 2018-11-21 10:06:45
 */
public class StringUtil {

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);


    /**
     * 判断多个字符串参数是否为空，为空返回true，不为空返回false
     *
     * @param params 参数值
     * @return 有一个参数值包含空，则返回true，都不为空，返回true
     */
    public static boolean hasEmptyMultipleStr(String... params) {
        for (String param : params) {
            if (null == param || "".equals(param.trim()) || "null".equals(param.trim()) || param.trim().length() == 0) {
                logger.debug("参数值" + param + "为空!");
                return true;
            }
        }

        return false;
    }


    /**
     * 判断map集合中是否有为空的参数
     *
     * @param paramMap 参数集合，key为参数名，value为参数值
     * @return 参数集合中包含为空的参数，返回true，都不为空，返回true
     */
    public static boolean hasEmptyParam(Map<String, String> paramMap) {
        if (MapUtils.isEmpty(paramMap)) {
            logger.debug("参数paramMap集合为空！");
            return true;
        }

        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();
            if (null == paramValue || "".equals(paramValue.trim()) || "null".equals(paramValue.trim()) || paramValue.trim().length() == 0) {
                logger.debug("参数" + paramName + "为空!");
                return true;
            }
        }

        return false;
    }


    /**
     * 空字符串对象转空字符串
     */
    public static String null2String(String str) {
        return str == null ? "" : str;
    }
}
