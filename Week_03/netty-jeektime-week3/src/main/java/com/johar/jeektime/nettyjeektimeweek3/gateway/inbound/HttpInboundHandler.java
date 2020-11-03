package com.johar.jeektime.nettyjeektimeweek3.gateway.inbound;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.httpclient.HttpOutbounderHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: HttpInboundHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 22:39
 * @Since: 1.0.0
 */
@Slf4j
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private final String proxyServer;
    private HttpOutbounderHandler handler;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new HttpOutbounderHandler(this.proxyServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
            handler.handle(fullHttpRequest, ctx);
        } catch (Exception e){
            log.error("HttpInboundHandler channelRead error: ", e);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("HttpInboundHandler Handler Error: ", cause);
        ctx.close();
    }
}