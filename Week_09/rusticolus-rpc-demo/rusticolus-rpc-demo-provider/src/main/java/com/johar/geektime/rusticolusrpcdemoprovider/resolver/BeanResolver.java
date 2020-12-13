package com.johar.geektime.rusticolusrpcdemoprovider.resolver;

import com.johar.jeektime.rusticolusrpccommon.resolver.RpcResolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BeanResolver
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:46
 * @Since: 1.0.0
 */
@Component
public class BeanResolver implements RpcResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object resolve(String serviceName) {
        return applicationContext.getBean(serviceName);
    }

    @Override
    public Object resolve(Class<?> clazz){
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}