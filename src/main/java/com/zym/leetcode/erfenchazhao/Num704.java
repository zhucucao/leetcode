package com.zym.leetcode.erfenchazhao;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 *
 * 提示：
 *
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 *
 * 来源：力扣（LeetCode）ddd
 * 链接：https://leetcode-cn.com/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/er-fen-cha-zhao-xiang-jie
 */
public class Num704 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5,6,7};
        int target = 4;
//        search(nums,target);
        int i = search2(nums, target);
        System.out.println("::" + i);
    }

    // 「力扣」第 704 题：二分查找
    //  链接：https://leetcode-cn.com/leetbook/read/learning-algorithms-with-leetcode/xs41qg/

    public static int search2(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        // 目标元素可能存在在区间 [left, right]
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.err.println("L:" + left +  " R:" + right + " M:" + mid);
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索区间是 [left, mid]
                right = mid ;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println(mid+":"+left);
          if(nums[mid] == target) {
              return mid;
          }else if(nums[mid] < target) {
              left = mid + 1;
          }else if(nums[mid] > target) {
              right = mid -1;
          }
        }
        return -1;
    }



}
