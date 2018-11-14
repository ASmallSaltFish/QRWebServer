package com.huateng.qrcode.common.listener;

import com.huateng.qrcode.qrserver.manager.QRManager;
import com.huateng.qrcode.qrserver.thread.SocketServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;

public class QRCodeSocketServerListener extends BaseListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    protected void init() {
        //spring容器中获取处理二维码解析的bean
        QRManager qrManager = (QRManager) super.getBean("qrManager");

        logger.info("启动socket监听服务。。。");
        SocketServerHandler socketServerHandler = new SocketServerHandler(qrManager);
        socketServerHandler.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("socket监听服务已关闭。。。");
    }
}
