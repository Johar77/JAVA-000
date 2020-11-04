package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
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

    private String backendUrl;

    public NettClientOutboundHandler(String backendUrl){
        this.backendUrl = backendUrl;
    }

    @Override
    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        FullHttpRequest newHttpRequest = fullHttpRequest.copy();
        newHttpRequest.headers().set("Host", this.backendUrl);
        FullHttpResponse response = NettyHttpClient.send(newHttpRequest);
        if (!HttpUtil.isKeepAlive(fullHttpRequest)){
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.write(response);
        }
        ctx.flush();
    }

    @Override
    public void setBackendUrl(String backendUrl) {
        this.backendUrl = backendUrl;
    }
}