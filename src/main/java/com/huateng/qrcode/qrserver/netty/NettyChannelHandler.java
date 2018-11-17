package com.huateng.qrcode.qrserver.netty;

import com.huateng.qrcode.qrserver.config.ServiceConfigEnums;
import com.huateng.qrcode.qrserver.manager.QrServerManager;
import com.huateng.qrcode.qrserver.manager.ServiceConfigMapping;
import com.huateng.qrcode.utils.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

@ChannelHandler.Sharable
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(NettyChannelHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-->>channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("-->>channelRead");
        ByteBuf byteBuf = (ByteBuf) msg;
        String text = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("------------>>>>text=" + text);
        System.out.println("------>>>获取数据成功啦。。。");

        //开始解析二维码，获取服务码，获得对应处理服务对象，调用handler方法处理具体业务
        handler();

        //这里服务端响应客户端信息，需要返回一个byteBuf，使用池化bytebuf，用来提高性能
        String resposeStr = "服务端处理完成了，我是来告诉客户端的~~";
        ByteBufAllocator alloc = channel.alloc();
        ByteBuf buffer = alloc.buffer();
        buffer.writeBytes(resposeStr.getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(buffer);
    }


    private void handler() throws ClassNotFoundException {
        ApplicationContext applicationContext = SpringContextUtil.getInstance().getApplicationContext();
//        ServiceConfigMapping serviceConfigMapping = (ServiceConfigMapping) applicationContext.getBean("serviceConfigMapping");
        String serviceCode = "001";
//        String serviceFullClassName = serviceConfigMapping.getFullClassName(serviceCode);
//        Class<?> serviceClass = Class.forName(serviceFullClassName);
        Class serviceClass = ServiceConfigEnums.getByServiceCode(serviceCode);
        QrServerManager qrServerManager = (QrServerManager) applicationContext.getBean(serviceClass);
        qrServerManager.handler(serviceCode);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将暂存的ChannelOutboundBuffer中的消息冲刷到远程节点，并关闭Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        System.out.println("-->>channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("channel出现异常情况" + cause.getMessage());
        Channel channel = ctx.channel();
        if (!channel.isActive()) {
            logger.info("关闭channel");
            channel.close();
            ctx.close();
        }
    }
}
