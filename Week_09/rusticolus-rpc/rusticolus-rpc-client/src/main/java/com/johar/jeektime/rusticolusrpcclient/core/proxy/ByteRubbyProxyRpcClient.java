package com.johar.jeektime.rusticolusrpcclient.core.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

/**
 * @ClassName: ByteRubbyProxyRpcClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/16 23:58
 * @Since: 1.0.0
 */
public class ByteRubbyProxyRpcClient {
    public static <T> T create(final Class<T> serviceClass, final String url) throws Exception {

        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, url)))
                .make()
                .load(JavaProxyRpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
}