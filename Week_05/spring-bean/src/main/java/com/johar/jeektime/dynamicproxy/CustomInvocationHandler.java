package com.johar.jeektime.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: CustomInvocationHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 12:37
 * @Since: 1.0.0
 */
public class CustomInvocationHandler implements InvocationHandler {

    private final Object target;

    public CustomInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long duration = System.currentTimeMillis() - startTime;
        System.out.println(method.getName() + " elapse " + duration + " ms");
        return result;
    }
}