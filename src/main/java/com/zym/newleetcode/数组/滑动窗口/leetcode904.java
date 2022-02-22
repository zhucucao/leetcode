package com.zym.newleetcode.数组.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 904. 水果成篮
 * https://leetcode-cn.com/problems/fruit-into-baskets/
 * 其实就是找同一个窗口内有且只有两种元素且两种元素数据最多的这么一个窗口
 */
public class leetcode904 {


    /**
     * 示例 1：
     *
     * 输入：fruits = [1,2,1]
     * 输出：3
     * 解释：可以采摘全部 3 棵树。
     *
     * 示例 2：
     * 输入：fruits = [0,1,2,2]
     * 输出：3
     * 解释：可以采摘 [1,2,2] 这三棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
     *
     * 示例 3：
     * 输入：fruits = [1,2,3,2,2]
     * 输出：4
     * 解释：可以采摘 [2,3,2,2] 这四棵树。
     * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
     *
     *
     * 示例 4：
     * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
     * 输出：5
     * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
     */

    /**
     * 其实就是找同一个窗口内有且只有两种元素且两种元素个数最多的这么一个窗口
     * 或者说该数组中 只有两种元素且元素个数最多的一个连续数组
     *
     *  1 2 1         0 1 2 2    3,3,3,1,2,1,1,2,3,3,4
     *  1 2 1           1 2 2          1,2,1,1,2
     */
    public static int totalFruit(int[] fruits) {
        int slow = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Boolean slowIncrease = false;
        for (int fast = 0; fast < fruits.length; ) {
            if(!slowIncrease) {
                if (!hashMap.containsKey(fruits[fast])) {
                    hashMap.put(fruits[fast], 1);
                } else {
                    hashMap.put(fruits[fast], hashMap.get(fruits[fast]) + 1);
                }
            }
            if (hashMap.size() == 2) {
                maxLen = Math.max(maxLen, fast - slow + 1);
            }
            if (hashMap.size() > 2) {
                hashMap.put(fruits[slow],hashMap.get(fruits[slow]) - 1);
                if(hashMap.get(fruits[slow]) == 0) {
                    hashMap.remove(fruits[slow]);
                }
                slow++;
                slowIncrease = true;
            } else {
                fast++;
                slowIncrease = false;
            }
        }
        if(hashMap.size() == 1 && maxLen == 0) {
            maxLen = hashMap.get(hashMap.keySet().toArray()[0]);
        }
        return maxLen;
    }

/**
 *           1 2 1           0 1 2 2       3,3,3,1,2,1,1,2,3,3,4
             1 2 1             1 2 2             1,2,1,1,2
 *  */
    public static int totalFruit2(int[] fruits) {
        int slow = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int fast = 0 ; fast < fruits.length ; fast++) {
            if (!hashMap.containsKey(fruits[fast])) {
                hashMap.put(fruits[fast], 1);
            } else {
                hashMap.put(fruits[fast], hashMap.get(fruits[fast]) + 1);
            }
            if(hashMap.size() == 2) {
                maxLen = Math.max(maxLen,fast - slow + 1);
            }
            while (hashMap.size() > 2) {
                hashMap.put(fruits[slow],hashMap.get(fruits[slow]) - 1);
                if(hashMap.get(fruits[slow]) == 0) {
                    hashMap.remove(fruits[slow]);
                }
                slow++;
            }
        }
        if(hashMap.size() == 1 && maxLen == 0) {
            maxLen = hashMap.get(hashMap.keySet().toArray()[0]);
        }
        return maxLen;
    }

    public static int totalFruit3(int[] fruits) {
        int slow = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int fast = 0 ; fast < fruits.length ; fast++) {
            if (!hashMap.containsKey(fruits[fast])) {
                hashMap.put(fruits[fast], 1);
            } else {
                hashMap.put(fruits[fast], hashMap.get(fruits[fast]) + 1);
            }
            while (hashMap.size() > 2) {
                hashMap.put(fruits[slow],hashMap.get(fruits[slow]) - 1);
                if(hashMap.get(fruits[slow]) == 0) {
                    hashMap.remove(fruits[slow]);
                }
                slow++;
            }
            maxLen = Math.max(maxLen,fast - slow + 1);
        }
        if(hashMap.size() == 1 && maxLen == 0) {
            maxLen = hashMap.get(hashMap.keySet().toArray()[0]);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        int i = totalFruit2(new int[]{0,1,2,1});
        System.out.println(i);
    }
}
