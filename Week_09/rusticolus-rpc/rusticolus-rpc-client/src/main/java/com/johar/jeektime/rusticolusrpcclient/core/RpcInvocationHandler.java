package com.johar.jeektime.rusticolusrpcclient.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.johar.jeektime.rusticolusrpccommon.api.RpcReponse;
import com.johar.jeektime.rusticolusrpccommon.api.RpcRequest;
import com.johar.jeektime.rusticolusrpccommon.exception.RpcException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @ClassName: RpcInvocationHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 16:45
 * @Since: 1.0.0
 */
@Slf4j
public class RpcInvocationHandler implements InvocationHandler {
    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ParserConfig.getGlobalInstance().addAccept("com.johar.geektime.rusticolusrpcdemoapi.domain.");
    }

    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    private final Class<?> serviceClass;
    private final String url;
    public <T> RpcInvocationHandler(Class<T> serviceClass, String url){
        this.serviceClass = serviceClass;
        this.url = url;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws RpcException {
        RpcRequest request = new RpcRequest();
        request.setMethod(method.getName());
        request.setServiceClass(this.serviceClass.getName());
        request.setParams(args);

        RpcReponse response = new RpcReponse();
        try {
            response = post(request, url);
        } catch (IOException e) {
            log.error("call remote method error", e);
            response.setStatus(false);
            response.setException(new RpcException(e));
        }

        if (response.isStatus()){
             return JSON.parse(response.getResult().toString());
        }

        throw response.getException();
    }

    private RpcReponse post(RpcRequest request, String url) throws IOException {
        String body = JSON.toJSONString(request);
        log.info("RpcInvocationHandler send message: {}", body);
        OkHttpClient httpClient = new OkHttpClient();
        final Request request1 = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON_TYPE, body))
                .build();
        String response = httpClient.newCall(request1).execute().body().string();
        log.info("RpcInvocationHandler receive message: {}", response);
        return JSON.parseObject(response, RpcReponse.class);
    }
}