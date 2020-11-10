package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: ThreadMain2
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 23:41
 * @Since: 1.0.0
 */
public class ThreadMain2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadB threadB = new ThreadB();
        for (int i = 0; i < 5; i++){
            new Thread(threadB, "TheadB-" + i).start();
        }

        Thread.sleep(10000);

        Thread mainThread = Thread.currentThread();
        System.out.println("Thread name: " + mainThread.getName());
        System.out.println("Group active thread counts: " + Thread.activeCount());
        System.out.println("Thread ID: " + mainThread.getId());
        System.out.println("Thread priority: " + mainThread.getPriority());
        System.out.println("Thread state: " + mainThread.getState());
        System.out.println("Thread Group: " + mainThread.getThreadGroup());
        System.out.println("Is active: " + mainThread.isAlive());
        System.out.println("Is daemon: " + mainThread.isDaemon());
    }
}