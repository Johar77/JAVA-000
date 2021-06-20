package com.johar.geektime.leetcode.mostwater;

/**
 * @ClassName: MostWater
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/19 00:03
 * @Since: 1.0.0
 */
public class MostWater {

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，
     * 垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length -1, result = 0, area = 0;
        while (i < j){
            result = height[i] < height[j] ?
                    Math.max(result, (j - i) * height[i++]) :
                    Math.max(result, (j - i) * height[j--]);
        }

        return result;
    }
}