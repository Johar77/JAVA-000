package com.johar.jeektime.jvmweek1.topic2;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * @ClassName: HelloClassLoader
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/10/18 16:12
 * @Since: 1.0.0
 */
@Slf4j
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Class<?> hello = new HelloClassLoader().findClass("Hello");
            Method method = hello.getMethod("hello");
            method.setAccessible(true);
            method.invoke(hello.newInstance());
        } catch (ClassNotFoundException e) {
            log.error("find Hello file error", e);
        } catch (NoSuchMethodException e) {
            log.error("create hello method error", e);
        } catch (IllegalAccessException e) {
            log.error("invoke hello method error", e);
        } catch (InvocationTargetException e) {
            log.error("invoke hello method error", e);
        } catch (InstantiationException e) {
            log.error("create Hello instance error", e);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = "src/main/resources/Hello.xlass";
        try {
            byte[] bytes = Files.readAllBytes(new File(filePath).toPath());
            for (int i = 0; i < bytes.length; i++){
                bytes[i] = (byte)(255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            log.error("read Hello.xclass error", e);
        }
        return null;
    }
}