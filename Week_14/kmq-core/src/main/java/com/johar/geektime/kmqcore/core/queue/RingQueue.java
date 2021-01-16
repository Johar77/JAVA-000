package com.johar.geektime.kmqcore.core.queue;

import java.io.Serializable;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: RingQueue
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 17:28
 * @Since: 1.0.0
 */
public class RingQueue<T> implements KmqQueue<T>, Serializable {

    private static final Long serialVersionUID = 1L;

    private volatile int capacity;
    private volatile int readIndex;
    private volatile int writeIndex;
    private T[] data;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public RingQueue(int capacity) {
        this.capacity = capacity;
        this.data = (T[])new Object[capacity];
        this.readIndex = 0;
        this.writeIndex = -1;
    }

    @Override
    public T poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return pollNext();
        } finally {
            lock.unlock();
        }
    }

    private T pollNext() throws InterruptedException {
        if (writeIndex < readIndex){
            notEmpty.await();
        }

        if (writeIndex >= readIndex) {
            T nextValue = data[readIndex % capacity];
            readIndex = (readIndex + 1) % capacity;
            return nextValue;
        }

        return null;
    }

    @Override
    public T poll(long timeout, TimeUnit timeUnit) throws InterruptedException {
        lock.lockInterruptibly();
        try{
            return pollNext(timeout, timeUnit);
        } finally {
            lock.unlock();
        }
    }

    private T pollNext(long timeout, TimeUnit timeUnit) throws InterruptedException {
        if (writeIndex < readIndex){
            notEmpty.await(timeout, timeUnit);
        }

        if (writeIndex >= readIndex) {
            T nextValue = data[readIndex % capacity];
            readIndex = (readIndex + 1) % capacity;
            return nextValue;
        }

        return null;
    }

    @Override
    public boolean offer(T item) throws InterruptedException {
        if (item == null){
            throw new NullPointerException("item can't be null");
        }

        lock.lockInterruptibly();
        try{
            return offerNext(item);
        } finally {
            lock.unlock();
        }
    }

    private boolean offerNext(T item) throws InterruptedException {
        if ((writeIndex - readIndex) + 1 >= capacity){
            notFull.await();
        }

        if (writeIndex - readIndex + 1 < capacity) {
            writeIndex = (writeIndex + 1) % capacity;
            this.data[writeIndex] = item;
            notEmpty.signalAll();
            return true;
        }

        return false;
    }
}