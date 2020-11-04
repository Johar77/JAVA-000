package com.johar.jeektime.nettyjeektimeweek3.gateway.router;

import com.johar.jeektime.nettyjeektimeweek3.gateway.common.ProxyServerInfo;
import com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request.IHttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import sun.security.ssl.ProtocolVersion;

import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

/**
 * @ClassName: WeightedRandomHttpEndpointRouter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/5 00:04
 * @Since: 1.0.0
 */
public class WeightedRandomHttpEndpointRouter implements IHttpEndpointRouter {
    @Override
    public String route(List<ProxyServerInfo> endpoints) {
        int sum = 0;
        int[] nums = new int[endpoints.size()];
        int i = 0;
        for (ProxyServerInfo endpoint : endpoints){
            sum += endpoint.getWeight();
            nums[i++] = sum;
        }

        int random = new Random().nextInt(sum);
        for (int j = 0; j < endpoints.size(); j++){
            if (random < nums[j]){
                return endpoints.get(j).getBackEndUrl();
            }
        }

        return endpoints.get(endpoints.size()).getBackEndUrl();
    }
}