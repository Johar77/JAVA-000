package com.johar.jeektime.springmockdata.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName: TimeAop
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/1 08:50
 * @Since: 1.0.0
 */
@Aspect
@Component
public class TimeAop {

    @Pointcut("execution(public * com.johar.jeektime.springmockdata.service.*.*(..))")
    public void run(){}

    @Around("run()")
    public Object arround(ProceedingJoinPoint point){
        LocalDateTime localDateTime = LocalDateTime.now();
        try{
            return point.proceed();
        } catch (Throwable e){

        } finally {
            System.out.println(point.getTarget().getClass().getName() + " cost: " +  java.time.Duration.between(localDateTime, LocalDateTime.now()).toMillis() + " ms");
        }

        return null;
    }
}