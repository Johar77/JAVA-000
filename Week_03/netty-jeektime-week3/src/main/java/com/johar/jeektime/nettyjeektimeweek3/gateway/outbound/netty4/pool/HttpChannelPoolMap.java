package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ClassName: HttpChannelPoolMap
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 15:05
 * @Since: 1.0.0
 */
public class HttpChannelPoolMap  extends AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool> {
    private final int maxConnections;
    private final int maxPendingAcquires;

    public HttpChannelPoolMap(int maxConnections, int maxPendingAcquires) {
        this.maxConnections = maxConnections;
        this.maxPendingAcquires = maxPendingAcquires;
    }

    @Override
    protected FixedChannelPool newPool(InetSocketAddress key) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.SO_SNDBUF, 32 * 1024);
        bootstrap.option(ChannelOption.SO_RCVBUF, 32 * 1024);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        return new FixedChannelPool(bootstrap.remoteAddress(key), new HttpChannelPoolHandler(), maxConnections, maxPendingAcquires);
    }
}