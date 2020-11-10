package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: DaemonThread
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 21:56
 * @Since: 1.0.0
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread t = Thread.currentThread();
                System.out.println("当前线程： " + t.getName());
            }
        };
        // 由于thread 是Daemon线程，程序直接退出，不会打印任何信息
//        Thread thread = new Thread(task);
//        thread.setName("test-thread-1");
//        thread.setDaemon(true);
//        thread.start();

        // 1:修改为非Daemon线程
//        Thread thread = new Thread(task);
//        thread.setName("test-thread-1");
//        thread.setDaemon(false);
//        thread.start();

        task.wait();
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();
        thread.join();
    }
}