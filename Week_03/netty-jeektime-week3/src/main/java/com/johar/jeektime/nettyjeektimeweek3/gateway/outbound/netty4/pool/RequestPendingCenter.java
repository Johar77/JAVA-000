package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.pool;

import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.netty4.FullHttpResponseFuture;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: RequestPendingCenter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 15:19
 * @Since: 1.0.0
 */
public class RequestPendingCenter {

    private RequestPendingCenter(){}
    private ConcurrentHashMap<String, FullHttpResponseFuture> futureMap = new ConcurrentHashMap<>();

    public void add(String requestID, FullHttpResponseFuture httpResponseFuture){
        futureMap.put(requestID, httpResponseFuture);
    }

    public void set(String requestID, FullHttpResponse fullHttpResponse){
        FullHttpResponseFuture future = this.futureMap.get(requestID);
        if (future == null){
            throw new IllegalArgumentException("can't find http response future");
        }

        future.setSuccess(fullHttpResponse);
        this.futureMap.remove(requestID);
    }

    public FullHttpResponseFuture get(String requestID){
        return this.futureMap.get(requestID);
    }

    public static RequestPendingCenter getInstance(){
        return RequestPendingCenterHolder.INSTANCE;
    }

    private static class RequestPendingCenterHolder{
        private static RequestPendingCenter INSTANCE = new RequestPendingCenter();
    }
}