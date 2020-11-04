package com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @ClassName: IHttpRequestFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 22:24
 * @Since: 1.0.0
 */
public interface IHttpRequestFilter {

    /**
     * 多个filter，根据OrderId排序，OrderId越大，优先级越高
     * @return
     */
    int getOrderId();

    /**
     * http filter
     * @param fullHttpRequest
     * @param ctx
     */
    boolean filter(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx);
}
