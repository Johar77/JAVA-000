package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * @ClassName: HttpChannelPoolHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 15:11
 * @Since: 1.0.0
 */
public class HttpChannelPoolHandler  extends AbstractChannelPoolHandler {
    @Override
    public void channelCreated(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpClientCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new PoolNettyResponseHandler());
    }
}