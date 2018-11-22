package com.huateng.qrcode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";


    /**
     * 获取当前时间（HHmmSS格式）
     */
    public static String getCurrentTimeStr() {
        String dateTimeStr = getCurrentDateTimeStr();
        return dateTimeStr.substring(8);
    }


    /**
     * 获取当前日期（yyyyMMdd格式）
     */
    public static String getCurrentDateStr() {
        String dateTimeStr = getCurrentDateTimeStr();
        return dateTimeStr.substring(0, 8);
    }


    /**
     * 获取当前日期和时间（yyyyMMddHHmmss格式）
     */
    public static String getCurrentDateTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 根据格式，格式化当前时间，返回对应格式的时间字符串
     */
    public static String getCurrentDateTimeStr(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}



