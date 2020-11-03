package com.johar.jeektime.nettyjeektimeweek3.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName: HttpInBoundServer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 08:36
 * @Since: 1.0.0
 */
@Slf4j
public class HttpInBoundServer {

    private final int port;
    private final String proxyServer;

    public HttpInBoundServer(int port, String proxyServer) {
        this.port = port;
        this.proxyServer = proxyServer;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1,
                new CustomizableThreadFactory("BossGroup"));
        int cores = Runtime.getRuntime().availableProcessors();
        EventLoopGroup workerGroup = new NioEventLoopGroup(cores * 10,
                new CustomizableThreadFactory("WorkerGroup"));

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInboundInitializer(this.proxyServer));

            Channel channel = bootstrap.bind(port).sync().channel();
            log.info("start netty http  server success，server ip: http://127.0.0.1:{}/", port);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("run netty error: ", e);
        }
    }
}