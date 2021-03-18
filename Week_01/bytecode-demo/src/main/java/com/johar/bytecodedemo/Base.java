package com.johar.bytecodedemo;

import java.lang.management.ManagementFactory;

/**
 * @ClassName: Base
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/19 14:41
 * @Since: 1.0.0
 */
public class Base {

    public static void process() {
        System.out.println("process");
    }

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        System.out.println("pid: --> " + s);
        while (true){
            try {
                Thread.sleep(500L);
            } catch (Exception e){
                break;
            }
            process();
        }
    }
}