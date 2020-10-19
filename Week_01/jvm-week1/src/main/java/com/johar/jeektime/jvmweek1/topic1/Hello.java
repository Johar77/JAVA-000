package com.johar.jeektime.jvmweek1.topic1;

/**
 * @ClassName: Hello
 * @Description: 字节码分析demo
 * @Author: Johar
 * @Date: 2020/10/19 00:02
 * @Since: 1.0.0
 */
public class Hello {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int sum = a + b;
        System.out.println("a + b = " + sum);
        int sub = b - a;
        System.out.println("b - a = " + sub);
        int mul = a * b;
        System.out.println("a * b = " + mul);
        int div = a / b;
        System.out.println("a / b = " + div);
        int reminder = a % b;
        System.out.println("a % b = " + reminder);
        if (b > a){
            System.out.println(" b > a");
        }

        for (int i = 0; i < 10; i++){
            System.out.println("count: " + i);
        }
    }

}