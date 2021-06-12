package com.johar.geektime.leetcode.twosum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TwoSum
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/12 14:50
 * @Since: 1.0.0
 */
public class TwoSum {

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2){
            throw new IllegalArgumentException("num must have more than two");
        }

        int[] result = { -1, -1};
        for (int i = 0; i < nums.length - 1; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    public int[] twpSumEx(int[] nums, int target){
        if (nums == null || nums.length < 2){
            throw new IllegalArgumentException("num must have more than two");
        }

        int[] result = { -1, -1};
        Map<Integer, Integer> map = new HashMap<>();
        int num;
        for (int i = 0; i < nums.length; i++){
            num = target - nums[i];
            if (map.containsKey(num)){
                result[0] = i;
                result[1] = map.get(num);
                return result;
            }
            map.put(nums[i], i);
        }

        return result;
    }
}