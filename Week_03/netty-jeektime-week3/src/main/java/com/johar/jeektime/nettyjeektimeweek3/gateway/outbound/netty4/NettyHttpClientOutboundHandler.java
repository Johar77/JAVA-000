package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: NettyHttpClientOutbounderHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 08:57
 * @Since: 1.0.0
 */
@Slf4j
public class NettyHttpClientOutboundHandler implements IOutboundHandler {



    @Override
    public void handle(FullHttpRequest request, ChannelHandlerContext ctx) {

    }
}