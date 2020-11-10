package com.johar.jeektime.multhreadweek4.homework.question2;

import java.util.concurrent.*;

/**
 * @ClassName: ReturnResultFromOtherThread5
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/10 08:44
 * @Since: 1.0.0
 */
public class ReturnResultFromOtherThread5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                 Thread.sleep(5000);
                 countDownLatch.countDown();
                return Thread.currentThread().getName();
            }
        });
        executorService.shutdown();

        countDownLatch.await();
        System.out.println(future.get());
    }
}