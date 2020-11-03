package com.johar.jeektime.nettyjeektimeweek3.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.Header;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

/**
 * @ClassName: AddHeaderHttpRequestFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 22:29
 * @Since: 1.0.0
 */
public class AddHeaderHttpRequestFilter implements IHttpRequestFilter {

    private final int orderId;
    private final Header[] headers;

    public AddHeaderHttpRequestFilter(int orderId, Header[] headers) {
        this.orderId = orderId;
        this.headers = headers;
    }

    @Override
    public int getOrderId() {
        return this.orderId;
    }

    @Override
    public void filter(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        if (this.headers == null || headers.length == 0){
            return;
        }

        Arrays.stream(headers).forEach((header) ->{
            fullHttpRequest.headers().set(header.getName(), header.getValue());
        });
    }
}