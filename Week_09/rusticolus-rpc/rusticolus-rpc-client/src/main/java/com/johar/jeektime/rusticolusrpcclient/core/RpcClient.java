package com.johar.jeektime.rusticolusrpcclient.core;

import com.alibaba.fastjson.parser.ParserConfig;

import java.lang.reflect.Proxy;

/**
 * @ClassName: RpcClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 16:39
 * @Since: 1.0.0
 */
public class RpcClient {

    public static <T> T create(final Class<T> serviceClass, final String url){
        return (T) Proxy.newProxyInstance(RpcClient.class.getClassLoader(),
                new Class[] { serviceClass},
                new RpcInvocationHandler(serviceClass, url));
    }
}