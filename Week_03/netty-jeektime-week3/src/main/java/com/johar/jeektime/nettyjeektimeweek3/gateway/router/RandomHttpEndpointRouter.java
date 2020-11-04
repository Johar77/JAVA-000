package com.johar.jeektime.nettyjeektimeweek3.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @ClassName: RandomRouter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/4 08:41
 * @Since: 1.0.0
 */
public class RandomHttpEndpointRouter implements IHttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null | endpoints.isEmpty()){
            throw new IllegalArgumentException("endpoints can not be null");
        }

        int size = endpoints.size();
        int random = new Random().nextInt(size);
        return endpoints.get(random);
    }
}