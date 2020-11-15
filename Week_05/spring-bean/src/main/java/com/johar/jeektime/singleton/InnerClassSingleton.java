package com.johar.jeektime.singleton;

/**
 * @ClassName: InnerClassSingleton
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 14:43
 * @Since: 1.0.0
 */
public class InnerClassSingleton {
    private InnerClassSingleton(){

    }

    private static class InnerClassSingletonHolder{
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance(){
        return InnerClassSingletonHolder.instance;
    }
}