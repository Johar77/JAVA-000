package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: Runner3
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 22:58
 * @Since: 1.0.0
 */
public class Runner3 implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            System.out.println("Runner3.run InterruptedException ======> " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("Runner3.run InterruptedException ======> " + Thread.currentThread().isInterrupted());
        }
    }
}