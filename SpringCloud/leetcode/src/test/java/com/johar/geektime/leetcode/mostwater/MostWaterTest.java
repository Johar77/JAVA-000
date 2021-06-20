package com.johar.geektime.leetcode.mostwater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MostWaterTest {

    @Test
    void maxArea1() {
        MostWater water = new MostWater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        assertEquals(49, water.maxArea(height));
    }

    @Test
    void maxArea2() {
        MostWater water = new MostWater();
        int[] height = {1,1};
        assertEquals(1, water.maxArea(height));
    }

    @Test
    void maxArea3() {
        MostWater water = new MostWater();
        int[] height = {4,3,2,1,4};
        assertEquals(16, water.maxArea(height));
    }

    @Test
    void maxArea4() {
        MostWater water = new MostWater();
        int[] height = {1,2,1};
        assertEquals(2, water.maxArea(height));
    }
}