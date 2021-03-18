package com.johar.bytecodedemo.objectsize;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.lang.instrument.Instrumentation;

/**
 * @ClassName: HeadSize
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/3/5 08:41
 * @Since: 1.0.0
 */
public class HeadSize {

    public static void main(String[] args) {
        Integer num = 5;
        System.out.println(ObjectSizeCalculator.getObjectSize(num));

        Long count = 5L;
        System.out.println(ObjectSizeCalculator.getObjectSize(count));
    }
}