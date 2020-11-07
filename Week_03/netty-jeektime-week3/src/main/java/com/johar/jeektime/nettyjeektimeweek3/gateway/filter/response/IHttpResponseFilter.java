package com.johar.jeektime.nettyjeektimeweek3.gateway.filter.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: IHttpResponseFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/7 23:50
 * @Since: 1.0.0
 */
public interface IHttpResponseFilter {
    /**
     * 多个filter，根据OrderId排序，OrderId越大，优先级越高
     * @return
     */
    int getOrderId();

    /**
     * http filter
     * @param fullHttpResponse
     * @param ctx
     */
    void filter(final FullHttpResponse fullHttpResponse, final ChannelHandlerContext ctx) throws JsonProcessingException, UnsupportedEncodingException;
}
