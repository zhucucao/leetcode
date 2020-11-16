package com.zym.leetcode.huisusuanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 *         示例:
 *
 *         输入: [1,2,3]
 *         输出:
 *         [
 *         [1,2,3],
 *         [1,3,2],
 *         [2,1,3],
 *         [2,3,1],
 *         [3,1,2],
 *         [3,2,1]
 *         ]
 *
 *         来源：力扣（LeetCode）
 *         链接：https://leetcode-cn.com/problems/permutations
 *         著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Number46 {
//    public static void main(String[] args) {
//        LinkedList<List<Integer>> returnList = new LinkedList<>();
//        LinkedList<Integer> resLinkedList = new LinkedList<>();
//        per(new int[]{1,2,3},returnList,resLinkedList);
//        System.out.println(returnList);
//    }


    public static void per(int[] nums,List<List<Integer>> returnList,LinkedList<Integer> resLinkedList) {
        for(int i = 0 ; i < nums.length ; i++) {
            if(resLinkedList.size() == nums.length) {
                returnList.add(new LinkedList<>(resLinkedList));
                return;
            }
            int currentNum = nums[i];
            if(resLinkedList.indexOf(currentNum) != -1) {
                continue;
            }
            resLinkedList.add(currentNum);
            per(nums,returnList,resLinkedList);
            resLinkedList.removeLast();
        }
    }

    /**
     * https://www.bilibili.com/video/BV1nC4y1W7ah?p=2
     * @param nums
     * @param resList
     * @param returnList
     */
    public void backTrack(int[] nums,LinkedList<Integer> resList,LinkedList<List<Integer>> returnList){
        if(resList.size() == nums.length) {
            returnList.add(new LinkedList<>(resList));
            return;
        }
        for (int num : nums) {
            if (resList.indexOf(num) != -1) {
                continue;
            }
            resList.add(num);
            backTrack(nums, resList, returnList);
            resList.removeLast();
        }
    }


    public static void backTrackSpeed(int[] nums,LinkedList<Integer> pathList,List<List<Integer>> returnList) {
        if(pathList.size() == nums.length) {
            returnList.add(new LinkedList<>(pathList));
            return;
        }
        for(int num :nums) {
            if(pathList.indexOf(num) != -1) {
                continue;
            }
            pathList.add(num);
            backTrackSpeed( nums,pathList,returnList);
            pathList.removeLast();
        }

    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        LinkedList<Integer> pathList = new LinkedList<>();
        LinkedList<List<Integer>> returnList = new LinkedList<>();
        backTrackSpeed(ints,pathList,returnList);
        System.out.println(returnList);

    }







}
