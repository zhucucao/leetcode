package com.zym.leetcode.huisusuanfa;

import java.util.*;

/**
 *
     给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

     candidates 中的每个数字在每个组合中只能使用一次。

     说明：

     所有数字（包括目标数）都是正整数。
     解集不能包含重复的组合。
     示例 1:

     输入: candidates = [10,1,2,7,6,1,5], target = 8,
     所求解集为:
     [
     [1, 7],
     [1, 2, 5],
     [2, 6],
     [1, 1, 6]
     ]
     示例 2:

     输入: candidates = [2,5,2,1,2], target = 5,
     所求解集为:
     [
     [1,2,2],
     [5]
     ]
 */
public class Num40 {
//    public static void main(String[] args) {
//        int[] candidates = new int[]{10,1,2,7,6,1,5};
//        candidates = new int[]{2,5,2,1,2};
//        candidates = new int[]{1,2,2,2,5};
//        int index = 0;
//        int target = 8;
//        target = 5;
//        LinkedList<Integer> resList = new LinkedList<>();
//        LinkedList<LinkedList<Integer>> returnList = new LinkedList<>();
//        backTrack(candidates,index,target,resList,returnList);
//        System.out.println(returnList);
//        LinkedHashSet<LinkedList> hashSet = new LinkedHashSet<>();
//        for (LinkedList<Integer> linkedList : returnList) {
//            Collections.sort(linkedList, new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    return o1-o2;
//                }
//            });
//            hashSet.add(linkedList);
//        }
//        LinkedList<List<Integer>> lists = new LinkedList<>();
//        for (LinkedList linkedList : hashSet) {
//            lists.add(linkedList);
//        }
//        System.err.println(lists);
//    }

    public static void backTrack(int[] candidates,int index ,int target, LinkedList<Integer> resList, LinkedList<LinkedList<Integer>> returnList) {
        if(target ==0) {
            returnList.add(new LinkedList<>(resList));
            return;
        }
        if(target < 0) {
            return;
        }
        if(index > candidates.length -1) {
            return;
        }
        int currentNum = 0;
        for(int i = index ; i < candidates.length ; i++) {
            currentNum = candidates[i];
            resList.add(currentNum);
            backTrack(candidates,i+1,target - currentNum,resList,returnList);
            resList.removeLast();
        }

    }
    /**
     * 回溯加剪枝
     */


    public static void backTrackSpeed(int[] candidates,int index ,int target, LinkedList<Integer> resList, LinkedList<LinkedList<Integer>> returnList) {
        if(target ==0) {
            returnList.add(new LinkedList<>(resList));
            return;
        }
        int currentNum = 0;
        for(int i = index ; i < candidates.length ; i++) {
            currentNum = candidates[i];
            if(target - currentNum < 0) {
                break;
            }
            if(i > index && candidates[i -1] == candidates[i]) {
                continue;
            }
            resList.add(currentNum);
            backTrackSpeed(candidates,i+1,target - currentNum,resList,returnList);
            resList.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        candidates = new int[]{1,2,2,2,5};
        Arrays.sort(candidates);
        int index = 0;
        int target = 8;
        target = 5;
        LinkedList<Integer> resList = new LinkedList<>();
        LinkedList<LinkedList<Integer>> returnList = new LinkedList<>();
        backTrackSpeed(candidates,index,target,resList,returnList);
        System.err.println(returnList);
    }
}
