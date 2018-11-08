package com.huateng.qrcode.qrserver.thread;

import com.huateng.qrcode.qrserver.manager.QRManager;
import com.huateng.qrcode.qrserver.netty.NettyServer;
import com.huateng.qrcode.utils.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 启动一个线程，提供socket通讯服务
 */
public class SocketServerHandler extends Thread {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private QRManager qrManager;

    public SocketServerHandler() {

    }

    public SocketServerHandler(QRManager qrManager) {
        this.qrManager = qrManager;
    }

    public void run() {
        System.out.println("二维码socket服务启动。。");
        NettyServer nettyServer = new NettyServer(qrManager);
        nettyServer.bind(Integer.parseInt(ConfigConstants.getParam("qrCode.netty.port")));
    }
}
