package com.zym.leetcode.huadongchuangkou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * 示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Num438 {
    public static void main(String[] args) {
//        windowFindAnagrams("cbaebabacd","abc");
        List<Integer> list = windowSolve("cbaebabacd", "abc");
        System.out.println(list);
    }

    public static List<Integer> windowSolve(String s,String p) {
        LinkedList<Integer> matchResList = new LinkedList<>();
        int left=0,right=0;
        int match = 0;
        int needEleKeySize = 0;
        char[] needCharArr = new char[128];
        char[] windowCharArr = new char[128];
        for(char c : p.toCharArray()) {
            needCharArr[c]++;
            if(needCharArr[c] == 1) {
                needEleKeySize++;
            }
        }
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            right++;
            if(needCharArr[rightChar] > 0) {
                windowCharArr[rightChar]++;
                if(windowCharArr[rightChar] == needCharArr[rightChar]) {
                    match++;
                }
            }
            while (right - left >= p.length()) {
                if(needEleKeySize == match) {
                    matchResList.add(left);
                }
                char lefthChar = s.charAt(left);
                if (needCharArr[lefthChar] > 0) {
                    if(windowCharArr[lefthChar] == needCharArr[lefthChar]) {
                        match--;
                    }
                    windowCharArr[lefthChar]--;
                }
                left++;
            }

        }
        return matchResList;

    }
//    s: "cbaebabacd" p: "abc"
    public static List<Integer> windowFindAnagrams(String s, String p) {
        LinkedList<Integer> resultList = new LinkedList<>();
        int left = 0;
        int right = 0;
        int match = 0;
        HashMap<String, Integer> needMap = new HashMap<>();
        HashMap<String, Integer> windowMap = new HashMap<>();
        char[] needChar = p.toCharArray();
        for (char c : needChar) {
            if(needMap.containsKey(c+"")) {
                needMap.put(c+"",needMap.get(c+"")+1);
            } else {
                needMap.put(c+"",1);
            }
        }
        char[] sCharArr = s.toCharArray();
        while (right < s.length()){
            char rightChar = sCharArr[right];
            right++;
            if(needMap.containsKey(rightChar+"")) {
                if(windowMap.containsKey(rightChar+"")) {
                    windowMap.put(rightChar+"",windowMap.get(rightChar+"")+1);
                } else {
                    windowMap.put(rightChar+"",1);
                }
                if(windowMap.get(rightChar+"").equals(needMap.get(rightChar+""))) {
                    match++;
                }
            }
            while (right - left >= p.length()) {
                if(match == needMap.keySet().size()) {
                    resultList.add(left);
                }
                char leftChar = sCharArr[left];
                if(needMap.containsKey(leftChar+"")) {
                    if(windowMap.get(leftChar+"").equals(needMap.get(leftChar+""))) {
                        match--;
                    }
                    windowMap.put(leftChar+"",windowMap.get(leftChar+"")-1);
                }
                left++;
            }
        }
        return  resultList;
    }
}
