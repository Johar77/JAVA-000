package com.johar.jeektime.nettyjeektimeweek3.gateway.filter.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johar.jeektime.nettyjeektimeweek3.gateway.common.BaseResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @ClassName: MatchHeaderHttpResponseFilter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/7 23:54
 * @Since: 1.0.0
 */
@Slf4j
public class MatchHeaderHttpResponseFilter implements IHttpResponseFilter{
    @Override
    public int getOrderId() {
        return 0;
    }

    @Override
    public void filter(final FullHttpResponse fullHttpResponse, final ChannelHandlerContext ctx) throws JsonProcessingException, UnsupportedEncodingException {
        String charset = "UTF-8";
        if (!fullHttpResponse.headers().contains("Content-Type")){
            fullHttpResponse.headers().set("Content-Type", "application/json;charset=utf-8");
        } else {
            String contentType = fullHttpResponse.headers().get("Content-Type");
            String[] arr = contentType.split(";");
            if (arr.length >= 2){
                charset = arr[1].split("=")[1];
            }
        }

        byte[] bytes = new byte[fullHttpResponse.content().readableBytes()];
        fullHttpResponse.content().readBytes(bytes);
        String content = new String(bytes, Charset.forName(charset));
        log.info("Before Response content: {}", content);
        BaseResult<String> result = new BaseResult<>(content);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        log.info("Before Response content: {}", json);
        byte[] data = json.getBytes(charset);
        fullHttpResponse.content().writeBytes(Unpooled.wrappedBuffer(data));
        fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
    }
}