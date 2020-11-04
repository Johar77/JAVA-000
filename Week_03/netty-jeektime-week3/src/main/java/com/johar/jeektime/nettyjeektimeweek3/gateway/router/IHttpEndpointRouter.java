package com.johar.jeektime.nettyjeektimeweek3.gateway.router;

import java.util.List;

/**
 * @ClassName: IHttpEndpointRouter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/4 08:39
 * @Since: 1.0.0
 */
public interface IHttpEndpointRouter {
    /**
     * 路由接口
     * @param endpoints
     * @return
     */
    String route(List<String> endpoints);
}
