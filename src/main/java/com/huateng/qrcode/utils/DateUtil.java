package com.huateng.qrcode.utils;

import com.huateng.qrcode.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获取当前日期和时间（yyyyMMddHHmmss格式）
     */
    public static String formatCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_PATTERN_DEFAULT);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间（HHmmSS格式）
     */
    public static String formatCurrentTime() {
        String dateTimeStr = formatCurrentDateTime();
        return dateTimeStr.substring(8);
    }


    /**
     * 获取当前日期（yyyyMMdd格式）
     */
    public static String formatCurrentDate() {
        String dateTimeStr = formatCurrentDateTime();
        return dateTimeStr.substring(0, 8);
    }


    /**
     * 获取日期YYMMdd格式
     */
    public static String getYYMMDD() {
        String currentDateStr = formatCurrentDate();
        return currentDateStr.substring(2);
    }


    /**
     * 将yyyyMMddHHmmss格式字符串转换为Date对象
     *
     * @param dateTime 时间格式字符串
     * @return 返回Date对象
     */
    public static Date parserDate(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_PATTERN_DEFAULT);
        return sdf.parse(dateTime);
    }

    /**
     * 指定时间字符串格式匹配，将时间字符串转化为Date对象
     *
     * @param dateTime 时间字符串
     * @param format   字符串转换格式
     * @return 返回Date对象
     */
    public static Date parserDate(String dateTime, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            format = Constants.DATE_TIME_PATTERN_DEFAULT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateTime);
    }


    /**
     * 将指定date对象，转换为yyyyMMddHHmmss格式时间字符串
     *
     * @param date Date对象
     * @return 返回yyyyMMddHhmmss格式字符串
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_PATTERN_DEFAULT);
        return sdf.format(date);
    }

    /**
     * 将指定Date对象，装换为yyyyMMdd格式日期字符串
     *
     * @param date Date对象
     * @return 返回yyyyMMdd日期字符串
     */
    public static String formatDate(Date date) {
        String dateTime = formatDateTime(date);
        return dateTime.substring(0, 8);
    }

    /**
     * 将指定Date对象，装换为HHmmss格式时间字符串
     *
     * @param date Date对象
     * @return 返回HHmmss时间字符串
     */
    public static String formatTime(Date date) {
        String dateTime = formatDateTime(date);
        return dateTime.substring(8, 14);
    }

    /**
     * 日期加减
     * @param date 如果为Null，则为当前时间
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     * @throws ParseException
     */
    public static Date timeAdd(Date date, Integer year, Integer month, Integer day, Integer hour,
                                 Integer minute, Integer second) throws ParseException {
        //SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_PATTERN_CC);
        if (date == null) {
            date = new Date();
        }

        //date = sdf.parse(sdf.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, minute);
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

    public static void main(String[] args) {
        System.out.println("$1$8$3$4$4$7$".split("\\$"));
    }
}



