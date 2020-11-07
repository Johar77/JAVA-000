package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @ClassName: IInboundHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 22:42
 * @Since: 1.0.0
 */
public interface IOutboundHandler {

    /**
     * 处理http请求转发，回复
     * @param request
     * @param ctx
     */
    FullHttpResponse handle(final FullHttpRequest request, final ChannelHandlerContext ctx);

}
