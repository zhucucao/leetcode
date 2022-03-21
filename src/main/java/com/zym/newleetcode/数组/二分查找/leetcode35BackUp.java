package com.zym.newleetcode.数组.二分查找;

public class leetcode35BackUp {


    /**
     *
     示例 1:

     输入: nums = [1,3,5,6], target = 5
     输出: 2
     示例 2:

     输入: nums = [1,3,5,6], target = 2
     输出: 1
     示例 3:

     输入: nums = [1,3,5,6], target = 7
     输出: 4
     示例 4:

     输入: nums = [1,3,5,6], target = 0
     输出: 0
     示例 5:

     输入: nums = [1], target = 0
     输出: 0

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/search-insert-position
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     *  找目标元素得插入位置。无非两种情况
     *  1.目标元素在数组中，返回下标
     *  2.目标元素不在数组中
     *      又分为 2.1 目标元素<nums[0] 返回0
     *            2.2 目标元素>nums[n-1] 返回 n
     *            2.3 目标元素插入到某个数组元素后面
     *
     *   为什么目标元素不在数组中的时候，left的位置就是最终元素的插入位置？
     *      left = 0 right = n - 1 取的是两端都闭合的边界
     *      中间值以左都是小于中间值的元素，中间值以右都是大于中间值的元素
     *      中间值<target left= mid + 1  中间值>target right = mid - 1;
     *      二分的过程实际上是在寻找目标元素的一个边界，或是左边界，或是右边界
     *      因为目标元素不存在于数组中，所以左右指针最终肯定会指向同一元素或者左右指针差1个位置
     *      1.目标元素小于这个指针，右指针往左，结束循环，左指针是插入的位置
     *      2.目标元素大于这个指针区间，左指针往右，结束循环，左指针是插入的位置
     *      例如: 1 3 4 5  target = 2  首次循环：中位数是3，right左移指向1，左右指针重叠
     *      第二次中位数位1，小于目标元素，左指针右移，结束循环，左指针就是2要插入的位置
     *      1 3 4 5 6 target = 7 一样，右指针始终不动，左指针不断右移，直到结束循环，
     *      1 3 4 5 6 target = -2 ,左指针不动，右指针左移动，直到最后一次,left = 0 ,right = 1,mid = 0,
     *      大于target right = mid - 1 等于 -1 结束循环，left为插入位置
     *
     *      多画画，我也不是太明白
     *
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0 , right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target) {
                left = mid + 1;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {

    }
}
