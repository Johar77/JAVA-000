package com.johar.jeektime.jvmnettyweek2.question2;

import okhttp3.*;

import java.io.IOException;

/**
 * @ClassName: OkHttpDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/10/25 23:36
 * @Since: 1.0.0
 */
public class OkHttpDemo {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8808/test").build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("request is error: error code: " + response.code());
            }
            String responseBody = response.body().string();
            System.out.println("response: " + responseBody);
            
        } catch (IOException e) {
            System.out.println("request is error: " + e);
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("request is error: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("request is OK, response: " + response.body().string());
            }
        });
    }
}