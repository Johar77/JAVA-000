package com.johar.bytecodedemo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @ClassName: InstrumentDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/19 23:37
 * @Since: 1.0.0
 */
public class InstrumentDemo implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transformering " + className);
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("com.johar.bytecodedemo.Base");
            CtMethod ctMethod = ctClass.getDeclaredMethod("process");
            ctMethod.insertBefore("{System.out.println(\"start\");}");
            ctMethod.insertAfter("{System.out.println(\"after\");}");
            return ctClass.toBytecode();
        } catch (Exception e){

        }
        return null;
    }
}