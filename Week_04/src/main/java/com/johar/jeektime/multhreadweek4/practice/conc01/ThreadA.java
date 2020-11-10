package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: ThreadA
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 23:40
 * @Since: 1.0.0
 */
public class ThreadA  extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是线程A");
    }
}