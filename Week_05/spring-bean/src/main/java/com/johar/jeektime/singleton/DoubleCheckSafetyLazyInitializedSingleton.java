package com.johar.jeektime.singleton;

/**
 * @ClassName: DoubleCheckSafetyLazyInitializedSingleton
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 14:40
 * @Since: 1.0.0
 */
public class DoubleCheckSafetyLazyInitializedSingleton {
    private DoubleCheckSafetyLazyInitializedSingleton(){

    }

    private static volatile DoubleCheckSafetyLazyInitializedSingleton instance;

    private static final Object locker = new Object();

    public static DoubleCheckSafetyLazyInitializedSingleton getInstance(){
        if (instance == null){
            synchronized (locker){
                if (instance == null){
                    instance = new DoubleCheckSafetyLazyInitializedSingleton();
                }
            }
        }

        return instance;
    }
}