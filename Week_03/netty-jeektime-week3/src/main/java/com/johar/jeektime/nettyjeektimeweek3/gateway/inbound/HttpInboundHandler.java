package com.johar.jeektime.nettyjeektimeweek3.gateway.inbound;

import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request.AddHeaderHttpRequestFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request.AuthHttpRequestFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request.HttpRequestFilterComparator;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request.IHttpRequestFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.response.IHttpResponseFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.response.MatchHeaderHttpResponseFilter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.FullHttpResponseFuture;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.NettClientOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool.NettyHttpClientPool;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool.RequestPendingCenter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.IHttpEndpointRouter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
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
    private IOutboundHandler handler;
    private List<IHttpRequestFilter> httpRequestFilters;
    private IHttpResponseFilter httpResponseFilter;
    private IHttpEndpointRouter endpointRouter;
    private NettyHttpClientPool nettyHttpClientPool;

    public HttpInboundHandler() {
        this.endpointRouter = new RandomHttpEndpointRouter();
        //this.handler = new HttpOutboundHandler(proxyServer);
        this.handler = new NettClientOutboundHandler();
        int orderId = 0;
        Header[] headers = new Header[]{ new BasicHeader("nio", "Johar")};
        this.httpRequestFilters = new ArrayList<>();
        this.httpRequestFilters.add(new AddHeaderHttpRequestFilter(headers));
        this.httpRequestFilters.add(new AuthHttpRequestFilter());
        this.httpRequestFilters.sort(new HttpRequestFilterComparator());

        this.httpResponseFilter = new MatchHeaderHttpResponseFilter();

        this.nettyHttpClientPool = new NettyHttpClientPool();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;

            // RequestFilter
            for (IHttpRequestFilter httpRequestFilter : httpRequestFilters) {
                if (!httpRequestFilter.filter(fullHttpRequest, ctx)){
                    return;
                }
            }

            // 请求实际的服务
            //FullHttpResponse fullHttpResponse = handler.handle(fullHttpRequest, ctx);
            String requestID = fullHttpRequest.headers().get("RequestID");
            nettyHttpClientPool.newCall(requestID, fullHttpRequest);
            FullHttpResponseFuture future = RequestPendingCenter.getInstance().get(requestID);
            FullHttpResponse fullHttpResponse = future.get();

            // ResponseFilter
            httpResponseFilter.filter(fullHttpResponse, ctx);

            // 返回客户端
            if (!HttpUtil.isKeepAlive(fullHttpRequest)){
                ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
            } else {
                ctx.write(fullHttpResponse);
            }
            ctx.flush();

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