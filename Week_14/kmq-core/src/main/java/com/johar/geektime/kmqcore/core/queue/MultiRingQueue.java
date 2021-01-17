package com.johar.geektime.kmqcore.core.queue;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: MutiRingQueue
 * @Description:
 * @Author: Johar
 * @Date: 2021/1/17 11:29
 * @Since: 1.0.0
 */
public class MultiRingQueue<T> implements KmqQueue<T>, Serializable {
    private static final Long serialVersionUID = 1L;

    private T[] data;
    private int writePtr;
    private int capacity;
    private ReentrantLock lock = new ReentrantLock();

    public MultiRingQueue(int capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity];
        this.writePtr = -1;
    }

    @Override
    public T poll() throws InterruptedException {
        throw new RuntimeException("don't support");
    }

    @Override
    public T poll(long timeout, TimeUnit timeUnit) throws InterruptedException {
        throw new RuntimeException("don't support");
    }

    public T get(int index){
        return getElement(index);
    }

    private T getElement(int index){
        int realIndex = index % capacity;
        return data[realIndex];
    }

    public T get(int index, long timeout, TimeUnit unit) throws InterruptedException {
        T result;
        long nanos = unit.toNanos(timeout);
        LocalDateTime endTime = LocalDateTime.now().plusNanos(nanos);
        while ((result = getElement(index)) == null){
            if (nanos <= 0){
                break;
            }

            if (LocalDateTime.now().isAfter(endTime)){
                break;
            }

            Thread.sleep(10);
        }

        return result;
    }



    @Override
    public boolean offer(T item) throws InterruptedException {
        if (item == null) {
            throw new RuntimeException("item can't be null.");
        }
        lock.lockInterruptibly();
        try {
            writePtr = (writePtr + 1) % capacity;
            this.data[writePtr] = item;
            return true;
        } finally {
            lock.unlock();
        }
    }
}