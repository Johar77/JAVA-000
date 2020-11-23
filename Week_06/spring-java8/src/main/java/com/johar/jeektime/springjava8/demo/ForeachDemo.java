package com.johar.jeektime.springjava8.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ForeachDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/20 22:36
 * @Since: 1.0.0
 */
public class ForeachDemo {

    private int x = 1;

    public static void main(String[] args) {
        ForeachDemo demo = new ForeachDemo();
        demo.test();
        System.out.println(demo.x);
    }

    private void test(){
        List list = Arrays.asList(1, 2);
        int y = 2;
        list.forEach(e -> {
            x = x + (Integer) e;
            //y = x;
        });
    }
}