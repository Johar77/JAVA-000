package com.johar.geektime.leetcode.median;

/**
 * @ClassName: MedianOfTwoSortedArrays
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/13 09:21
 * @Since: 1.0.0
 */
public class MedianOfTwoSortedArrays {

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums2 == null)
                || (nums1.length == 0 && nums2.length == 0)){
            throw new IllegalArgumentException("nums can't be null");
        }

        double result = 0.0D;
        int length = nums1.length + nums2.length;
        int m1 = 0,m2 = 0;
        boolean evenNum = length % 2 == 0;
        if (nums1.length == 0){
            if (!evenNum){
                return nums2[length >> 1];
            } else {
                return (nums2[(length >> 1) - 1] + nums2[length >> 1]) / 2.0D;
            }
        }

        if (nums2.length == 0){
            if (!evenNum){
                return nums1[length >> 1];
            } else {
                return (nums1[(length >> 1) - 1] + nums1[length >> 1]) / 2.0D;
            }
        }

        int i = 0, j =0, index = -1, temp = 0;
        while (i < nums1.length || j < nums2.length){
            index++;
            if (i >= nums1.length){
                temp = nums2[j];
                j++;
            } else if (j >= nums2.length){
                temp = nums1[i];
                i++;
            } else {
                if (nums1[i] < nums2[j]) {
                    temp = nums1[i];
                    i++;
                } else {
                    temp = nums2[j];
                    j++;
                }
            }


            if (evenNum){
                if (index == (length >> 1) -1){
                    m1 = temp;
                } else if (index == (length >> 1)){
                    m2 = temp;
                    return (m1 + m2) / 2.0D;
                }
            } else {
                if (index == (length >> 1)){
                    return temp;
                }
            }
        }


        return result;
    }
}