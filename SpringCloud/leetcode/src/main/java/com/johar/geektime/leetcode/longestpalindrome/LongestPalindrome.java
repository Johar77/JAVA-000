package com.johar.geektime.leetcode.longestpalindrome;

import org.springframework.util.StringUtils;

/**
 * @ClassName: LongestPalindrome
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/13 18:31
 * @Since: 1.0.0
 */
public class LongestPalindrome {

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (!StringUtils.hasText(s)){
            throw new IllegalArgumentException("s is null");
        }

        int length = s.length();
        for (int i = length; i > 0; i--){
            for (int j = 0; j + i <= length; j++){
                if (isPalindrome(s.substring(j,j+i))){
                    return s.substring(j,j+i);
                }
            }
        }

        return s;
    }

    private boolean isPalindrome(String s){
        if (s.length()  == 1){
            return true;
        }

        int length = s.length();
        int num = 0;
        if (length % 2 == 0){
            num = length / 2 - 1;
        } else {
            num = length / 2;
        }

        for (int i = 0; i <= num; i++){
            if (s.charAt(i) != s.charAt(length - 1 - i)){
                return false;
            }
        }

        return true;
    }
}