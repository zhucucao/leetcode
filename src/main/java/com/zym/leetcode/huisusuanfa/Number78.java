package com.zym.leetcode.huisusuanfa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 *         说明：解集不能包含重复的子集。
 *
 *         示例:
 *
 *         输入: nums = [1,2,3]
 *         输出:
 *         [
 *         [3],
 *         [1],
 *         [2],
 *         [1,2,3],
 *         [1,3],
 *         [2,3],
 *         [1,2],
 *         []
 *         ]
 */


public class Number78 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        LinkedList<Integer> pathList = new LinkedList<>();
        ArrayList<List<Integer>> returnList = new ArrayList<>();
        subset(nums,0,pathList,returnList);
        System.out.println(returnList);
    }

    public static void subset(int[] nums, int begin, LinkedList<Integer> pathList, List<List<Integer>> returnList) {
        returnList.add(new LinkedList<>(pathList));

        for(int i = begin ; i < nums.length; i++) {
            if(pathList.indexOf(nums[i])!=-1) {
                continue;
            }
            System.out.println("递归前:" + pathList);
            pathList.add(nums[i]);
            subset(nums,i,pathList,returnList);
            System.out.println("递归后:" + pathList);
            pathList.removeLast();
        }
    }

    public static void subsetSpeed(int[] nums, int begin, LinkedList<Integer> pathList, List<List<Integer>> returnList) {
        returnList.add(new LinkedList<>(pathList));

        for(int i = begin ; i < nums.length; i++) {
            pathList.add(nums[i]);
            subset(nums,i+1,pathList,returnList);
            pathList.removeLast();
        }
    }

//    作者：dao-fa-zi-ran-2
//    链接：https://leetcode-cn.com/problems/subsets/solution/er-jin-zhi-wei-zhu-ge-mei-ju-dfssan-chong-si-lu-9c/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//    public static void main(String[] args) {
//        int[] nums = {1,2,3};
//        List<List<Integer>> enumerate = enumerate(nums);
//        System.out.println(enumerate);
//    }

    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        int loopCount = 0;
        for (Integer n : nums) {
            int size = res.size();
            System.out.println("本次一共循环" + size + "次");
            for (int i = 0; i < size; i++) {
                loopCount++;
                System.out.println("第" + loopCount + "次：添加前是" + res);
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                res.add(newSub);
                System.out.println("是" + n + "这个数");
                System.out.println("本次添加的为" + newSub);
                System.out.println("添加后为" + res);
            }
        }
        return res;
    }


}
