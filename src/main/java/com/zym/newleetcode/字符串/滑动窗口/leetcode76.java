package com.zym.newleetcode.字符串.滑动窗口;

import java.util.HashMap;

public class leetcode76 {
    /**
     * 示例 1：
     *
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 示例 2：
     *
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 示例 3:
     *
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String s = new leetcode76().minWindow("a", "aa");
        System.out.println(s);
    }

    /**
        一个compare map用来初始化目标串t里面的key的个数以及key出现的次数
        另外一个record map用来记录遍历串s中t中出现的元素以及个数
        双指针: slow fast 初始为数组0下标。随着fast指针往后遍历记录t中出现的key以及key出现的次数
        当record map与 compare map的key的个数相同，并且 record map中的key的值 大于等于 compare map里key的值的时候(该双指针的窗口满足条件)
        停止fast指针往后，slow指针开始往后移动，直到 record map中的key的个数 小于 compare中key的个数 或者 record中的key的值小于 compare中key的值
        则 fast指针重新往后移动。以此循环

        2.核心思想就是一个双指针维护着一个窗口 这个窗口中的元素必须包含给出的目标串中的所有元素以及元素的个数
        ，随后窗口开始缩小，直到不满足目标串中元素或者目标串中元素的个数后。随后继续扩大窗口直到满足。依此循环

        原始串:ACBDAABCEF  目标串:AABC
     */
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = t.toCharArray();
        for (char currentChar : chars) {
            compareMap.put(currentChar, compareMap.getOrDefault(currentChar, 0) + 1);
        }

        HashMap<Character, Integer> recordHashMap = new HashMap<>();
        int slow = 0;
        int minLen = Integer.MAX_VALUE;
        String minString = "";
        char[] loopCharArr = s.toCharArray();
        for (int fast = 0; fast < loopCharArr.length; fast++) {
            if (compareMap.containsKey(loopCharArr[fast])) {
                recordHashMap.put(loopCharArr[fast], recordHashMap.getOrDefault(loopCharArr[fast], 0) + 1);
            }
            while (stopFast(compareMap, recordHashMap)) {
                if (minLen > fast - slow + 1) {
                    minString = s.substring(slow, fast + 1);
                    minLen = Math.min(minLen, fast - slow + 1);
                }
                if (recordHashMap.containsKey(loopCharArr[slow])) {
                    recordHashMap.put(loopCharArr[slow], recordHashMap.get(loopCharArr[slow]) - 1);
                    if (recordHashMap.get(loopCharArr[slow]) == 0) {
                        recordHashMap.remove(loopCharArr[slow]);
                    }
                }
                slow++;
            }
        }

        return minString;
    }

    public Boolean stopFast(HashMap<Character, Integer> compareMap, HashMap<Character, Integer> recordHashMap) {
        Boolean stopFast = true;
        if (recordHashMap.size() == compareMap.size()) {
            for (Character character : compareMap.keySet()) {
                if (compareMap.get(character) > recordHashMap.get(character)) {
                    return false;
                }
            }
        } else {
            stopFast = false;
        }
        return stopFast;
    }

//    作者：user4006En
//    链接：https://leetcode-cn.com/problems/minimum-window-substring/solution/tu-jie-leetcode-yi-dao-ti-xue-dong-hua-d-2rah/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 滑动窗口模板
     */
    public void template() {
//        int[] arrayT = new int[52];
//        for (char c : t.toCharArray()) {
//            arrayT[getIndex(c)]++;
//        }
//        int l = 0;
//        int r = 0;
//        int valid = 0;
//        while (r < s.length()) {
//            char cur = s.charAt(r);
//            r++;
//            // 逻辑代码,窗口内数据更新
//
//            while(needLeftMove()){
//                // 将要移除的元素
//                char d = s.charAt(l);
//                l++;
//                // 逻辑代码,窗口内数据更新
//            }
//        }
//
//        return "";
    }




}
