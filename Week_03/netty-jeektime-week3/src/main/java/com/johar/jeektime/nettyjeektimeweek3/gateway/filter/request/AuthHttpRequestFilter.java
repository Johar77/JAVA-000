package com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * @ClassName: AuthHttpRequestFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/4 23:27
 * @Since: 1.0.0
 */
public class AuthHttpRequestFilter implements IHttpRequestFilter{
    @Override
    public int getOrderId() {
        return 100;
    }

    /**
     * 根据http header中是否包含Token 判断是否有权限
     * @param fullHttpRequest
     * @param ctx
     */
    @Override
    public boolean filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        if (!fullHttpRequest.headers().contains("Token")){
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(fullHttpRequest.protocolVersion(), HttpResponseStatus.FORBIDDEN);
            ctx.channel().writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
            return false;
        }

        return true;
    }


}