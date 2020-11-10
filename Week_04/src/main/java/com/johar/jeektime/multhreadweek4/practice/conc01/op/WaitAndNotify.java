package com.johar.jeektime.multhreadweek4.practice.conc01.op;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: WaitAndNotify
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/9 08:29
 * @Since: 1.0.0
 */
public class WaitAndNotify {

    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(() ->{
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() ->{
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}

class MethodClass{
    private final int MAX_COUNT = 20;
    private int producctCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true){
            System.out.println(Thread.currentThread().getName() + "::::run:::" + producctCount);
            Thread.sleep(10);
            if (producctCount > MAX_COUNT){
                System.out.println("货仓已满，不必再生产");
                wait();
            } else {
                producctCount++;
            }

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true){
            System.out.println(Thread.currentThread().getName() + ":::run:::" + producctCount);
            Thread.sleep(10);
            if (producctCount <= 0){
                System.out.println("货仓已无货， 无法消费");
                wait();
            } else {
                producctCount--;
            }
            notifyAll();
        }
    }
}