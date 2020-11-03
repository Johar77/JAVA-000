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

    private final String backendUrl;

    public NettClientOutboundHandler(String backendUrl){
        this.backendUrl = backendUrl;
    }

    @Override
    public void handle(final FullHttpRequest request, final ChannelHandlerContext ctx) {
        request.headers().set("Host", this.backendUrl);
        FullHttpResponse response = NettyHttpClient.send(request);
        if (request != null){
            if (!HttpUtil.isKeepAlive(request)){
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.write(response);
            }
        }
        ctx.flush();
    }
}