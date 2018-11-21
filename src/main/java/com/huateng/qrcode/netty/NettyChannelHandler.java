package com.huateng.qrcode.netty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huateng.qrcode.base.parser.impl.XMLRequestVoParser;
import com.huateng.qrcode.base.parser.param.ResponseVo;
import com.huateng.qrcode.common.enums.ServiceConfigEnums;
import com.huateng.qrcode.base.parser.MsgParser;
import com.huateng.qrcode.base.parser.impl.XMLMsgParser;
import com.huateng.qrcode.base.parser.param.RequestVo;
import com.huateng.qrcode.qrserver.QrServerManager;
import com.huateng.qrcode.utils.SpringContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
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
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            logger.info("开始接受报文。。");

            Channel channel = ctx.channel();
            ByteBuf byteBuf = (ByteBuf) msg;
            String data = byteBuf.toString(CharsetUtil.UTF_8);

            logger.info("接收到的报文信息：" + data);

            //解析报文信息，获取服务码，根据服务码处理对应业务
            ResponseVo responseVo = processBusiness(data);
            ObjectMapper objectMapper = new ObjectMapper();
            String responseData = objectMapper.writeValueAsString(responseVo);
            logger.info("报文解析完成，响应内容：" + responseData);

            ByteBufAllocator alloc = channel.alloc();
            ByteBuf buffer = alloc.buffer();
            buffer.writeBytes(responseData.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(buffer);

            logger.info("报文响应结束。。");
            logger.info("报文处理结束。。");
        } catch (Exception e) {
            logger.error("socket解析报文出现异常", e);
            e.printStackTrace();
        }
    }


    //解析报文，请求分发
    private ResponseVo processBusiness(String data) throws Exception {
        ApplicationContext applicationContext = SpringContextUtil.getInstance().getApplicationContext();
        if (applicationContext == null) {
            throw new RuntimeException("解析报文时没有获取到applicationContext对象！");
        }

        MsgParser<RequestVo> parserManager = new XMLRequestVoParser();
        RequestVo requestVo = parserManager.parser(data);
        String serviceCode = requestVo.getSysHeader().getServiceCode();
        if (StringUtils.isBlank(serviceCode)) {
            throw new RuntimeException("报文中参数serviceCode不能为空！");
        }

        Class<?> serviceClass = ServiceConfigEnums.getByServiceCode(serviceCode);
        QrServerManager qrServerManager = (QrServerManager) applicationContext.getBean(serviceClass);
        if (qrServerManager == null) {
            throw new RuntimeException("spring容器中获取服务类bean失败！");
        }

        return qrServerManager.handler(requestVo);
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
