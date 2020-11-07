package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
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
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: NettyHttpClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/3 09:00
 * @Since: 1.0.0
 */
@Slf4j
public class NettyHttpClient  {


    public static FullHttpResponse send(final FullHttpRequest fullHttpRequest){
        HttpVersion version = HttpVersion.HTTP_1_1;
        HttpResponseStatus status = HttpResponseStatus.NO_CONTENT;
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
            String host = fullHttpRequest.headers().get("Host");
            URL url = new URL(host);
            ChannelFuture f = bootstrap.connect(url.getHost(), url.getPort()).sync();
            f.channel().writeAndFlush(fullHttpRequest);

            fullHttpResponse = responseFuture.get();

            f.channel().close().sync();
        } catch (InterruptedException e) {
            log.error("Netty Client Error: ", e);
        } catch (ExecutionException e) {
            log.error("Netty Client Error: ", e);
        } catch (MalformedURLException e) {
            log.error("Netty Client Error: ", e);
        } finally {
            workerGroup.shutdownGracefully();
        }

        return fullHttpResponse;
    }
}