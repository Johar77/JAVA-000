package com.johar.jeektime.multhreadweek4.practice.conc01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: ThreadMain
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 23:41
 * @Since: 1.0.0
 */
public class ThreadMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("This is main thread");

        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.println("This is main");

        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("This is main thread: begin!");
        System.out.println("ThreadC return result: " + futureTask.get());
    }
}