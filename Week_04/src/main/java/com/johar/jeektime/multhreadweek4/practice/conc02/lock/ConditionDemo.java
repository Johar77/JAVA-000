package com.johar.jeektime.multhreadweek4.practice.conc02.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ConditionDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/10 23:04
 * @Since: 1.0.0
 */
public class ConditionDemo {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[20];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try{
            while (count == items.length){
                System.out.println("notFull.await()");
                notFull.await();
            }
            items[putptr] = x;
            System.out.println("put: " + x);
            if (++putptr == items.length) {
                putptr = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try{
            while (count == 0){
                System.out.println("notEmpty.await()");
                notEmpty.await();
            }
            Object o = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            count--;
            notFull.signal();
            System.out.println("take " + o);
            return o;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
       for (int i = 0; i < 1000_000; i++){
           service.submit(new Runnable() {
               @Override
               public void run() {
                   try {
                       demo.put((Object) Thread.currentThread().getName());
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           });

           if (i % 10 == 0) {
               service.submit(new Callable<Object>() {
                   @Override
                   public Object call() throws Exception {
                       return demo.take();
                   }
               });
           }
       }
       service.shutdown();

    }
}