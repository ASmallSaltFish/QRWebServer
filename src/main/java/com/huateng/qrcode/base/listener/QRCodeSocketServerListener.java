package com.huateng.qrcode.base.listener;

import com.huateng.qrcode.netty.NettyServer;
import com.huateng.qrcode.utils.ConfigConstants;
import com.huateng.qrcode.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QRCodeSocketServerListener extends BaseListener {

    private static Logger logger = LoggerFactory.getLogger(QRCodeSocketServerListener.class);

    private NettyServer nettyServer;

    @Override
    protected void init() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            logger.info("启动socket监听服务。。。");
            nettyServer = (NettyServer) super.getBean("nettyServer");
            if (nettyServer == null) {
                throw new RuntimeException("spring容器中没有获取到nettyServer实例，启动socket服务失败！");
            }

            nettyServer.bind(Integer.parseInt(ConfigConstants.getParam("qrCode.netty.port")));
        });

        executorService.shutdown();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("正在关闭socket服务。。。");
        logger.info("正在关闭socket服务。。。");

        try {
            //关闭netty服务
            nettyServer.close();
        } catch (Exception e) {
            logger.error("关闭socket服务时出现异常", e);
        }

        System.out.println("socket服务已关闭。。。");
        logger.info("socket服务已关闭。。。");
    }
}
