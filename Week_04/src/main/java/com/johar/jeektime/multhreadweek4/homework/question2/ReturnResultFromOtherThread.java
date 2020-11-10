package com.johar.jeektime.multhreadweek4.homework.question2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: ReturnResultFromOtherThread
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 17:16
 * @Since: 1.0.0
 */
public class ReturnResultFromOtherThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1.方法FutureTask
        Integer result;
        CustomTask task = new CustomTask();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        new Thread(futureTask).start();
        result = futureTask.get();

        System.out.println("Result: " + result);
    }

    private static class CustomTask implements Callable<Integer> {
        @Override
        public Integer call() {
            int num = 0;
            for (int i = 0; i < 1000_000; i++){
                num++;
            }

            return num;
        }
    }
}