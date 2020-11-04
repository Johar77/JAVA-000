package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.RouterManager;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * @ClassName: NettClientOutboundhandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 23:58
 * @Since: 1.0.0
 */
public class NettClientOutboundHandler implements IOutboundHandler {

    public NettClientOutboundHandler(){
    }

    @Override
    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        FullHttpRequest newHttpRequest = fullHttpRequest.copy();
        newHttpRequest.headers().set("Host", RouterManager.getInstance().getBackEndUrl());
        FullHttpResponse response = NettyHttpClient.send(newHttpRequest);
        if (!HttpUtil.isKeepAlive(fullHttpRequest)){
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.write(response);
        }
        ctx.flush();
    }
}