package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.FullHttpResponseFuture;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.RouterManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.handler.codec.http.*;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName: HttpClientPool
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 15:28
 * @Since: 1.0.0
 */
public class NettyHttpClientPool {
    HttpChannelPoolMap poolMap = new HttpChannelPoolMap(3, 6);
    private RequestPendingCenter requestPendingCenter = RequestPendingCenter.getInstance();

    public NettyHttpClientPool(){

    }

    public void newCall(String requestId, FullHttpRequest request){
        String url = RouterManager.getInstance().getBackEndUrl();
        InetSocketAddress address = getAddress(url);
        FixedChannelPool pool = poolMap.get(address);
        Future<Channel> channelFuture = pool.acquire();
        try {
            Channel channel = channelFuture.get();
            FullHttpResponseFuture future = new FullHttpResponseFuture();
            requestPendingCenter.add(requestId, future);
            channel.writeAndFlush(request.copy());
            pool.release(channel);
        } catch (InterruptedException | ExecutionException e) {
            requestPendingCenter.set(requestId, getErrorResponse(requestId));
            e.printStackTrace();
        }

    }

    private InetSocketAddress getAddress(String url){
        URI uri = URI.create(url);
        return InetSocketAddress.createUnresolved(uri.getHost(), uri.getPort());
    }

    private DefaultFullHttpResponse getErrorResponse(String requestId){
        HttpVersion version = HttpVersion.HTTP_1_1 ;
        HttpResponseStatus status = HttpResponseStatus.INTERNAL_SERVER_ERROR;
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, status);
        response.headers().set("Connection", "keep-alive");
        response.headers().set("RequestID", requestId);
        return response;
    }
}