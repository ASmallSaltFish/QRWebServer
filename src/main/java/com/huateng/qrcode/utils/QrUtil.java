package com.huateng.qrcode.utils;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.base.parser.param.base.BusRespBody;
import com.huateng.qrcode.common.enums.QrCodeTxnStatusMenu;
import com.huateng.qrcode.common.model.QrcodeTxn;

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


    /**
     * 封装二维码解析生成流水表记录实体
     */
    public static QrcodeTxn getQrcodeTxn(String qrcodeId, String industryApp, String useType,
                                         String scene, String receiveDate, String receiveTime, String remark) {
        QrcodeTxn qrcodeTxn = new QrcodeTxn();
        qrcodeTxn.setTxnId(IdWorker.getIdStr());
        qrcodeTxn.setQrcodeId(qrcodeId);
        qrcodeTxn.setApplication(industryApp);
        qrcodeTxn.setPurpose(useType);
        qrcodeTxn.setScene(scene);
        qrcodeTxn.setReceiveDate(receiveDate);
        qrcodeTxn.setReceiveTime(receiveTime);
        qrcodeTxn.setRemark(remark);
        qrcodeTxn.setStatus(QrCodeTxnStatusMenu.VALID.getCode());
        qrcodeTxn.setCrtDate(DateUtil.formatCurrentDate());
        qrcodeTxn.setCrtTime(DateUtil.formatCurrentTime());
        return qrcodeTxn;
    }


    /**
     * 封装二维码解析响应报文
     *
     * @param processCode   响应码
     * @param processStatus 响应状态
     * @param msg           响应信息
     * @return 响应报文对象
     */
    public static ResponseVo wrapResponseVo(String processCode, String processStatus, String msg) {
        ResponseVo responseVo = new ResponseVo();
        BusRespBody busRespBody = new BusRespBody();
        busRespBody.setProcessCode(processCode);
        busRespBody.setProcessStatus(processStatus);
        busRespBody.setMsg(msg);
        responseVo.setBusRespBody(busRespBody);
        return responseVo;
    }
}
