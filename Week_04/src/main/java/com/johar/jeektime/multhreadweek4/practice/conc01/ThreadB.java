package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: ThreadB
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 23:40
 * @Since: 1.0.0
 */
public class ThreadB implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is ThreadB");

        Thread currentThread = Thread.currentThread();

        System.out.println("Thread name: " + currentThread.getName());
        System.out.println("Group active thread counts: " + Thread.activeCount());
        System.out.println("Thread ID: " + currentThread.getId());
        System.out.println("Thread priority: " + currentThread.getPriority());
        System.out.println("Thread state: " + currentThread.getState());
        System.out.println("Thread Group: " + currentThread.getThreadGroup());
        System.out.println("Is active: " + currentThread.isAlive());
        System.out.println("Is daemon: " + currentThread.isDaemon());
    }
}