package com.huateng.qrcode.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ZXing生成二维码工具类
 * 使用时注意：如果生成带logo的二维码，logo图片尺寸不能太大，最好为二维码尺寸的1/6，避免二维码无法识别。
 *
 * @author QinYupeng
 * @since 2018年10月25日11:17:22
 */
public class QRCodeKit {

    public static final Logger logger = LoggerFactory.getLogger(QRCodeKit.class);

    //生成二维码默认编码
    private static final String QRCODE_DEFAULT_CHARSET = "UTF-8";
    //生成二维码图片默认高度
    private static final int QRCODE_DEFAULT_HEIGHT = 300;
    //生成二维码图片默认宽度
    private static final int QRCODE_DEFAULT_WIDTH = 300;
    //图片类型
    private static final String IMAGE_TYPE_PNG = "PNG";


    /**
     * 生成二维码
     *
     * @param codeUrl 链接地址
     * @return 生成二维码
     */
    public static BufferedImage createQRCode(String codeUrl) {
        return createQRCode(codeUrl, QRCODE_DEFAULT_CHARSET, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT);
    }


    /**
     * 指定二维码宽、高，生成二维码
     *
     * @param codeUrl 链接地址
     * @param width   宽
     * @param height  高
     * @return 返回指定宽、高二维码，默认字符集为utf-8
     */
    public static BufferedImage createQRCode(String codeUrl, int width, int height) {
        return createQRCode(codeUrl, QRCODE_DEFAULT_CHARSET, width, height);
    }

    /**
     * 指定二维码字符集、宽、高，生成二维码
     *
     * @param codeUrl 链接地址
     * @param charset 字符集
     * @param width   宽
     * @param height  高
     * @return 返回指定字符集、宽、高二维码
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static BufferedImage createQRCode(String codeUrl, String charset, int width, int height) {
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hintMap.put(EncodeHintType.CHARACTER_SET, charset);
        return toQRCodeBufferedImage(codeUrl, charset, width, height, hintMap);
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    private static BufferedImage toQRCodeBufferedImage(String codeUrl, String charset, int width, int height, Map hintMap) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(new String(codeUrl.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height, hintMap);
            return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (Exception e) {
            logger.error("[生成二维码图片失败]" + e.getMessage(), e);
        }

        return null;
    }

    /**
     * 生成带logo的二维码图片
     *
     * @param codeUrl  链接地址
     * @param logoFile logo文件
     * @return 返回带logo文件的二维码图片
     */
    public static BufferedImage createQRCodeWithLogo(String codeUrl, File logoFile) {
        return createQRCodeWithLogo(codeUrl, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT, logoFile);
    }


    /**
     * 指定宽、高、生成带logo的二维码
     *
     * @param codeUrl  链接地址
     * @param width    宽
     * @param height   高
     * @param logoFile logo文件
     * @return 生成指定宽、高带logo的二维码
     */
    public static BufferedImage createQRCodeWithLogo(String codeUrl, int width, int height, File logoFile) {
        return createQRCodeWithLogo(codeUrl, QRCODE_DEFAULT_CHARSET, width, height, logoFile);
    }


    /**
     * 指定宽、高、字符集生成带logo的二维码
     *
     * @param codeUrl  链接地址
     * @param width    宽
     * @param height   高
     * @param logoFile logo文件
     * @return 生成指定宽、高、字符集带logo的二维码
     */
    public static BufferedImage createQRCodeWithLogo(String codeUrl, String charset, int width, int height, File logoFile) {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        //指定容错级别
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //指定字符集
        hintMap.put(EncodeHintType.CHARACTER_SET, charset);
        try {
            //生成二维码
            BufferedImage qrCodeImage = toQRCodeBufferedImage(codeUrl, charset, width, height, hintMap);
            //读取logo图片，按比例压缩
            BufferedImage logoImage = logoImgscale(logoFile, width / 6, height / 6, true);
            //读取logo文件
            int deltaWidth = width - logoImage.getWidth();
            int deltaHeight = height - logoImage.getHeight();

            BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            //将logo文件嵌入到二维码图片中间
            Graphics2D g = (Graphics2D) combinedImage.getGraphics();
            g.drawImage(qrCodeImage, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.drawImage(logoImage, (int) Math.round(deltaWidth >> 1), (int) Math.round(deltaHeight >> 1), null);
            return combinedImage;
        } catch (IOException e) {
            logger.error("[生成带logo的二维码失败！]" + e.getMessage(), e);
        }

        return null;
    }


    /**
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
     *
     * @param logoImageFile logo图片文件
     * @param height        目标高度
     * @param width         目标宽度
     * @param hasFiller     比例不对时是否需要补白：true为补白; false为不补白;
     * @throws IOException
     */
    private static BufferedImage logoImgscale(File logoImageFile, int height, int width, boolean hasFiller) throws IOException {
        double ratio = 0.0; // 缩放比例
        BufferedImage srcImage = ImageIO.read(logoImageFile);
        Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
            if (srcImage.getHeight() > srcImage.getWidth()) {
                ratio = (new Integer(height)).doubleValue()
                        / srcImage.getHeight();
            } else {
                ratio = (new Integer(width)).doubleValue()
                        / srcImage.getWidth();
            }

            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(srcImage, null);
        }

        // 补白
        if (hasFiller) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);
            if (width == destImage.getWidth(null))
                graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2,
                        destImage.getWidth(null), destImage.getHeight(null), Color.white, null);//画图片
            else {
                graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0,
                        destImage.getWidth(null), destImage.getHeight(null), Color.white, null);
            }

            graphic.dispose();
            destImage = image;
        }

        return (BufferedImage) destImage;
    }


    /**
     * 将图片经过Base64编码成字符串
     *
     * @param image 图片
     * @return 将图片转换为Base64编码的字符串
     */
    public static String imageToBase64String(BufferedImage image) {
        return imageToBase64String(image, QRCODE_DEFAULT_CHARSET);
    }


    /**
     * 将图片经过Base64编码成字符串
     *
     * @param image   图片
     * @param charset 字符集
     * @return 将图片转换为Base64编码的字符串
     */
    public static String imageToBase64String(BufferedImage image, String charset) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, IMAGE_TYPE_PNG, new Base64OutputStream(os));
            return os.toString(charset);
        } catch (IOException e) {
            logger.error("[图片Base64编码失败！]" + e.getMessage(), e);
        }

        return null;
    }

    /**
     * 将Base64编码字符串转换为image二进制流
     *
     * @param base64ImageString 图片二进制流经过Base64编码
     * @param file              生成图片路径图片文件
     */
    public static void base64StringToImageFile(String base64ImageString, File file) {
        try {
            Base64 d = new Base64();
            byte[] bs = d.decode(base64ImageString);
            FileOutputStream os = new FileOutputStream(file);
            os.write(bs);
            os.close();
        } catch (Exception e) {
            logger.error("Base64字符串解码成image失败！" + e.getMessage(), e);
        }
    }
}