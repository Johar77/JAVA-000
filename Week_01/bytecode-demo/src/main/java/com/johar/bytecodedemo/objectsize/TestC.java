package com.johar.bytecodedemo.objectsize;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

/**
 * @ClassName: TestC
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/3/5 09:03
 * @Since: 1.0.0
 */
public class TestC {

    private int x;
    private int y;

    public static void main(String[] args) {
        //System.out.println(ObjectSizeFetcher.getObjectSize(new TestC()));
        System.out.println(ObjectSizeCalculator.getObjectSize(new TestC()));
    }
}