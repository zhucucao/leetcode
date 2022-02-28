package com.zym.newleetcode.数组.滑动窗口之快慢指针;

/**
 * 双指针(快慢指针) 数组
 * 慢指针实际是用来标记元素 快指针实际上是用来探路查询的作用
 */
public class leecode27 {
    /**给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/remove-element
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     **/
    public static void main(String[] args) {
        int i = removeEle2(new int[]{0,1,2,2,3,0,4,2}, 2);
        System.err.println(i);
    }

    /**
     *
     输入：nums = [3,2,2,3], val = 3
     输出：2, nums = [2,2]
     解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/remove-element
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     [0,1,2,2,3,0,4,2]
     2
     0,1,4,0,3
     */

    public static int removeEle2(int[] nums, int val) {
        int slow = 0;
        for(int fast = 0 ; fast < nums.length ; fast++) {
            if(nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
