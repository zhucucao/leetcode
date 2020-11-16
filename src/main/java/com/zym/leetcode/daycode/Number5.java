package com.zym.leetcode.daycode;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;

// tag:   字符串 动态规划
// title: 回文子串
// ok
public class Number5 {
    public static void main(String[] args) {
        String executeStr = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        executeStr = "cbbd";
//        executeStr = "abcde";
        executeStr = "abcda";
        executeStr = "aaabaaaa";
        StopWatch stopWatch = new StopWatch("");
        stopWatch.start();
//        String palindrome = longestPalindrome(executeStr);
        String maxStr = longestPalindromeForNew2(executeStr);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        System.err.println(""+ maxStr);

    }
    public static String longestPalindromeForNew2(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return  s;
        }
        char[] charArray = s.toCharArray();
        HashMap<Character, String> hashMap = new HashMap<>();
        boolean isSingleEle = true;
        for(int i = 0 ; i < charArray.length ; i++) {
            if(hashMap.containsKey(charArray[i])) {
                isSingleEle = false;
                hashMap.put(charArray[i],hashMap.get(charArray[i]) + ";" + i );
            } else {
                hashMap.put(charArray[i],i+"");
            }
        }
        if(hashMap.keySet().size() == 1){
            return  s;
        }
        if(isSingleEle) {
            return  s.substring(0,1);
        }
        // 上一个key代表的最长回文串的长度
        // 如果下一个截取的最大长度的字符串没有这个长 则直接跳过循环
        int currentLen = 0;
        String maxStr = "";
        String val = "";
        StringBuilder stringBuilder = new StringBuilder(s);
        for (Character key : hashMap.keySet()) {
            if(!hashMap.get(key).contains(";")) {
                continue;
            }
            val =  hashMap.get(key);
            String[] split = val.split(";");

           outer: for (int i = 0; i < split.length; i++) {
                // 当前元素的第一个下标
                int currentSameEleIndex = Integer.parseInt(split[i]);
                // 相同元素的下标 从这个数组倒叙开始截取字符串
                int nextSameEleIndex = 0;
                // 当前截取的字符串
                String currentStr = "";
                // 当前截取字符串对应的char数组
                char[] sameEleArray;
                // 循环次数
                int loopCount = 0;
                for(int j = split.length -1 ; j > 0 ; j--) {
                    // 是否是回文串
                    boolean isRequire = true;
                    nextSameEleIndex = Integer.parseInt(split[j]);
                    currentStr = stringBuilder.substring(currentSameEleIndex,nextSameEleIndex + 1);
                    if(currentStr.length() < maxStr.length()) {
                        continue  outer;
                    }
                    sameEleArray = currentStr.toCharArray();
                    loopCount = currentStr.length() / 2;
                    for(int x = 0 ; x < loopCount ; x++) {
                        // TODO 如果是偶数长度 可以先比较中间两位 如果中间两位不一样 则肯定不是回文串 可以跳过循环
                        // TODO 判断回文串时 可以跳过首次判断 因为串的截取是按照相同元素的坐标截取的
                        char beginc = sameEleArray[x];
                        char endC = sameEleArray[sameEleArray.length - 1 -x];
                        if(beginc != endC) {
                            isRequire = false;
                        }
                    }
                    if(isRequire) {
                        maxStr = currentStr;
                        break;
                    }
                }
            }

        }
        return maxStr;
    }

    // new Method
    public static String longestPalindromeForNew(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return  s;
        }
        char[] charArray = s.toCharArray();
        HashMap<Character, String> hashMap = new HashMap<>();
        for(int i = 0 ; i < charArray.length ; i++) {
            if(hashMap.containsKey(charArray[i])) {
                hashMap.put(charArray[i],hashMap.get(charArray[i]) + ";" + i );
            } else {
                hashMap.put(charArray[i],i+"");
            }
        }

        if(hashMap.keySet().size() == 1){
            return  s;
        }

        String val = "";
        String maxStr = "";
        for (Character key : hashMap.keySet()) {
            if(!hashMap.get(key).contains(";")) {
                continue;
            }
            val =  hashMap.get(key);
            String[] split = val.split(";");
            for(int i = 0 ; i < split.length-1; i++){
                int currentIndex = Integer.parseInt(split[i]);
                for (int j = i+1 ; j < split.length ;j++) {
                    int nextIndex = Integer.parseInt(split[j]);
                    String substring = s.substring(currentIndex, nextIndex + 1);
                    int len = substring.length() / 2;
                    char[] array = substring.toCharArray();
                    boolean isRequire =true;
                    for (int x = 0 ; x < len ; x++) {
                        char beginC = array[x];
                        char endC = array[array.length-1-x];
                        if(beginC != endC) {
                            isRequire = false;
                            break;
                        }
                    }
                    if(substring.length() >= maxStr.length() && isRequire){
                        maxStr = substring;
                    }
                }

            }
        }
        if(maxStr.equals("")){
            maxStr = s.substring(0,1);
        }
        return  maxStr;
    }

    // old Method
    public static String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return  s;
        }
        char[] charArray = s.toCharArray();
        HashMap<Character, String> hashMap = new HashMap<>();
        for(int i = 0 ; i < charArray.length ; i++) {
            if(hashMap.containsKey(charArray[i])) {
                hashMap.put(charArray[i],hashMap.get(charArray[i]) + ";" + i );
            } else {
                hashMap.put(charArray[i],i+"");
            }
        }

        if(hashMap.keySet().size() == 1){
            return  s;
        }

        String val = "";
        String maxStr = "";
        for (Character key : hashMap.keySet()) {
            if(!hashMap.get(key).contains(";")) {
                continue;
            }
            val =  hashMap.get(key);
            String[] split = val.split(";");
            for(int i = 0 ; i < split.length-1; i++){
                int currentIndex = Integer.parseInt(split[i]);
                for (int j = i+1 ; j < split.length ;j++) {
                    int nextIndex = Integer.parseInt(split[j]);
                    String substring = s.substring(currentIndex, nextIndex + 1);
                    int len = substring.length() / 2;
                    char[] array = substring.toCharArray();
                    boolean isRequire =true;
                    for (int x = 0 ; x < len ; x++) {
                        char beginC = array[x];
                        char endC = array[array.length-1-x];
                        if(beginC != endC) {
                            isRequire = false;
                            break;
                        }
                    }
                    if(substring.length() >= maxStr.length() && isRequire){
                        maxStr = substring;
                    }
                }

            }
        }
        if(maxStr.equals("")){
            maxStr = s.substring(0,1);
        }
        return  maxStr;
    }
}
