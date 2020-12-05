package com.johar.geektime.multidatasources.utils;

import com.johar.geektime.multidatasources.annotation.ReadOnly;
import com.johar.geektime.multidatasources.config.DataSourceType;
import com.johar.geektime.multidatasources.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @ClassName: DataSourceAspect
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 21:19
 * @Since: 1.0.0
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    @Pointcut("@annotation(com.johar.geektime.multidatasources.annotation.ReadOnly)")
    public void dataSourcePointCut(){

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        ReadOnly readOnly = method.getAnnotation(ReadOnly.class);
        if (readOnly == null){
            DynamicDataSource.setDataSourceName(DynamicDataSource.MASTER);
            log.info("DataSource ----->  {}  <----------", DynamicDataSource.MASTER);
        } else {
            Random random = new Random();
            int num = random.nextInt(DynamicDataSource.SLAVES.length);
            DynamicDataSource.setDataSourceName(DynamicDataSource.SLAVES[num]);
            log.info("DataSource ----->  {}  <----------", DynamicDataSource.SLAVES[num]);
        }

        try{
            return point.proceed();
        } catch (Throwable e){
            log.error("DataSourceAspect around error", e);
        } finally {
            DynamicDataSource.clearDataSourceType();
        }

        return null;
    }
}