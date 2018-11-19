package com.huateng.qrcode.base.listener;

import com.huateng.qrcode.qrserver.netty.NettyServer;
import com.huateng.qrcode.utils.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QRCodeSocketServerListener extends BaseListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void init() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            logger.info("启动socket监听服务。。。");
            NettyServer nettyServer = new NettyServer();
            nettyServer.bind(Integer.parseInt(ConfigConstants.getParam("qrCode.netty.port")));
        });

        executorService.shutdown();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("socket监听服务已关闭。。。");
    }
}
