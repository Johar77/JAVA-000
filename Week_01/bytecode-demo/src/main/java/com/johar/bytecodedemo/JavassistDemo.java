package com.johar.bytecodedemo;

import javassist.*;

/**
 * @ClassName: JavassistDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/19 15:23
 * @Since: 1.0.0
 */
public class JavassistDemo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.johar.bytecodedemo.Base");
        CtMethod ctMethod = ctClass.getDeclaredMethod("process");
        ctMethod.insertBefore("{System.out.println(\"start\");}");
        ctMethod.insertAfter("{System.out.println(\"after\");}");
        Class clazz = ctClass.toClass();
        Base h = (Base) clazz.newInstance();
        h.process();
    }
}