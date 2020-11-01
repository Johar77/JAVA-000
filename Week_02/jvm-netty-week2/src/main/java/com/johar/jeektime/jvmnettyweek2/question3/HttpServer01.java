package com.johar.jeektime.jvmnettyweek2.question3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: HttpServer01
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/10/31 10:59
 * @Since: 1.0.0
 */
@Slf4j
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true){
            try{
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e){
                log.error("Accept socket error: ", e);
            }
        }
    }

    private static void service(Socket socket) {
        try{
            Thread.sleep(20);
            try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type:text/html;charset=utf-8");
                String body = "hello, nio";

                // 增加Content-Length，方便请求客户端通过body的长度解析body内容
                int contentLength = body.getBytes("UTF-8").length;
                printWriter.println("Content-Length:" + contentLength);
                // socket连接结束，短连接
                printWriter.println("Connection:close");

                printWriter.println();
                printWriter.write(body);
            } catch (IOException e){
                log.error("getOutPutStream error: ", e);
            }
        } catch (InterruptedException e) {
            log.error("Thread sleep error: ", e);
        }
    }
}