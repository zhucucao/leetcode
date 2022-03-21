package com.zym.newleetcode.数组.二分查找;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class leetcode34 {
    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *
     *  [1] 1
     *  0,0
     */
    public int[] searchRange(int[] nums, int target) {
        int minIndex = -1;
        int maxIndex = -1;
        for(int i = 0 ; i < nums.length ; i++) {
            if(minIndex != -1 && nums[i] == target) {
                maxIndex = i;
            }else if(nums[i] == target) {
                minIndex = i;
            }
        }
        if(minIndex != -1 && maxIndex == -1) {
            maxIndex = minIndex;
        }
        return new int[]{minIndex,maxIndex};
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int target = 1;
        int[] ints = new leetcode34().searchRange(nums,target);
        System.out.println(Arrays.toString(ints));
    }
}
