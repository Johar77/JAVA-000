package com.johar.jeektime.multhreadweek4.homework.question2;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: ReturnResultFromOtherThread3
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/10 08:21
 * @Since: 1.0.0
 */
public class ReturnResultFromOtherThread3 {

    public static void main(String[] args) throws InterruptedException {
        final Data data = new Data();
        Thread thread = new Thread(() -> {
            try {
                data.name = Thread.currentThread().getName();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.join();
        System.out.println(data.name);
    }

}

class Data {
    public String name;
}