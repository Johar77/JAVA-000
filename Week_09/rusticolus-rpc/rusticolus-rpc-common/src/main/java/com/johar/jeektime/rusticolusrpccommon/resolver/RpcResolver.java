package com.johar.jeektime.rusticolusrpccommon.resolver;

/**
 * @ClassName: RpcResolver
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 16:20
 * @Since: 1.0.0
 */
public interface RpcResolver {
    Object resolve(String serviceName);

    Object resolve(Class<?> clazz);
}
