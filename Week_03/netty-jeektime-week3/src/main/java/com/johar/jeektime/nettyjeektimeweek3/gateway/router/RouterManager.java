package com.johar.jeektime.nettyjeektimeweek3.gateway.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: RouterManager
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/4 22:58
 * @Since: 1.0.0
 */
@Slf4j
public class RouterManager {

    private List<String> backEndUrls = new ArrayList<>();

    private IHttpEndpointRouter httpEndpointRouter = new RandomHttpEndpointRouter();

    private RouterManager(){
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8808,http://localhost:8807,http://localhost:8806");
        backEndUrls = Arrays.asList(proxyServer.split(",").clone());
    }

    private static class RouterManagerHollder{
        private static RouterManager INSTANCE = new RouterManager();
    }

    public static RouterManager getInstance(){
        return RouterManagerHollder.INSTANCE;
    }

    public String getBackEndUrl(){
        return httpEndpointRouter.route(backEndUrls);
    }
}