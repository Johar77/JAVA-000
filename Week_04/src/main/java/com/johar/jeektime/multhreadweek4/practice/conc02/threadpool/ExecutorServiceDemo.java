package com.johar.jeektime.multhreadweek4.practice.conc02.threadpool;

import com.johar.jeektime.multhreadweek4.practice.conc02.factory.CustomThreadFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName: ExecutorServiceDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/9 22:23
 * @Since: 1.0.0
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();

        //scheduledExecutorService(cores);

        // singleThreadScheduledExecutor();

        //fixedThreadPool(cores);

        //cachedThreadPool();

        workStealingPool();

    }

    private static void scheduledExecutorService(int cores){
        ScheduledExecutorService executorService = Executors
                .newScheduledThreadPool(cores, new CustomizableThreadFactory());

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello newScheduledThreadPool : " + Thread.currentThread().getId());
                }
            });
        }

        executorService.shutdown();
    }

    private static void singleThreadScheduledExecutor(){
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor(new CustomizableThreadFactory());

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello newScheduledThreadPool : " + Thread.currentThread().getId());
                }
            });
        }

        executorService.shutdown();
    }

    private static void fixedThreadPool(int cores){
        ExecutorService executorService = Executors
                .newFixedThreadPool(cores, new CustomizableThreadFactory());

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello newScheduledThreadPool : " + Thread.currentThread().getId());
                }
            });
        }

        executorService.shutdown();
    }

    private static void cachedThreadPool(){
        ExecutorService executorService = Executors
                .newCachedThreadPool(new CustomizableThreadFactory());

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello newScheduledThreadPool : " + Thread.currentThread().getId());
                }
            });
        }

        executorService.shutdown();
    }

    private static void workStealingPool(){
        ExecutorService executorService = Executors
                .newWorkStealingPool();

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(" Hello newScheduledThreadPool : " + Thread.currentThread().getId());
                }
            });
        }

        executorService.shutdown();
    }
}