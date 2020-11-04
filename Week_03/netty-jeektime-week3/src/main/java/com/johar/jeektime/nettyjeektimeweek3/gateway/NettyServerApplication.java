package com.johar.jeektime.nettyjeektimeweek3.gateway;

import com.johar.jeektime.nettyjeektimeweek3.gateway.inbound.HttpInBoundServer;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: NettyServerApplication
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 08:28
 * @Since: 1.0.0
 */
@Slf4j
public class NettyServerApplication {
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort", "8888");

        int port = Integer.parseInt(proxyPort);
        log.info("{} {} starting...", GATEWAY_NAME, GATEWAY_VERSION);
        HttpInBoundServer httpServer = new HttpInBoundServer(port);
        log.info("{} {} stated at http://localhost:{} for server: {}", GATEWAY_NAME, GATEWAY_VERSION, proxyPort);
        try{
            httpServer.run();
        } catch (Exception e){
            log.error("Netty Server run error: ", e);
        }
    }
}