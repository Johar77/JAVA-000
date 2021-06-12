package com.johar.geektime.leetcode.longestcharacters;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: LongestCharacters
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/12 16:27
 * @Since: 1.0.0
 */
public class LongestCharacters {

    /**
     *  给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")){
            return 0;
        }

        int lenth = s.length();
        int result = 1;
        char c;
        Set<Character> set = new HashSet<>(128);
        for (int i = 0; i < lenth - result; i++){
            set.clear();
            set.add(s.charAt(i));
            int count = 1;
            for (int j = i + 1; j < lenth; j++){
                // 终止条件
                c = s.charAt(j);
                if (set.contains(c)) {
                    if (count > result){
                        result = count;
                        if (lenth - 1 - i <= result){
                            return result;
                        }
                    }
                    break;
                } else {
                    set.add(c);
                    count++;
                }
            }

            if (count > result) {
                result = count;
                if (lenth - 1 - i <= result) {
                    return result;
                }
            }
        }

        return result;
    }
}