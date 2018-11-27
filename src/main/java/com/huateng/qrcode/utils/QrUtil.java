package com.huateng.qrcode.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * 二维码生成和校验常用工具类
 *
 * @author qinyupeng
 * @since 2018-11-23 09:09:49
 */
public class QrUtil {

    /**
     * 判断二维码是否失效（参数为yyyyMMddHHmmss格式时间字符串）
     *
     * @param expiryDateTime 时间格式字符串
     * @return 失效返回true，否则返回false
     */
    public static boolean isQrExpire(String expiryDateTime) throws ParseException {
        Date expireDate = DateUtil.parserDate(expiryDateTime);
        return new Date().after(expireDate);
    }


    /**
     * 根据二进制字符串，获取校验位
     *
     * @param binaryString 二进制字符串
     * @return 二进制字符串中1的个数为偶数，返回校验位"1"，为奇数返回校验为"0"
     */
    public static String getCheckFlag(String binaryString) {
        int cnt = 0;
        for (char ch : binaryString.toCharArray()) {
            if (ch == '1') {
                cnt++;
            }
        }

        return cnt % 2 == 0 ? "1" : "0";
    }
}
