package com.johar.jeektime.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @ClassName: ServerInterfacr
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 12:43
 * @Since: 1.0.0
 */
public class ServerInterface implements IInterface{

    public static void main(String[] args) throws InterruptedException {
        ServerInterface serverInterface = new ServerInterface();
        CustomInvocationHandler handler = new CustomInvocationHandler(serverInterface);
        IInterface serverInterface1 = (IInterface) Proxy.newProxyInstance(ServerInterface.class.getClassLoader(), new Class[] { IInterface.class}, handler);
        serverInterface1.handle(new Random().nextInt(20));
    }

    @Override
    public void handle(int i) throws InterruptedException {
        Thread.sleep(i * 1000);
        System.out.println(i);
    }
}