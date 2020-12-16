package com.johar.jeektime.rusticolusrpcclient.core;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.Proxy;

/**
 * @ClassName: RpcClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 16:39
 * @Since: 1.0.0
 */
public class JavaProxyRpcClient {

    public static <T> T create(final Class<T> serviceClass, final String url) throws Exception {
        return (T) Proxy.newProxyInstance(JavaProxyRpcClient.class.getClassLoader(),
                new Class[] { serviceClass},
                new RpcInvocationHandler(serviceClass, url));
    }
}