package com.zym.leetcode.huisusuanfa;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
// TODO 有点像零钱兑换 322题
public class Num39 {
//    public static void main(String[] args) {
//        LinkedList<Integer> resList = new LinkedList<>();
//        LinkedList<LinkedList<Integer>> returnList = new LinkedList<>();
//        backTrack(new int[]{2,3,6,7}, 7, resList, returnList);
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


    public static void backTrack(int[] candidates, int target,LinkedList<Integer> resList,LinkedList<LinkedList<Integer>> returnList) {
        if(target == 0) {
            returnList.add(new LinkedList<>(resList));
            return;
        }
        for(int i = 0 ; i < candidates.length ; i++) {
               if(target - candidates[i] < 0) {
                   continue;
               }
               resList.add(candidates[i]);
               backTrack(candidates,target - candidates[i],resList,returnList);
               resList.removeLast();
        }
    }

    /**
     * https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * 回溯 加 剪枝
     */

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,5};
        Arrays.sort(nums);
        LinkedList<Integer> pathList = new LinkedList<>();
        List<List<Integer>> returnList = new LinkedList<>();
        backTrack(nums,0,8,pathList,returnList);
        System.out.println(returnList);
    }

    public static void backTrack(int[] nums,int begin,int target,LinkedList<Integer> pathList,List<List<Integer>> resList){
        if(target == 0) {
            resList.add(new LinkedList<Integer>(pathList));
            return;
        }
        // begin 防止重复答案。避免重复的方法 本层循环当中 下一层不在使用本层前面循环使用过的节点
        for(int i = begin ; i < nums.length ;i++) {
            // 数组事先从小到达排序 当target减去前面某个num 小于0时，则后面都小于0 不用再循环
            if(target - nums[i] < 0) {
                break;
            }
            // 选择
            pathList.add(nums[i]);
            backTrack(nums,i,target - nums[i],pathList,resList);
            // 回溯
            pathList.removeLast();
        }
    }
}
