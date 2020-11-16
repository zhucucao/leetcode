package com.zym.leetcode.huisusuanfa;


import java.util.*;
import java.util.stream.Collectors;

/**
 *          47. 全排列 II
 *         给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 *         示例:
 *
 *         输入: [1,1,2]
 *         输出:
 *         [
 *         [1,1,2],
 *         [1,2,1],
 *         [2,1,1]
 *         ]
 *
 *               1                                      1                                        2
 *      1        1                  2                ........                   1               1         2
 *      x     1  1  2           1   1    2                                1     1    2
 *           x   x  ok          ok  x    x                               x     ok   x
 */

public class Number47 {
//    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
//        Arrays.sort(nums);
//        LinkedList<Integer> pathList = new LinkedList<>();
//        LinkedList<LinkedList<Integer>> returnList = new LinkedList<>();
//        LinkedList<Integer> indexList = new LinkedList<>();
//        backTrackSpeed(nums,false,pathList,returnList,indexList);
//        System.out.println(returnList);
//    }

    public static void backTrackSpeed(int[] nums, boolean isContinue, LinkedList<Integer> pathList, LinkedList<LinkedList<Integer>> returnList,LinkedList<Integer> indexList) {
        if(pathList.size() == nums.length) {
            returnList.add(new LinkedList<>(pathList));
            isContinue = false;
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(indexList.indexOf(i) != -1) {
                isContinue = true;
                continue;
            }
            if(!isContinue && i > 0 &&(nums[i] == nums[i-1])) {
                continue;
            }
            indexList.add(i);
            pathList.add(nums[i]);
            backTrackSpeed(nums,isContinue,pathList,returnList,indexList);
            pathList.removeLast();
            indexList.removeLast();
            isContinue = false;
        }
    }

    /**
     * https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        Arrays.sort(nums);
        boolean[] userd = new boolean[nums.length];
        LinkedList<Integer> pathList = new LinkedList<>();
        ArrayList<List<Integer>> returnList = new ArrayList<>();
        speedBackTrack(nums,userd,pathList,returnList);
        System.out.println(returnList);
    }

    public static void speedBackTrack(int nums[],boolean []used,LinkedList<Integer>pathList,List<List<Integer>> returnList) {
        if(pathList.size() == nums.length) {
            returnList.add(new LinkedList<>(pathList));
            return;
        }

        for(int i = 0; i < nums.length ;i++) {
            if(used[i]) {
                continue;
            }
            if(i > 0 && nums[i-1] == nums[i] && !used[i-1]) {
                continue;
            }
            pathList.add(nums[i]);
            used[i] = true;
            speedBackTrack(nums,used,pathList,returnList);
            used[i] = false;
            pathList.removeLast();
        }

    }

}
