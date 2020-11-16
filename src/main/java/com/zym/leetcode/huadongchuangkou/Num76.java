package com.zym.leetcode.huadongchuangkou;

import java.util.HashMap;

/**
 * 滑动窗口算法
 * https://www.cnblogs.com/labuladong/p/12320475.html
 * https://mp.weixin.qq.com/s/ioKXTMZufDECBUwRRp3zaA
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串
 */
public class Num76 {
    public static void main(String[] args) {
//        minWindow("ADOBECODEBANC","ABC");
        String s = minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd");
        System.out.println(s);
        String s1 = windowSolve("aaaaaaaaaaaabbbbbcdd", "abcdd");
        System.err.println(s1);
    }

    public static String windowSolve(String s,String t) {
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int needCharlen = 0;
        int left = 0;
        int right = 0;
        int match = 0;
        char[] windowArr = new char[128];
        char[] needArr = new char[128];
        char[] charArray = t.toCharArray();
        for (char c : charArray) {
            needArr[c]++;
            if(needArr[c] == 1) {
                needCharlen++;
            }
        }
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            right++;
            if(needArr[rightChar] > 0) {
                windowArr[rightChar]++;
                if(windowArr[rightChar] == needArr[rightChar]) {
                    match++;
                }
            }
            while (match == needCharlen) {
                if(right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char leftChar = s.charAt(left);
                left++;
                if(needArr[leftChar] > 0) {
                    if(windowArr[leftChar] == needArr[leftChar]) {
                        match--;
                    }
                    windowArr[leftChar]--;
                }
            }
        }

        return minLen == Integer.MAX_VALUE? "" :s.substring(start,start + minLen);
    }

    public static String minWindow(String s, String t) {
        int start = 0;
        int compareVal = Integer.MAX_VALUE;
        // 左右指针
        int left = 0,right = 0;
        // 与目标串中的匹配次数
        int match = 0;
        // 需要的 字符 以及需要字符的个数
        HashMap<String, Integer> needMap = new HashMap<>();
        // 记录窗口出现满足需要的 字符 以及 需要字符出现的个数
        HashMap<String,Integer> windowMap = new HashMap<String, Integer>();
        char[] charArr = t.toCharArray();
        // 初始化需要的字符
        for(int i = 0 ; i< charArr.length;i++) {
            if(needMap.containsKey(charArr[i]+"")) {
                needMap.put(charArr[i]+"",needMap.get(charArr[i]+"")+1);
            }else {
                needMap.put(charArr[i]+"",1);
            }
        }
        char[] sCahr = s.toCharArray();
        // 开始右移指针
        while (right < s.length()){
            char rightChar = sCahr[right];
            right++;
            if(needMap.containsKey(rightChar+"")) {
                if(windowMap.containsKey(rightChar+"")) {
                    windowMap.put(rightChar+"",windowMap.get(rightChar+"")+1);
                } else {
                    windowMap.put(rightChar+"",1);
                }
                if(windowMap.get(rightChar + "").equals(needMap.get(rightChar + ""))) {
                    match++;
                }
            }
            // 窗口满足目标子串
            while (match == needMap.keySet().size()) {
                char leftChar = sCahr[left];
                if(right - left < compareVal) {
                    compareVal = right - left;
                    start = left;
                }
                left++;
                if(windowMap.containsKey(leftChar+"")) {
                    if(windowMap.get(leftChar+"").equals(needMap.get(leftChar+""))) {
                        match--;
                    }
                    windowMap.put(leftChar+"",windowMap.get(leftChar+"")-1);

                }
            }
        }
        if(compareVal == Integer.MAX_VALUE) {
            return "";
        } else {
            return  s.substring(start,start + compareVal) ;
        }
    }
}
