package com.zym.leetcode.huadongchuangkou;

import jdk.nashorn.internal.ir.CallNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class Number3 {
    public static void main(String[] args) {
//        lengthOfLongestSubstring("pwwkew");
//        int pwwkew = windowlengthOfLongestSubstring("pwwkew");
        int len = windowlengthOfLongestSubstring("aBcdefBk");
        int violenceSolve = lengthOfLongestSubstring("aBcdefBk");
        int windowMethod = windowSolve("aBcdefBk");
        System.err.println("win:"+windowMethod);
        System.err.println(violenceSolve);
        System.out.println(len);

        int winSolveDiff = windowSolveRecordDiff("aBcdefBk");
        System.out.println("\033[34;4m" + winSolveDiff + "\033[0m");

    }

    public static int windowSolveRecordDiff(String s) {
       int left = 0;
       int right = 0;
       int maxLen = 0;
       char [] windowCharArr = new char[128];
       while (right < s.length()) {
           char rightChar = s.charAt(right);
           right++;
           windowCharArr[rightChar]++;
           while (windowCharArr[rightChar] > 1) {
               char leftChar = s.charAt(left);
               left++;
               windowCharArr[leftChar]--;
           }
           maxLen = Math.max(right-left,maxLen);
       }
       return maxLen;
    }



    public static  int windowSolve(String s) {
        int right = 0 ;
        int left = 0;
        int maxLen = 0;
        HashMap<String, Integer> windowMap = new HashMap<>();
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            right++;
            if(windowMap.containsKey(rightChar+"")){
                windowMap.put(rightChar+"",windowMap.get(rightChar+"")+1);
            } else {
                windowMap.put(rightChar+"",1);
            }
            while (windowMap.get(rightChar+"") > 1) {
                char leftChar= s.charAt(left);
                left++;
                windowMap.put(leftChar+"",windowMap.get(leftChar+"")-1);
            }
            maxLen = Math.max(right-left,maxLen);
        }
        return maxLen;

    }

    // 滑动窗口解法
    public static  int windowlengthOfLongestSubstring(String s) {
        if (s.length() == 0 ){
            return 0;
        }
        char[] windows = new char[128];   //用于记录每个字符
        int left = 0 , right = 0 ;        //双指针控制窗口大小
        int maxlength = 0 ;               //记录窗口最大长度

        while(right< s.length()){
            char ch = s.charAt(right);
            windows[ch]++;

            //如果有重复字符则左边窗口一直加，直到剔除重复字符
            while (windows[ch] > 1){
                char ch1 = s.charAt(left);
                windows[ch1]--;
                left++;
            }
            maxlength = Math.max(right - left+1 , maxlength);
            right++;
        }
        return maxlength;
    }
    // 暴力解法
    public static int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        int maxLen = 0;
        tag:for(int i = 0 ; i < charArray.length ; i++) {
            HashSet<Character> set = new LinkedHashSet<>();
            set.add(charArray[i]);
            for(int j = i+1 ; j < charArray.length ; j++) {
                if(!set.add(charArray[j])){
                    if(set.size() > maxLen) {
                        maxLen = set.size();
                    }
                    continue  tag;
                }
            }

            if(set.size() > maxLen) {
                maxLen = set.size();
            }

        }
        return maxLen;
    }
}
