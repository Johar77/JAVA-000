package com.johar.jeektime.springjava8.demo;

import java.util.function.Function;

/**
 * @ClassName: FunctionDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/20 23:08
 * @Since: 1.0.0
 */
public class FunctionDemo {

    public static void main(String[] args) {
        FunctionDemo demo = new FunctionDemo();
        System.out.println(demo.compute(1, value -> 2 * value));

        System.out.println(demo.compute(2, value -> 5 + value));

        System.out.println(demo.compute(3, Integer::intValue));

        System.out.println(demo.convert(4, value -> value + "hello, world"));

        System.out.println(demo.convert(5, demo::toString1));

        System.out.println(demo.convert(5, Integer::toBinaryString));

        System.out.println(demo.compute(3, v -> v * 2, v -> v *v));

        System.out.println(demo.compute2(3, v -> v * 2, v -> v * v));
    }

    public int compute(int a, Function<Integer, Integer> function, Function<Integer, Integer> before){
        return function.compose(before).apply(a);
    }

    public int compute2(int a, Function<Integer, Integer> function, Function<Integer, Integer> before){
        return function.andThen(before).apply(a);
    }

    public int compute(int a, Function<Integer, Integer> function){
        return function.apply(a);
    }

    public String convert(int a, Function<Integer, String> function){
        return function.apply(a);
    }

    public String toString1(Integer integer){
        return integer.toString();
    }

    public int method(int a){
        return a + 1;
    }

    public int method2(int a){
        return a * 5;
    }

    public int method3(int a){
        return a * a;
    }
}