package com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.Header;

import java.util.Arrays;

/**
 * @ClassName: AddHeaderHttpRequestFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 22:29
 * @Since: 1.0.0
 */
public class AddHeaderHttpRequestFilter implements IHttpRequestFilter {

    private final Header[] headers;

    public AddHeaderHttpRequestFilter(Header[] headers) {
        this.headers = headers;
    }

    @Override
    public int getOrderId() {
        return 0;
    }

    @Override
    public boolean filter(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        if (this.headers == null || headers.length == 0){
            return true;
        }

        Arrays.stream(headers).forEach((header) ->{
            fullHttpRequest.headers().set(header.getName(), header.getValue());
        });

        return true;
    }
}