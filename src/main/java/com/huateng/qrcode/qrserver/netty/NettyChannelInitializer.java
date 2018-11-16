package com.huateng.qrcode.qrserver.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final StringEncoder ENCODER = new StringEncoder(CharsetUtil.UTF_8);
    private static final StringDecoder DECODER = new StringDecoder(CharsetUtil.UTF_8);

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        socketChannel.pipeline().addLast(DECODER);
//        socketChannel.pipeline().addLast(ENCODER);
        socketChannel.pipeline().addLast(new NettyChannelHandler());
    }
}
