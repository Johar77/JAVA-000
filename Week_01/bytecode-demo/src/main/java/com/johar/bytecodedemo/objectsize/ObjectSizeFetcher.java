package com.johar.bytecodedemo.objectsize;

import java.lang.instrument.Instrumentation;

/**
 * @ClassName: ObjectSizeFetcher
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/3/5 09:01
 * @Since: 1.0.0
 */
public class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation ins){
        instrumentation = ins;
    }

    public static long getObjectSize(Object obj){
        return instrumentation.getObjectSize(obj);
    }
}