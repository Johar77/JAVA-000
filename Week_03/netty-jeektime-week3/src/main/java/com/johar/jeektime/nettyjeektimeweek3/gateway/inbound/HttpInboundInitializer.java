package com.johar.jeektime.nettyjeektimeweek3.gateway.inbound;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @ClassName: HttpInboundInitializer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 08:55
 * @Since: 1.0.0
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    public HttpInboundInitializer() {
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new HttpInboundHandler());
    }
}