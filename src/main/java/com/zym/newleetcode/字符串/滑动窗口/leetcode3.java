package com.zym.newleetcode.字符串.滑动窗口;

import java.util.HashMap;

public class leetcode3 {

    public int lengthOfLongestSubstring(String s) {

        char[] loopArr = s.toCharArray();

        int slow = 0;
        HashMap<Character,Integer> recordMap = new HashMap<Character,Integer>();
        Boolean stopFast = false;
        int maxLen = 0;
        for(int fast = 0 ; fast < loopArr.length; fast++ ) {
            recordMap.put(loopArr[fast],recordMap.getOrDefault(loopArr[fast],0) + 1);
            if(recordMap.get(loopArr[fast]) > 1) {
                stopFast = true;
            }
            while(stopFast) {
                if(recordMap.get(loopArr[slow]) > 1) {
                    stopFast = false;
                }
                recordMap.put(loopArr[slow],recordMap.get(loopArr[slow]) - 1);
                slow++;
            }
            maxLen = Math.max(maxLen,fast - slow + 1);
        }
        return maxLen;
    }
}
