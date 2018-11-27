package com.huateng.qrcode.utils;

import com.huateng.qrcode.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
     * 获取日期YYMMdd格式
     */
    public static String getYYMMDD() {
        LocalDate toDay = LocalDate.now();
        String year = String.valueOf(toDay.getYear()).substring(2);
        String month = String.valueOf(toDay.getMonth().getValue());
        String day = String.valueOf(toDay.getDayOfMonth());
        return year + month + day;
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

    /**
     * 根据时间字符串，解析为date对象（yyyyMMddHHmmss格式）
     */
    public static Date parserToDate(String dateTimeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 日期相加减秒数
     *
     * @param date   如果为Null，则为当前时间
     * @param second 加减秒数
     * @return
     * @throws ParseException
     */
    public static Date secondAdd(Date date, int second) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        if (date == null) {
            date = new Date();
        }
        date = sdf.parse(sdf.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 时间格式化成字符串
     *
     * @param date    Date
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) throws ParseException {
        if (StringUtils.isBlank(pattern)) {
            pattern = Constants.DATE_PATTERN_DEFAULT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}



