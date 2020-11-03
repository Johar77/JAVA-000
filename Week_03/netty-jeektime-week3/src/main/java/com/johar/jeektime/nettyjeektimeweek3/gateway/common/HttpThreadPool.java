package com.johar.jeektime.nettyjeektimeweek3.gateway.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * @ClassName: ThreadPool
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 22:58
 * @Since: 1.0.0
 */
@Slf4j
public class HttpThreadPool {

    private ExecutorService executorService;

    private HttpThreadPool(){
        int cores = Runtime.getRuntime().availableProcessors();
        int keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        executorService = new ThreadPoolExecutor(cores,
                cores * 10,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new CustomizableThreadFactory("proxyService"),
                rejectedExecutionHandler);
    }

    private static class HttpThreadPoolHoler{
        private static HttpThreadPool INSTANCE = new HttpThreadPool();
    }

    public static ExecutorService getHttpThreadPool(){
        return HttpThreadPoolHoler.INSTANCE.executorService;
    }
}