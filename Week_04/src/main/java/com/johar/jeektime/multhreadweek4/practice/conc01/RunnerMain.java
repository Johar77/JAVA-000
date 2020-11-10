package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: RunnerMain
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 22:58
 * @Since: 1.0.0
 */
public class RunnerMain {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner1());
        Thread thread2 = new Thread(new Runner2());
        Thread thread3 = new Thread(new Runner3());

        thread1.start();
        thread2.start();
        thread3.start();

        thread2.interrupt();
        thread3.interrupt();

        System.out.println(Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeCount());
        Thread.currentThread().getThreadGroup().getParent().list();
    }
}