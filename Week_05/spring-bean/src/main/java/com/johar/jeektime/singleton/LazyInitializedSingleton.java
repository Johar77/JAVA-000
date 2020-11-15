package com.johar.jeektime.singleton;

/**
 * @ClassName: LazyInitializedSingleton
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 14:36
 * @Since: 1.0.0
 */
public class LazyInitializedSingleton {

    private LazyInitializedSingleton(){

    }

    private static LazyInitializedSingleton instance;

    public static LazyInitializedSingleton getInstance(){
        if (instance == null){
            instance = new LazyInitializedSingleton();
        }

        return instance;
    }
}