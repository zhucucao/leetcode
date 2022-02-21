package com.zym.newleetcode.数组.滑动窗口;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class leetcode209 {
    public static void main(String[] args) {
        int i = minSubArrayLen(11, new int[]{1,2,3,4,5});
        System.out.println(i);
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int slow = 0;
        double minLen = Math.pow(10, 5);
        for(int fast = 0; fast < nums.length ;) {
            if(slow > fast) {
                break;
            }
            if(slow == fast && nums[fast] == target) {
                return 1;
            }

            int sumCount = 0;
            for(int j = slow ; j <= fast ; j++) {
                sumCount+=nums[j];
            }
            if(sumCount >= target) {
                minLen = minLen > fast-slow + 1 ? fast-slow + 1 : minLen;
                slow++;
            }else if(sumCount < target) {
                fast++;
            }
        }
        return (int)minLen == (int)Math.pow(10,5) ? 0 : (int) minLen;
    }
}
