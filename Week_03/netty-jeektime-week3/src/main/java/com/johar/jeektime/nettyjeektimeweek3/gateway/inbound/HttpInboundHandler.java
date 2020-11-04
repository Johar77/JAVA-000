package com.johar.jeektime.nettyjeektimeweek3.gateway.inbound;

import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.AddHeaderHttpRequestFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.IHttpRequestFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.httpclient.HttpOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.NettClientOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.IHttpEndpointRouter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: HttpInboundHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 22:39
 * @Since: 1.0.0
 */
@Slf4j
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private final List<String> proxyServers;
    private IOutboundHandler handler;
    private List<IHttpRequestFilter> httpRequestFilters;
    private IHttpEndpointRouter endpointRouter;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServers = Arrays.asList(proxyServer.split(",").clone());
        this.endpointRouter = new RandomHttpEndpointRouter();
        //this.handler = new HttpOutboundHandler(proxyServer);
        this.handler = new NettClientOutboundHandler(proxyServer);
        int orderId = 0;
        Header[] headers = new Header[]{ new BasicHeader("nio", "Johar")};
        this.httpRequestFilters = new ArrayList<>();
        this.httpRequestFilters.add(orderId, new AddHeaderHttpRequestFilter(0, headers));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;

            for (IHttpRequestFilter httpRequestFilter : httpRequestFilters) {
                httpRequestFilter.filter(fullHttpRequest, ctx);
            }
            String url = this.endpointRouter.route(this.proxyServers);
            log.info("HttpEndPointRouter: {}", url);
            handler.setBackendUrl(url);
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