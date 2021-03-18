package com.johar.bytecodedemo;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: Generator
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/2/19 14:42
 * @Since: 1.0.0
 */
public class Generator {

    public static void main(String[] args) throws IOException {

        ClassReader classReader = new ClassReader("com/johar/bytecodedemo/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        File file = new File("E:/JavaTrain/homework/JAVA-000/Week_01/bytecode-demo/target/classes/com/johar/bytecodedemo/Base.class");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
          fileOutputStream.write(data);
        }
        System.out.println("now generator cc success!");

        Base base = new Base();
        base.process();
    }
}