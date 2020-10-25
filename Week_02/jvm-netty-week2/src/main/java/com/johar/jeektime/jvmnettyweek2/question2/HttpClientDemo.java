package com.johar.jeektime.jvmnettyweek2.question2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ClassName: HttpClientDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/10/25 23:35
 * @Since: 1.0.0
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8808/test");
            String responseBody = httpClient.execute(httpGet, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    System.out.println("request is error, error code: " + status);
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });
            System.out.println("request is ok, response: " + responseBody);
        } catch (IOException e) {
            System.out.println("request is error: " + e);
        }
    }
}