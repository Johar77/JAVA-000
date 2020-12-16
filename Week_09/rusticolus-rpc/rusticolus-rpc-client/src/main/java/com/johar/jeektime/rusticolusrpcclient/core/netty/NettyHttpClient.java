package com.johar.jeektime.rusticolusrpcclient.core.netty;

import com.alibaba.fastjson.JSON;
import com.johar.jeektime.rusticolusrpccommon.api.RpcReponse;
import com.johar.jeektime.rusticolusrpccommon.api.RpcRequest;
import com.johar.jeektime.rusticolusrpccommon.exception.RpcException;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: NettyHttpClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/17 00:12
 * @Since: 1.0.0
 */
@Slf4j
public class NettyHttpClient  {

    public static RpcReponse post(RpcRequest request, String url){
        RpcReponse result = new RpcReponse();
        try{
            String body = JSON.toJSONString(request);
            ByteBuf content = Unpooled.wrappedBuffer(body.getBytes(StandardCharsets.UTF_8));
            FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.POST,
                    "/",
                    content);
            URL host = new URL(url);
            fullHttpRequest.headers().set("Content-Type", "application/json;charset=utf-8");
            fullHttpRequest.headers().set("Host", host.getHost());
            fullHttpRequest.headers().set("Connection", "keep-alive");
            fullHttpRequest.headers().set("Content-Length", content.readableBytes());
            FullHttpResponse httpResponse = send(fullHttpRequest, url);
            String string = httpResponse.content().toString(StandardCharsets.UTF_8);
            if (httpResponse.status().equals(HttpResponseStatus.OK)){
                result = JSON.parseObject(string, RpcReponse.class);
            } else {
                result.setStatus(false);
                result.setException(new RpcException(string));
            }
        } catch (Exception e){
            result.setStatus(false);
            result.setException((RpcException)e);
        }

        return result;
    }


    public static FullHttpResponse send(final FullHttpRequest fullHttpRequest, String url) throws Exception{
        HttpVersion version = HttpVersion.HTTP_1_1;
        HttpResponseStatus status = HttpResponseStatus.INTERNAL_SERVER_ERROR;;
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(version, status);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            FullHttpResponseFuture responseFuture = new FullHttpResponseFuture();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new HttpClientCodec());
                    socketChannel.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                    socketChannel.pipeline().addLast(new NettyResponseHandler(responseFuture));
                }
            });
            URL host = new URL(url);
            ChannelFuture f = bootstrap.connect(host.getHost(), host.getPort()).sync();
            f.channel().writeAndFlush(fullHttpRequest);

            fullHttpResponse = responseFuture.get();

            f.channel().close().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

        return fullHttpResponse;
    }
}