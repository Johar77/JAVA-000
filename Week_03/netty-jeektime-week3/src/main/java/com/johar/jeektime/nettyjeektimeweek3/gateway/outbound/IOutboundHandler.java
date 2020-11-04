package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

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
    void handle(final FullHttpRequest request, final ChannelHandlerContext ctx);

}
