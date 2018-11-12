package com.huateng.qrcode.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA实现实现加密解密工具类（非对称式加解密）
 *
 * @author qinyupeng
 * @since 2018-11-12 15:14:38
 */
public class RsaEncryptUtil {
    private static Logger logger = LoggerFactory.getLogger(RsaEncryptUtil.class);

    private static String KEY_ALGORITHM = "RSA";

    //private static int KEY_SIZE = 1024;

    /**
     * 生成RAS密钥对（包含公钥和私钥）
     * 使用rsa加密算法，需要设置所占字节大小
     */
    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }


    /**
     * 通过公钥字符串获取公钥对象
     *
     * @param publicKeyStr 公钥经过Base64编码后生成的字符串
     * @return 返回公钥对象
     */
    public static RSAPublicKey getPublicKeyFromStr(String publicKeyStr) {
        byte[] buff = Base64.decodeBase64(publicKeyStr);
        PublicKey publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //私钥使用的keySpec和公钥不一样
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(buff));
        } catch (NoSuchAlgorithmException e) {
            logger.error("指定算法不存在~", e);
        } catch (InvalidKeySpecException e) {
            logger.error("公钥非法~", e);
        }

        return (RSAPublicKey) publicKey;
    }

    /**
     * 通过私钥字符串获取私钥对象
     *
     * @param privateKeyStr 私钥字符串
     */
    public static RSAPrivateKey getPrivateKeyFromStr(String privateKeyStr) {
        byte[] buff = Base64.decodeBase64(privateKeyStr);
        PrivateKey privateKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            //私钥使用的keySpec和公钥不一样
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(buff));
        } catch (NoSuchAlgorithmException e) {
            logger.error("指定算法不存在~", e);
        } catch (InvalidKeySpecException e) {
            logger.error("公钥非法~", e);
        }

        return (RSAPrivateKey) privateKey;
    }


    /**
     * 使用公钥加密，获得加密后的字节数组
     *
     * @param publicKey     公钥
     * @param plainTextData 加密字节
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] plainTextData) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainTextData);
    }


    /**
     * 使用私钥解密
     *
     * @param privateKey 私钥
     * @param cipherData 加密后的明文
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] cipherData) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(cipherData);
    }

}