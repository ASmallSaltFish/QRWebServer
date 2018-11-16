package com.huateng.qrcode.qrserver.thread;

import com.huateng.qrcode.qrserver.netty.NettyServer;
import com.huateng.qrcode.utils.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 启动一个线程，提供socket通讯服务
 */
public class SocketServerHandler extends Thread {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {
        logger.info("二维码socket服务启动。。");
        NettyServer nettyServer = new NettyServer();
        nettyServer.bind(Integer.parseInt(ConfigConstants.getParam("qrCode.netty.port")));
    }
}
