package com.johar.jeektime.rusticolusrpcserver.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.johar.jeektime.rusticolusrpccommon.api.RpcReponse;
import com.johar.jeektime.rusticolusrpccommon.api.RpcRequest;
import com.johar.jeektime.rusticolusrpccommon.resolver.RpcResolver;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName: RpcServerInvoker
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 17:13
 * @Since: 1.0.0
 */
@Slf4j
public class RpcServerInvoker {
    private RpcResolver rpcResolver;

    public RpcServerInvoker(RpcResolver resolver){
        this.rpcResolver = resolver;
    }

    public RpcReponse invoke(RpcRequest request) {
        RpcReponse response = new RpcReponse();
        String serviceClass = request.getServiceClass();
        log.info("receive message: {}", JSON.toJSONString(request));
        try {
            Class<?> clazz = Class.forName(serviceClass);
            Object service = rpcResolver.resolve(clazz);
            Method method = resolverMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams());
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            log.error("RpcServerInvoker invoker error", e);
            response.setException(e);
            response.setStatus(false);
        }
        log.info("send message: {}", JSON.toJSONString(response));

        return response;
    }

    private Method resolverMethodFromClass(Class<?> aClass, String method) {
        return Arrays.stream(aClass.getMethods()).filter(m -> method.equals(m.getName())).findFirst().get();
    }
}