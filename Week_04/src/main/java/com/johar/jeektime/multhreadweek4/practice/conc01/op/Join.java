package com.johar.jeektime.multhreadweek4.practice.conc01.op;

/**
 * @ClassName: Join
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/9 08:28
 * @Since: 1.0.0
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread("Thread1 -- ");
        myThread.start();

        synchronized (myThread){
            for (int i = 0; i < 100; i++){
                if (i == 20){
                    myThread.join();
                }

                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }



}

class MyThread extends Thread{

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 100; i++){
                System.out.println(name + i);
            }
        }
    }
}