package com.johar.jeektime.springjava8.demo;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.cache.*;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: GuavaDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/21 09:33
 * @Since: 1.0.0
 */
public class GuavaDemo {

    public static void main(String[] args) throws InterruptedException {
        orderingTest();

        checkArgumentTest(0);

        //cacheTest();

        futureTest();
    }

    private static void futureTest() {
        ExecutorService delegate = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(delegate);
        ListenableFuture future = executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                return new Object();
            }
        });

        Futures.addCallback(future, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object o) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failed");
            }
        }, executorService);
        executorService.shutdown();
    }

    private static void cacheTest() throws InterruptedException {
        Cache<Integer, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .removalListener(new MyListener())
                .build();


        loadingCache.put(1, "hello");
        loadingCache.put(2, "hello");
        loadingCache.put(3, "hello");
        Thread.sleep(1000 * 1);
        loadingCache.put(4, "hello");
        loadingCache.put(5, "hello");
        loadingCache.put(6, "hello");
        Thread.sleep(1000 * 1);
        loadingCache.cleanUp();
        Thread.sleep(1000 * 6);
    }

    private static void checkArgumentTest(int i) {
        Preconditions.checkArgument(i >= 0,
                "Argument i was %s, but expected negative", i);
    }

    private static void orderingTest() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(@Nullable String s, @Nullable String t1) {
                return Ints.compare(s.length(), t1.length());
            }
        };

        List<String> names = new ArrayList<>();
        names.add("Johar");
        names.add("Lynn");
        names.add("Jin");
        byLengthOrdering.sortedCopy(names).stream().forEach(System.out::println);
    }

    private static class MyListener implements RemovalListener<Integer, String> {

        @Override
        public void onRemoval(RemovalNotification<Integer, String> removalNotification) {
            System.out.println("Remove: {" + removalNotification.getKey() + ", " + removalNotification.getValue() + " }");
        }
    }
}