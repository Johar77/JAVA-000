package com.johar.bytecodedemo.classloader;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/27 16:33
 * @Since: 1.0.0
 */
public class Test {

    //static Test Test = new Test();

    static int amount = 112;

    int price = 110;

    static {
        System.out.println("静态代码块");
        System.out.println("amount=" + amount);
    }

    {
        System.out.println("普通代码块");
        System.out.println("price=" + price +",amount=" + amount);
    }

    Test() {
        System.out.println("构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    public static void main(String[] args) {
        //staticFunction();
        ClassLoaderTest.test();
    }

    public static void staticFunction(){
        System.out.println("静态方法");
    }
}