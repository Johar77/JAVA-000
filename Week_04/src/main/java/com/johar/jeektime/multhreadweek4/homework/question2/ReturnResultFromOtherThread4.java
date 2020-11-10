package com.johar.jeektime.multhreadweek4.homework.question2;

import java.util.concurrent.*;

/**
 * @ClassName: ReturnResultFromOtherThread4
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/10 08:37
 * @Since: 1.0.0
 */
public class ReturnResultFromOtherThread4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return Thread.currentThread().getName();
            }
        });
        executorService.shutdown();
        while (!future.isDone()){
            Thread.sleep(1000);
        }
        System.out.println(future.get());
    }
}