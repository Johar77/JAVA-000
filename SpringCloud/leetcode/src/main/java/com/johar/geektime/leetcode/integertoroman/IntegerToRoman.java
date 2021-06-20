package com.johar.geektime.leetcode.integertoroman;

/**
 * @ClassName: IntegerToRoman
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/19 00:39
 * @Since: 1.0.0
 */
public class IntegerToRoman {

    public String intToRoman(int num) {
        String[] one =      {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] twq =      {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] three =    {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] four =     {"M", "MM", "MMM"};

        if (num < 1 || num > 3999){
            throw new IllegalArgumentException("out of index");
        }

        StringBuilder result = new StringBuilder();
        int flag = num / 1000;
        if (flag > 0){
            result.append(four[flag -1]);
        }

        flag = num % 1000 / 100;
        if (flag > 0){
            result.append(three[flag - 1]);
        }

        flag = num % 100 / 10;
        if (flag > 0){
            result.append(twq[flag - 1]);
        }

        flag = num % 10;
        if (flag > 0){
            result.append(one[flag - 1]);
        }

        return result.toString();
    }
}