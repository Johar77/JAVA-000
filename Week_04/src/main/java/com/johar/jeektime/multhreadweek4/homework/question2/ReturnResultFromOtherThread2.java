package com.johar.jeektime.multhreadweek4.homework.question2;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @ClassName: ReturnResultFromOtherThread2
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/9 22:38
 * @Since: 1.0.0
 */
public class ReturnResultFromOtherThread2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Data data = new Data();
        CustomThread customThread = new CustomThread(data);
        ExecutorService service = Executors.newSingleThreadExecutor();
        synchronized (customThread) {
            Future<Data> future = service.submit(customThread, data);
            customThread.wait();
            System.out.println("result: " + future.get().sum);
        }
        service.shutdown();
    }

    private static class CustomThread implements Runnable{

        private Data num;
        public CustomThread(Data num){
            this.num = num;
        }

        @Override
        public synchronized void run() {
            for (int i = 0; i < 100_000_000; i++){
                num.sum += i;
            }
            this.notifyAll();
        }
    }

    private static class Data{
        Integer sum = 0;
    }
}

