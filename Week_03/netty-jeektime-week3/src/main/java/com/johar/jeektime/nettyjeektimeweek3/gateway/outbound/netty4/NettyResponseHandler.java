package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: NettyHttpClientOutbounderHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 08:57
 * @Since: 1.0.0
 */
@Slf4j
public class NettyResponseHandler extends ChannelInboundHandlerAdapter {

    private final FullHttpResponseFuture httpResponseFuture;

    public NettyResponseHandler(FullHttpResponseFuture httpResponseFuture){
        this.httpResponseFuture = httpResponseFuture;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        httpResponseFuture.setSuccess((FullHttpResponse) msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}