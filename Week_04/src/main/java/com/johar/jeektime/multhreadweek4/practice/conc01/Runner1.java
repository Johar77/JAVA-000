package com.johar.jeektime.multhreadweek4.practice.conc01;

/**
 * @ClassName: Runner1
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 22:58
 * @Since: 1.0.0
 */
public class Runner1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println("进入Runner1运行状态-------> " + i);
        }
    }
}