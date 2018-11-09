package com.huateng.qrcode.qrserver.manager;

import org.springframework.stereotype.Component;

/**
 * 二维码服务管理类
 */
@Component(value = "qrManager")
public class QRManager {

    public void parserQRCode(String qrData) {
        System.out.println("---->>解析二维码报文，qrData=" + qrData);
    }
}
