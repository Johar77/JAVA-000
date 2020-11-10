package com.johar.jeektime.multhreadweek4.practice.conc01;

import java.util.concurrent.Callable;

/**
 * @ClassName: ThreadC
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 23:41
 * @Since: 1.0.0
 */
public class ThreadC implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        System.out.println("This is ThreadC");
        return "ThreadC";
    }
}