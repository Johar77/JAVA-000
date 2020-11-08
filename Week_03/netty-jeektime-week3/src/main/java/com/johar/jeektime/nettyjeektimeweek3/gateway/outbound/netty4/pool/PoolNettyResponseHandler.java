package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @ClassName: PoolNettyResponseHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 15:15
 * @Since: 1.0.0
 */
public class PoolNettyResponseHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse httpResponse = (FullHttpResponse)msg;
        String requestID = httpResponse.headers().get("RequestID");
        RequestPendingCenter.getInstance().set(requestID, httpResponse);
    }
}