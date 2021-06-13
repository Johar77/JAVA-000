package com.johar.geektime.leetcode.median;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianOfTwoSortedArraysTest {

    @Test
    void findMedianSortedArrays1() {
        int[] num1 = {1, 3};
        int[] num2 = {2};
        MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
        assertEquals(median.findMedianSortedArrays(num1, num2), 2.0D);
    }

    @Test
    void findMedianSortedArrays2() {
        int[] num1 = {1, 2};
        int[] num2 = {3, 4};
        MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
        assertEquals(median.findMedianSortedArrays(num1, num2), 2.5);
    }

    @Test
    void findMedianSortedArrays3() {
        int[] num1 = {0, 0};
        int[] num2 = {0, 0};
        MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
        assertEquals(median.findMedianSortedArrays(num1, num2), 0.0D);
    }

    @Test
    void findMedianSortedArrays4() {
        int[] num1 = {};
        int[] num2 = {1};
        MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
        assertEquals(median.findMedianSortedArrays(num1, num2), 1.0D);
    }

    @Test
    void findMedianSortedArrays5() {
        int[] num1 = {2};
        int[] num2 = {};
        MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
        assertEquals(median.findMedianSortedArrays(num1, num2), 2.0D);
    }
}