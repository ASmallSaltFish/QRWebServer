package com.huateng.qrcode.common.constants;

public class Constants {


    /************序列号常量***************/
    public static final String SEQ_KEY = "QR_CODE_GENERATE_KEY";
    public static final String TOKEN_ID = "QR_TOKEN_GENERATOR";


    /***************黑名单类型**************/
    //请求系统
    public static final String BLACK_TYPE_SYS = "SYS";
    //二维码模版
    public static final String BLACK_TYPE_MODULE = "MODULE";
    //二维码
    public static final String BLACK_TYPE_QRCODE = "QRCODE";


    /****************http服务二维码解析*********************/
    //微信
    public static final String HTTP_REQ_SYS_WX = "WX";
    //支付宝
    public static final String HTTP_REQ_SYS_ALIPAY = "ALIPAY";
    //请求参数名，二维码码串
    public static final String REQ_PARAM_QR_CODE = "qrCode";
    //扫码客户端系统（微信、支付宝）
    public static final String HTTP_REQ_SYS = "httpReqSys";
    //请求接收日期
    public static final String RECEIVE_DATE = "receiveDate";
    //请求接收时间
    public static final String RECEIVE_TIME = "receiveTime";


    /************系统类二维码解析*****************/
    //二维码流水编号（待定，需要唯一标识二维码流水号）
    public static final String QR_TXN_ID = "qrTxnId";


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


    /************其他常量***************/
    //保留位默认值
    public static final String RESERVE = "0";

}
