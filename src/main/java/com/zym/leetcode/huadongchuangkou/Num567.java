package com.zym.leetcode.huadongchuangkou;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Num567 {
//    public static void main(String[] args) {
//        String s1 = "bbb";
//        String s2 = "eidbobbbccoobobb";
////        s1 = "ab";
////        s2 = "eidboaoo";
////
////        s1 = "ab";
////        s2 = "eidbaooo";
////        checkInclusion(s1,s2);
//        //
////        windowCheckInclusion(s1,s2);
//        windowCheckInclusionDiff(s1,s2);
//    }

    public static void main(String[] args) {
        String s1 = "adc";
        String s2 = "dcda";
        boolean i = windowArrSolve(s1, s2);
        System.out.println(i);
    }

    public static boolean windowArrSolve(String s1,String s2) {
        int left = 0,right = 0;
        int match = 0;
        int needEleKeySize = 0;
        char[] needCharArr = new char[128];
        char[] windowCharArr = new char[128];
        for(char c : s1.toCharArray()) {
            needCharArr[c]++;
            if(needCharArr[c] == 1) {
                needEleKeySize++;
            }
        }
        boolean isMatch = false;
        while (right < s2.length()) {
            char rightChar = s2.charAt(right);
            right++;
            if(needCharArr[rightChar] > 0) {
                windowCharArr[rightChar]++;
                if(windowCharArr[rightChar] == needCharArr[rightChar]) {
                    match++;
                }
            }
            while (right - left >= s1.length()) {
                if(needEleKeySize == match) {
                    isMatch = true;
                    return true;
                }
                char leftChar = s2.charAt(left);
                if(needCharArr[leftChar] > 0) {
                    if(windowCharArr[leftChar] == needCharArr[leftChar]) {
                        match--;
                    }
                    windowCharArr[leftChar]--;
                }
                left++;
            }
        }
        return isMatch;
    }
    // 滑动窗口解法2 need判断条件的不同。
    public static boolean windowCheckInclusionDiff(String s1,String s2) {
        int left = 0;
        int match = 0;
        int right = 0;
        HashMap<String, Integer> needMap = new HashMap<>();
        HashMap<String, Integer> windowMap = new HashMap<>();
        char[] s1CharArr = s1.toCharArray();
        for (char c : s1CharArr) {
            if(needMap.containsKey(c+"")) {
                needMap.put(c+"",needMap.get(c+"") + 1);
            } else {
                needMap.put(c+"",1);
            }
        }
        char[] s2CharArr = s2.toCharArray();
        boolean isAllMatch = false;
        outer:while (right < s2CharArr.length) {
            char rightChar = s2CharArr[right];
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
            // 如果是满足条件的串 长度必然是相同的 把长度视为满足条件
            while (right - left >= s1.length()) {
                if(match == needMap.keySet().size()) {
                    isAllMatch = true;
                    break outer;
                }
                char leftChar = s2CharArr[left];
                left++;
                if(needMap.containsKey(leftChar+"")) {
                    if(windowMap.get(leftChar+"").equals(needMap.get(leftChar+""))) {
                        match--;
                    }
                    windowMap.put(leftChar+"",windowMap.get(leftChar+"")-1);
                }
            }
        }
        return isAllMatch;
    }
    // 滑动窗口解法
    public static boolean windowCheckInclusion(String s1,String s2) {
        int left = 0;
        int match = 0;
        int right = 0;
        HashMap<String, Integer> needMap = new HashMap<>();
        HashMap<String, Integer> windowMap = new HashMap<>();
        char[] s1CharArr = s1.toCharArray();
        for (char c : s1CharArr) {
            if(needMap.containsKey(c+"")) {
                needMap.put(c+"",needMap.get(c+"") + 1);
            } else {
                needMap.put(c+"",1);
            }
        }
        char[] s2CharArr = s2.toCharArray();
        boolean isMatch = false;
       outer: while (right < s2.length()) {
            char rightChar = s2CharArr[right];
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
            // 把目标字符出现的次数视为满足的条件
            while (match == needMap.keySet().size()) {
                char leftChar = s2CharArr[left];
                if(needMap.containsKey(leftChar+"")) {
                    if(right - left == s1.length()) {
                        isMatch = true;
                        break outer;
                    }
                    if(windowMap.get(leftChar+"").equals(needMap.get(leftChar+""))) {
                        match--;
                    }
                    windowMap.put(leftChar+"",windowMap.get(leftChar+"")-1);
                }
                left++;
            }
        }
        return isMatch;
    }


    // 暴力解法 超时
    public static boolean checkInclusion(String s1, String s2) {
        // 目标串中出现的 字母的次数
        HashMap<String, Integer> needEleNumMap = new HashMap<>();
        // 可以改为ascii码相加 来与目标串的ascii码进行比对 但是这样的话会出现错误的情况
        // 可能是其他ascii码相加 造成与目标串的ascii码相同
        char[] needCharArr = s1.toCharArray();
        for (char c : needCharArr) {
            if(needEleNumMap.containsKey(c+"")) {
                needEleNumMap.put(c+"",needEleNumMap.get(c+"")+1);
            }else {
                needEleNumMap.put(c+"",1);
            }
        }
        // 在s2中获取与s1等长的子串
        ArrayList<String> strList = new ArrayList<>();
        int s1len = s1.length();
        for(int i = 0 ; i < s2.length();i++) {
            if(i+s1len > s2.length()) {
                break;
            }
            strList.add(s2.substring(i,i+s1len));
        }
        boolean allMatch = false;
        // 计算集合中各项出现的字符的个数是否与目标串需要的字符个数相同
        outer:for (String s : strList) {
            char[] charArray = s.toCharArray();
            HashMap<String, Integer> map = new HashMap<>();
            for (char c : charArray) {
                if(map.containsKey(c+"")) {
                    map.put(c+"",map.get(c+"")+1);
                }else {
                    map.put(c+"",1);
                }
            }
            boolean match = true;
            for (String key : needEleNumMap.keySet()) {
                if(!needEleNumMap.get(key).equals(map.get(key))) {
                    match = false;
                    break ;
                }
            }
            if(match) {
                allMatch = true;
                return allMatch;
            }
        }
        return allMatch;
    }
}
