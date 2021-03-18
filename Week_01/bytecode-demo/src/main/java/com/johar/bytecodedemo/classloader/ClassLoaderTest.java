package com.johar.bytecodedemo.classloader;

/**
 * @ClassName: ClassLoaderTest
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/27 16:19
 * @Since: 1.0.0
 */
public class ClassLoaderTest {

    static int num = 100;

    {
        System.out.println("class block");
    }

    static {
        System.out.println("static class block");
    }

    public ClassLoaderTest() {
        System.out.println("ClassLoaderTest() ");
    }

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        System.out.println("test()");
        System.out.println(num);
    }
}