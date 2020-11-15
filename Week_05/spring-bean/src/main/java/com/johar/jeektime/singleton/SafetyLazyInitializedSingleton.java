package com.johar.jeektime.singleton;

/**
 * @ClassName: SafetyLazyInitializedSingleton
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 14:38
 * @Since: 1.0.0
 */
public class SafetyLazyInitializedSingleton {
    private SafetyLazyInitializedSingleton() {

    }

    private static SafetyLazyInitializedSingleton instance;

    public static synchronized SafetyLazyInitializedSingleton getInstance(){
        if (instance == null){
            instance = new SafetyLazyInitializedSingleton();
        }

        return instance;
    }
}