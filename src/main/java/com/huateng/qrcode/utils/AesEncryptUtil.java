package com.huateng.qrcode.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * AES加密工具类（对称式加解密）
 *
 * @author qinyupeng
 * @since 2018-11-12 15:15:25
 */
public class AesEncryptUtil {

    private static Logger logger = LoggerFactory.getLogger(AesEncryptUtil.class);

    //默认字符集
    private static final String DEFAULT_CHARSET = "UTF-8";
    //加密算法名
    private static final String KEY_ALGORITHM = "AES";
    //默认的加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * AES 加密操作
     *
     * @param data     待加密的明文
     * @param password 自定义加密密码
     * @return 返回使用AES加密，Base64编码后的加密数据
     */
    public static String encrypt(String data, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //加密
            cipher.init(Cipher.ENCRYPT_MODE, getSecretSpecKey(KEY_ALGORITHM, password));
            byte[] encodeResult = cipher.doFinal(data.getBytes(DEFAULT_CHARSET));
            return Base64.encodeBase64String(encodeResult);
        } catch (Exception e) {
            logger.error("AES加密失败", e);
        }

        return null;
    }


    /**
     * AES 解密操作
     *
     * @param encodeData 使用AES加密，Base64编码后的密文
     * @param password   自定义加密密码
     * @return 返回解密后的明文数据
     */
    public static String decrypt(String encodeData, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretSpecKey(KEY_ALGORITHM, password));
            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(encodeData));
            return new String(result, DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("AES解密失败", e);
        }

        return null;
    }


    //生成加解密key
    private static Key getSecretSpecKey(String algorithm, String password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        //加密算法长度以及自定义加密字段
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes(DEFAULT_CHARSET));
        keyGenerator.init(128, random);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        return new SecretKeySpec(keyBytes, algorithm);
    }
}
