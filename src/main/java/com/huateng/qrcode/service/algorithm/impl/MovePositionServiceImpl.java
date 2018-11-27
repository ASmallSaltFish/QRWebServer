package com.huateng.qrcode.service.algorithm.impl;

import com.huateng.qrcode.common.exception.QrcException;
import com.huateng.qrcode.service.algorithm.MovePositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @class: MovePositionServiceImpl
 * @title: 移位算法实现类
 * @desc:
 * @author: xuzhangsheng
 * @date: 2018年11月21日 下午14:16:53
 * @since: 1.0.0
 */
@Service(value = "MovePositionServiceImpl")
public class MovePositionServiceImpl implements MovePositionService {

    private static final Logger logger = LoggerFactory.getLogger(MovePositionServiceImpl.class);

    /**
     * 移位算法加密
     *
     * @param ewmData
     * @param n
     * @throws QrcException
     */
    @Override
    public String movePosition(String ewmData, int n) throws QrcException {
        logger.info("移位算法开始，入参：" + ewmData);
        char[] ewmDataChar = ewmData.toCharArray();
        char[] encryptionData = forMovePosition(ewmDataChar, n, "encryption");
        String ewmStringData = String.valueOf(encryptionData);
        logger.info("移位算法结束，出参：" + ewmStringData);
        return ewmStringData;
    }

    /**
     * 移位算法解密
     *
     * @param ewaDataCipher
     * @param n
     * @return
     * @throws QrcException
     */
    public String decryptMovePosition(String ewaDataCipher, int n) throws QrcException {
        logger.info("移位算法解密开始，入参：" + ewaDataCipher);
        char[] ewaDataCipherChar = ewaDataCipher.toCharArray();
        char[] decryptData = forMovePosition(ewaDataCipherChar, n, "decrypt");
        logger.info("移位算法解密结束，出参：" + String.valueOf(decryptData));
        return String.valueOf(decryptData);
    }

    public char[] forMovePosition(char[] ewmData, int n, String flag) throws QrcException {

        int len, d, m, tempint;
        char[] datach, tempchar;
        //获取需加密或解密字段长度
        len = ewmData.length;
        if ((d = len % n) != 0) {
            len = len + n - d;
        }
        tempchar = new char[len];
        m = len / n;
        if ("decrypt".equals(flag)) {
            tempint = n;
            n = m;
            m = tempint;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i * n + j < ewmData.length) {
                    tempchar[i + m * j] = ewmData[i * n + j];
                } else {
                    tempchar[i + m * j] = ' ';
                }
            }
        }
        datach = new char[len];
        for (int i = 0; i < len; i++) {
            datach[i] = tempchar[i];
        }
        return datach;
    }

    public static void main(String[] args) {
        try {
            //String min = movePosition("1a34567890123", 3);
            //decryptMovePosition(min, 3);
        } catch (Exception e) {

        }
    }
}
