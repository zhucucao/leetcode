package com.zym.leetcode.huisusuanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Number90 {
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        int begin = 0;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> pathList = new LinkedList<>();
        ArrayList<List<Integer>> returnList = new ArrayList<>();
//        backTrack(nums,begin,used,pathList,returnList);
        backTrackSpeed(nums,begin,pathList,returnList);
        System.out.println(returnList);

    }

    public static void backTrack(int[] nums, int begin, boolean[] used, LinkedList<Integer> pathList, List<List<Integer>> returnList) {
        returnList.add(new ArrayList<>(pathList));
        for(int i = begin; i < nums.length; i++) {
            if(used[i]) {
                continue;
            }
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }
            pathList.add(nums[i]);
            System.out.println("递归前:" + pathList);
            used[i] = true;
            backTrack(nums,i,used,pathList,returnList);
            pathList.removeLast();
            System.out.println("递归后:" + pathList);
            used[i] = false;
        }

    }

    public static void backTrackSpeed(int[] nums, int begin, LinkedList<Integer> pathList, List<List<Integer>> returnList) {
        returnList.add(new ArrayList<>(pathList));
        for(int i = begin; i < nums.length; i++) {

            if(i > begin && nums[i] == nums[i-1]) {
                continue;
            }
            pathList.add(nums[i]);
            backTrackSpeed(nums,i+1,pathList,returnList);
            pathList.removeLast();
        }
    }

}

