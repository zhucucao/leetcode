package com.zym.newleetcode.数组.二分查找;

public class leetcode35 {

    /**
     *
     示例 1:
     输入: nums = [1,3,5,6], target = 5
     输出: 2
     */

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 4;
        int i = new leetcode35().searchInsert2(nums, target);
        System.out.println(i);
    }
    // https://leetcode-cn.com/problems/search-insert-position/solution/hua-jie-suan-fa-35-sou-suo-cha-ru-wei-zhi-by-guanp/1079722
    public int searchInsert2(int[] nums, int target) {
        int left = 0 , right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0 , right = nums.length - 1;
        int mid = 0;
        while (left <= right) {

            mid = left + (right - left) / 2;

            int midValue = nums[mid];

            if(right - left == 1) {
                if(nums[left] < target && target < nums[right]) {
                    return left + 1;
                }
            }


            if(target > midValue) {
                left = mid + 1;
                if(left <= right && nums[left] > target) {
                    return mid + 1;
                }
            }else if(target < midValue) {
                right = mid - 1;
                if(left <= right && nums[right] < target) {
                    return right + 1;
                }
            }else if(target == midValue){
                return mid;
            }


            if(left > right ) {
                if(right == nums.length - 1) {
                    return right + 1;
                }else if(left == 0) {
                    return 0;
                }
            }
        }
        return 0;
    }
}
