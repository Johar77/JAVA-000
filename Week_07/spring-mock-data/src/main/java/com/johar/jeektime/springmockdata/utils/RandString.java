package com.johar.jeektime.springmockdata.utils;

import java.util.Random;

/**
 * @ClassName: RandString
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/2 09:05
 * @Since: 1.0.0
 */
public class RandString {

    private static final String charters = "abcdefghijklmnopqrstuvwxyz";

    public static String randNum(int length){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        stringBuilder.append(random.nextInt(8) + 1);
        for (int i = 0; i < length - 1; i++){
            stringBuilder.append(random.nextInt(9));
        }

        return stringBuilder.toString();
    }

    public static String randCharter(int length){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            stringBuilder.append(charters.charAt(random.nextInt(charters.length())));
        }

        return stringBuilder.toString();
    }
}