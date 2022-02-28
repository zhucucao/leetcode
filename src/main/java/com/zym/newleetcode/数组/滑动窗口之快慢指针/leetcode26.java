package com.zym.newleetcode.数组.滑动窗口之快慢指针;
/**
 * 双指针(快慢指针) 数组
 */
public class leetcode26 {
    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for(int fast = 1 ; fast < nums.length ; fast++) {
            if(nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
