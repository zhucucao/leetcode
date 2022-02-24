package com.zym.newleetcode.字符串.滑动窗口;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// .找到字符串中所有字母异位词
public class leetcode438 {

    //    给定两个字符串s和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
//    异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//             
//
//    示例 1:
//
//    输入: s = "cbaebabacd", p = "abc"
//    输出: [0,6]
//    解释:
//    起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//    起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//             示例 2:
//
//    输入: s = "abab", p = "ab"
//    输出: [0,1,2]
//    解释:
//    起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//    起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//    起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//     s= baa  p = aa  预期 1
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
//    。

    /**
     *  核心思想仍然是双指针维护一个窗口,这个窗口中必须满足p中的字符以及字符的个数。
     *  关键点在于找异位词，这里的异位词是连续的。
     *  即 双指针中的 右指针寻找一个窗口满足key和key出现的次数，随后判断左右指针之间的长度
     *  是否满足目标串s的长度，只有两个条件同时成立的时候，才是要找的异位词
     *  abdcba  abc  | abdc 满足元素以及元素的个数 但是是不连续的，长度大于abc目标串，所以不是要寻找的异位词
     *
     *
     *
     *  compareMap初始化目标串p，记录p中的元素以及每个元素的个数
     *  slow fast指针初始化都指向s数组头部
     *  右移指针recordMap记录p中的目标元素以及元素出现的次数
     *  直到符合compareMap，对比左右指针的长度差，判断是否是要找的异位词
     *  slow指针右移，直到recordMap中的元素和元素的个数不符合compareMap后
     *  fast继续右移，以此类推直到遍历完成
     */
    public static void main(String[] args) {
        List<Integer> anagrams = new leetcode438().findAnagrams2("cbaebabacd", "abc");
        System.out.println(anagrams.toString());
    }
    public List<Integer> findAnagrams2(String s, String p) {
        ArrayList<Integer> resultList = new ArrayList<>();
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = p.toCharArray();
        for (char aChar : chars) {
            compareMap.put(aChar, compareMap.getOrDefault(aChar, 0) + 1);
        }
        HashMap<Character, Integer> recordMap = new HashMap<>();
        int slow = 0;
        // 相比下面方法优化的地方。在存储过程中校验recordmap的key和value是否符合p的要求
        int valid = 0;
        char[] loopArr = s.toCharArray();
        for(int fast = 0 ; fast < loopArr.length ; fast++) {
            if(compareMap.containsKey(loopArr[fast])) {
                recordMap.put(loopArr[fast],recordMap.getOrDefault(loopArr[fast],0) + 1);
                if(recordMap.get(loopArr[fast]).equals(compareMap.get(loopArr[fast]))) {
                    valid++;
                }
            }

            while (valid == compareMap.size()) {
                if(fast - slow + 1 == p.length()) {
                    resultList.add(slow);
                }
                if(compareMap.containsKey(loopArr[slow])) {
                    if(recordMap.get(loopArr[slow]).equals(compareMap.get(loopArr[slow]))) {
                        valid--;
                    }
                    recordMap.put(loopArr[slow],recordMap.get(loopArr[slow]) - 1);
                }
                slow++;
            }
        }
        return resultList;
    }
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> resultList = new ArrayList<>();
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = p.toCharArray();
        for (char aChar : chars) {
            compareMap.put(aChar, compareMap.getOrDefault(aChar, 0) + 1);
        }
        HashMap<Character, Integer> recordMap = new HashMap<>();
        int slow = 0;
        char[] loopArr = s.toCharArray();
        for (int fast = 0; fast < loopArr.length; fast++) {
            if (compareMap.containsKey(loopArr[fast])) {
                recordMap.put(loopArr[fast], recordMap.getOrDefault(loopArr[fast], 0) + 1);
            }
            while (stopFast(compareMap,recordMap)) {
                if (compareMap.containsKey(loopArr[slow])) {
                    if (fast - slow + 1 == p.length()) {
                        resultList.add(slow);
                    }
                    recordMap.put(loopArr[slow], recordMap.get(loopArr[slow]) - 1);
                    if (recordMap.get(loopArr[slow]) == 0) {
                        recordMap.remove(loopArr[slow]);
                    }
                }
                slow++;
            }
        }
        return resultList;
    }


    public Boolean stopFast(HashMap<Character,Integer> compareMap,HashMap<Character,Integer> recordMap) {
        boolean stopFast = true;
        if(compareMap.size() == recordMap.size()) {
            for (Character character : compareMap.keySet()) {
                if(recordMap.get(character) < compareMap.get(character)) {
                    return false;
                }
            }
        }else {
            stopFast = false;
        }
        return  stopFast;
    }
}
