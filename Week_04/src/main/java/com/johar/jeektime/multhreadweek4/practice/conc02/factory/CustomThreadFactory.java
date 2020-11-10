package com.johar.jeektime.multhreadweek4.practice.conc02.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CustomThreadFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/9 22:05
 * @Since: 1.0.0
 */
public class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger serial = new AtomicInteger();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName("CustomThread-" + serial.incrementAndGet());
        return thread;
    }
}