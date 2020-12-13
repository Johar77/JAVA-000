package com.johar.geektime.rusticolusrpcdemoprovider.config;

import com.johar.jeektime.rusticolusrpccommon.resolver.RpcResolver;
import com.johar.jeektime.rusticolusrpcserver.core.RpcServerInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RpcConfiguration
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 23:15
 * @Since: 1.0.0
 */
@Configuration
public class RpcConfiguration {
    @Bean
    public RpcServerInvoker invocationHandler(@Autowired RpcResolver resolver){
        return new RpcServerInvoker(resolver);
    }
}