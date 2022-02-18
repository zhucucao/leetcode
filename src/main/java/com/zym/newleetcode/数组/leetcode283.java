package com.zym.newleetcode.数组;

/**
 * 双指针(快慢指针) 数组
 * https://leetcode-cn.com/problems/move-zeroes/submissions/
 */
public class leetcode283 {
    public static void main(String[] args) {

    }
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for(int fast = 0 ; fast < nums.length ; fast++) {
            if(nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
        }
    }
}
