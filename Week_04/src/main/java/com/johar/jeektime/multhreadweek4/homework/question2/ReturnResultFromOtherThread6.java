package com.johar.jeektime.multhreadweek4.homework.question2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: ReturnResultFromOtherThread6
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/10 08:53
 * @Since: 1.0.0
 */
public class ReturnResultFromOtherThread6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        });

        System.out.println(completableFuture.get());
    }
}