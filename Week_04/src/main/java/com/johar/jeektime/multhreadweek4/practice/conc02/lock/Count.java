package com.johar.jeektime.multhreadweek4.practice.conc02.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: Count
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/10 23:27
 * @Since: 1.0.0
 */
public class Count {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private int num = 0;

    public void add() throws InterruptedException {
        synchronized (lock1){
            Thread.sleep(1000);

            synchronized (lock2){
                num++;
            }
            System.out.println(Thread.currentThread().getName() + "  " + num);
        }
    }

    public void add2() throws InterruptedException {
        synchronized (lock2){
            Thread.sleep(1000);

            synchronized (lock1){
                num++;
            }
            System.out.println(Thread.currentThread().getName() + "  " + num);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;
        Count count = new Count();
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    count.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    count.add2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.shutdown();
    }
}