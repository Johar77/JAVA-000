package com.johar.jeektime.singleton;

/**
 * @ClassName: EagerInitializedSingleton
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 14:34
 * @Since: 1.0.0
 */
public class EagerInitializedSingleton {

    private EagerInitializedSingleton() {
    }

    private static EagerInitializedSingleton instance = new EagerInitializedSingleton();

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
}