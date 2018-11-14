package com.huateng.qrcode.qrserver.netty;

import com.huateng.qrcode.qrserver.manager.QRManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {

    //todo 处理二维码解析的类实例
    private QRManager qrManager;


    public NettyChannelHandler() {

    }

    public NettyChannelHandler(QRManager qrManager) {
        this.qrManager = qrManager;
    }

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

        //todo 开始解析二维码，具体业务
        qrManager.parserQRCode(text);

        //这里服务端响应客户端信息，需要返回一个byteBuf，使用池化bytebuf，提高性能
        String resposeStr = "服务端处理完成了，我是来告诉客户端的~~";
        ByteBufAllocator alloc = channel.alloc();
        ByteBuf buffer = alloc.buffer();
        buffer.writeBytes(resposeStr.getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将暂存的ChannelOutboundBuffer中的消息冲刷到远程节点，并关闭Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        System.out.println("-->>channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("-->>exceptionCaught");
        ctx.close();
    }
}
