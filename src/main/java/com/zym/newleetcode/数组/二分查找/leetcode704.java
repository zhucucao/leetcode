package com.zym.newleetcode.数组.二分查找;

// 二分查找
public class leetcode704 {
    public static void main(String[] args) {
        int[] nums = {5};
        int target = 5;
        int search = new leetcode704().search(nums,target);
        System.out.println(search);
    }
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            int midValue = nums[mid];
            if(target < midValue) {
                right = mid - 1;
            }else if(target > midValue) {
                left = mid + 1;
            }else {
                return mid;
            }
            mid = left + (right - left) / 2;
        }
        return -1;
    }
}
