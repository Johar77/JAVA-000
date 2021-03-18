package com.johar.bytecodedemo;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @ClassName: TestAgent
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/19 23:43
 * @Since: 1.0.0
 */
public class TestAgent {

    public static void agentmain(String args, Instrumentation instrumentation){
        instrumentation.addTransformer(new InstrumentDemo(), true);
        try{
            instrumentation.retransformClasses(Base.class);
            System.out.println("Agent load done. ");
        } catch (UnmodifiableClassException e) {
            System.out.println("Agent load failed. ");
        }
    }
}