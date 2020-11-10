package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: Runner2
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 22:58
 * @Since: 1.0.0
 */
public class Runner2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println("进入Runner2运行状态--------> " + i);
        }

        boolean result = Thread.currentThread().isInterrupted();

        boolean result1 = Thread.interrupted();

        boolean result2 = Thread.currentThread().isInterrupted();

        System.out.println("Runner2.run result ====> " + result);
        System.out.println("Runner2.run result1 ====> " + result1);
        System.out.println("Runner2.run result2 ====> " + result2);
    }
}