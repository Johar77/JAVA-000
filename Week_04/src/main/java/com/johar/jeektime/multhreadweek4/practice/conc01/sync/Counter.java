package com.johar.jeektime.multhreadweek4.practice.conc01.sync;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: Counter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/9 00:10
 * @Since: 1.0.0
 */
public class Counter {
    private volatile int sum = 0;

    public synchronized void increment(){
        sum++;
    }

    public int getSum(){
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 1000000;
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++){
            counter.increment();
        }
        System.out.println("single thread: " + counter.getSum());


        final Counter counter2 = new Counter();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++){
                counter2.increment();
            }
            countDownLatch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++){
                counter2.increment();
            }
            countDownLatch.countDown();
        });

        thread1.start();
        thread2.start();

        countDownLatch.await();
        System.out.println("multiple thread: " + counter2.getSum());
    }
}