package com.johar.geektime.leetcode.twosum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @Test
    void twoSumThrow() {
        assertThrows(IllegalArgumentException.class, () ->{
            int[] nums = {1};
            int target = 1;
            TwoSum twoSum = new TwoSum();
            twoSum.twoSum(nums, target);
        });
    }

    @Test
    void twoSumSuccess(){
        int[] nums = {3,2,4};
        int target = 6;
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(nums, target);
        assertNotNull(result);
        assertEquals(result.length, 2);
        assertEquals(nums[result[0]] + nums[result[1]], target);
    }

    @Test
    void twoSumExSuccess(){
        int[] nums = {3,2,4};
        int target = 6;
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twpSumEx(nums, target);
        assertNotNull(result);
        assertEquals(result.length, 2);
        assertEquals(nums[result[0]] + nums[result[1]], target);
    }
}