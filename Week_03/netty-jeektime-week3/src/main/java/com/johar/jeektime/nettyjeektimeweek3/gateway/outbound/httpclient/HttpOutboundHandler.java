package com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.httpclient;

import com.johar.jeektime.nettyjeektimeweek3.gateway.common.HttpThreadPool;
import com.johar.jeektime.nettyjeektimeweek3.gateway.outbound.IOutboundHandler;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.IHttpEndpointRouter;
import com.johar.jeektime.nettyjeektimeweek3.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.*;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ClassName: HttpOutbounderHandler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/2 22:47
 * @Since: 1.0.0
 */
@Slf4j
public class HttpOutboundHandler implements IOutboundHandler {

    private String backendUrl;
    private final CloseableHttpAsyncClient httpClient;

    public HttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl;
        HttpClientConnectionManager connManager;
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setRcvBufSize(32 * 1024)
                .build();

        httpClient = HttpAsyncClients.custom()
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((httpResponse, httpContext) -> 6000))
                .build();
        httpClient.start();
    }

    @Override
    public void handle(final FullHttpRequest request, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + request.uri();
        HttpThreadPool.getHttpThreadPool().submit(() -> {
            doExecute(request, ctx, url);
        });
    }

    @Override
    public void setBackendUrl(String backendUrl) {
        this.backendUrl = backendUrl;
    }

    private void doExecute(final FullHttpRequest request, final ChannelHandlerContext ctx, final String backendUrl) {
        try {
            final HttpRequestBase httpUriRequest = copyHttpRequest(request, backendUrl);
            httpClient.execute(httpUriRequest, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse httpResponse) {
                    handleResponse(request, ctx, httpResponse);
                }

                @Override
                public void failed(Exception e) {
                    httpUriRequest.abort();
                }

                @Override
                public void cancelled() {
                    httpUriRequest.abort();
                }
            });
        } catch (NoSuchMethodException e) {
            log.error("doExecute error: ", e);
        }
    }

    private void handleResponse(final FullHttpRequest request, final ChannelHandlerContext ctx, final HttpResponse httpResponse){
        FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.NO_CONTENT);
        try{
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            HttpResponseStatus status = new HttpResponseStatus(httpResponse.getStatusLine().getStatusCode(),
                    httpResponse.getStatusLine().getReasonPhrase());
            response = new DefaultFullHttpResponse(request.protocolVersion(), status, Unpooled.wrappedBuffer(body));
            for (Header header : httpResponse.getAllHeaders()){
                response.headers().set(header.getName(), header.getValue());
            }
        } catch (IOException e) {
            log.error("handleResponse error: ", e);
        } finally {
            if (request != null){
                if (!HttpUtil.isKeepAlive(request)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    private HttpRequestBase copyHttpRequest(final FullHttpRequest request, final String url) throws NoSuchMethodException {
        HttpRequestBase httpRequest;
        // set http method
        switch (request.method().name()){
            case HttpGet.METHOD_NAME:
                httpRequest = new HttpGet(url);
                break;
            case HttpDelete.METHOD_NAME:
                httpRequest = new HttpDelete(url);
                break;
            case HttpHead.METHOD_NAME:
                httpRequest = new HttpHead(url);
                break;
            case HttpOptions.METHOD_NAME:
                httpRequest = new HttpOptions(url);
                break;
            case HttpPatch.METHOD_NAME:
                httpRequest = new HttpPatch(url);
                break;
            case HttpPost.METHOD_NAME:
                httpRequest = new HttpPost(url);
                break;
            case HttpPut.METHOD_NAME:
                httpRequest = new HttpPut(url);
                break;
            case HttpTrace.METHOD_NAME:
                httpRequest = new HttpTrace(url);
                break;
            default:
                throw new NoSuchMethodException("no such http method: " + request.method());
        }

        // set http version
        ProtocolVersion version = new ProtocolVersion(request.protocolVersion().protocolName(),
                request.protocolVersion().majorVersion(),
                request.protocolVersion().minorVersion());
        httpRequest.setProtocolVersion(version);

        // set http head
        request.headers().forEach((header) -> {
            httpRequest.setHeader(header.getKey(), header.getValue());
        });

        // set http content
        if (!(httpRequest instanceof HttpEntityEnclosingRequestBase)){
            return httpRequest;
        }

        ByteBuf content = request.content();
        if (content == null || content.readableBytes() == 0){
            return httpRequest;
        }

        HttpEntityEnclosingRequestBase httpEntityEnclosingRequest = (HttpEntityEnclosingRequestBase)httpRequest;
        httpEntityEnclosingRequest.setEntity(new ByteArrayEntity(content.array()));
        return  httpEntityEnclosingRequest;
    }
}