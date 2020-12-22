package com.johar.geektime.dubbohmilytccdemo.config;

import com.johar.geektime.dubbohmilytccdemo.annotation.DynamicSource;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @ClassName: DynamicDtatSOurceAnnotationInterceptor
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/22 11:19
 * @Since: 1.0.0
 */
public class DynamicDtatSOurceAnnotationInterceptor implements MethodInterceptor {

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation methodInvocation) throws Throwable {
        try {
            String dsFlag = methodInvocation.getMethod().getAnnotation(DynamicSource.class).name();
            DynamicSourceContext.setDataSourceName(dsFlag);
            return methodInvocation.proceed();
        } finally {
            DynamicSourceContext.clearDataSource();
        }
    }
}