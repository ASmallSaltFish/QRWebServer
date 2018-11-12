package com.huateng.test.encrypt;

import com.huateng.qrcode.utils.AesEncryptUtil;
import com.huateng.qrcode.utils.RsaEncryptUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


/**
 * AES算法加密和解密（对称加密算法，相比DES加密更安全）
 *
 * @author qinyupeng
 * @since 2018-11-12 15:06:15
 */
public class TestEncrypt {

    /**
     * 测试AesEncryptUtil类
     */
    @Test
    public void TestAES() {
        String data = "this is data";
        String password = "AES";
        String encryptData = AesEncryptUtil.encrypt(data, password);
        System.out.println(data + " 加密后字符串为：" + encryptData);

        String decryData = AesEncryptUtil.decrypt(encryptData, password);
        System.out.println("解密后明文数据为：" + decryData);
    }


    /**
     * 测试RsaEncryptUtil类
     */
    @Test
    public void TestRSA() throws Exception {
        System.out.println("--------测试使用RSA加密算法生成公钥、私钥-------");
        KeyPair keyPair = RsaEncryptUtil.generateKeyPair(1024);
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("私钥：" + Base64.encodeBase64String(privateKey.getEncoded()));
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("公钥：" + Base64.encodeBase64String(publicKey.getEncoded()));

        System.out.println("------测试公钥、私钥字符串还原公钥私钥-------");
        privateKey = RsaEncryptUtil.getPrivateKeyFromStr(Base64.encodeBase64String(privateKey.getEncoded()));
        System.out.println("私钥：" + Base64.encodeBase64String(privateKey.getEncoded()));

        publicKey = RsaEncryptUtil.getPublicKeyFromStr(Base64.encodeBase64String(publicKey.getEncoded()));
        System.out.println("公钥：" + Base64.encodeBase64String(publicKey.getEncoded()));

        System.out.println("-------公钥加密，私钥解密--------");
        //加密内容
        String content = "this is data";

        //公钥加密
        byte[] encryptData = RsaEncryptUtil.encrypt(publicKey, content.getBytes());
        //私钥解密
        byte[] decryptData = RsaEncryptUtil.decrypt(privateKey, encryptData);
        System.out.println("私钥解密：" + new String(decryptData));
    }

    @Test
    public void TestAes() throws Exception {
        //生成Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed("customPassword".getBytes());

        keyGenerator.init(128, random);
        //keyGenerator.init(128, new SecureRandom("seedseedseed".getBytes()));
        //使用上面这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        //Key转换
        Key key = new SecretKeySpec(keyBytes, "AES");

        //加密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encodeResult = cipher.doFinal("this is data".getBytes("UTF-8"));
        System.out.println("AESencode : " + Base64.encodeBase64String(encodeResult));

        //解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeResult = cipher.doFinal(encodeResult);
        System.out.println("AESdecode : " + new String(decodeResult));
    }
}
