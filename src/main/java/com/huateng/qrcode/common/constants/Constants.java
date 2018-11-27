package com.huateng.qrcode.common.constants;

public class Constants {

    /************序列号常量***************/
    public static final String SEQ_KEY = "QR_CODE_GENERATE_KEY";

    public static final String TOKEN_ID = "QR_TOKEN_GENERATOR";

    /************用途常量***************/
    //识别类
    public static final String IDENTIFY_ACTION_ACOPE = "1";
    //展示类
    public static final String DISPLAY_ACTION_ACOPE = "2";
    //支付类
    public static final String PAYMENT_ACTION_ACOPE = "3";
    //运营类
    public static final String OPERATION_ACTION_ACOPE = "4";
    //排队类
    public static final String QUEUE_ACTION_ACOPE = "5";

    /************日期格式化常量***************/
    //无符号日期时间
    public static final String DATE_TIME_PATTERN_DEFAULT = "yyyyMMddHHmmss";
    //横杆冒号日期时间
    public static final String DATE_TIME_PATTERN_CC = "yyyy-MM-dd HH:mm:ss";
    //斜杆冒号日期时间
    public static final String DATE_TIME_PATTERN_DC = "yyyy/MM/dd HH:mm:ss";
    //无符号日期
    public static final String DATE_PATTERN_DEFAULT = "yyyyMMdd";
    //横杆日期
    public static final String DATE_PATTERN_CROSS = "yyyy-MM-dd";
    //斜杆日期
    public static final String DATE_PATTERN_DIAGONAL = "yyyy/MM/dd";

}
