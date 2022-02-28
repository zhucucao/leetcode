package com.zym.newleetcode.字符串.滑动窗口;

/**
 * https://leetcode-cn.com/problems/permutation-in-string/
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

/**
 * leetcide438的变种。438是找所有异位词，此提只是判断是否包含指定串的异位词
 * 判断是否包含另外一个子串，核心思想仍然是维护一个窗口，窗口必须满足2个条件: 1.窗口中包含指定串的所有元素以及元素的个数 2.这个窗口的长度必须跟指定串相同
 * 初始化recordMap记录指定串的元素和元素的个数。初始化recordMap来在循环中记录元素
 * 初始化快慢指针slow,fast指向头部,fast指针向右移动同时recordMap记录指定串包含的元素和个数，直到符合指定穿的所有元素以及元素的个数
 * 之后判断当前窗口的长度是否符合指定串的长度，符合返回true.否则slow指针向右移动，减少记录元素的个数，直到不符合窗口的第一个必要条件后，
 * 右指针继续移动，依此循环
 */
public class leetcode567 {



    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = s1.toCharArray();
        for (char aChar : chars) {
            compareMap.put(aChar,compareMap.getOrDefault(aChar,0) + 1);
        }
        char[] loopArr = s2.toCharArray();
        HashMap<Character, Integer> recordMap = new HashMap<>();
        int slow = 0;
        int valid = 0 ;
        for(int fast = 0 ; fast < loopArr.length ; fast++) {
            if(compareMap.containsKey(loopArr[fast])) {
                recordMap.put(loopArr[fast],recordMap.getOrDefault(loopArr[fast],0) + 1);
                if(compareMap.get(loopArr[fast]).equals(recordMap.get(loopArr[fast]))) {
                    valid++;
                }
            }
            while (valid == compareMap.size()) {
                if(fast - slow + 1 == s1.length()) {
                    return true;
                }
                if(recordMap.containsKey(loopArr[slow])) {
                    if(recordMap.get(loopArr[slow]).equals(compareMap.get(loopArr[slow]))) {
                        valid--;
                    }
                    recordMap.put(loopArr[slow],recordMap.get(loopArr[slow]) - 1);
                }
                slow++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
//        boolean b = new leetcode567().checkInclusion("ab", "eidboaoo");
        boolean b2 = new leetcode567().checkInclusion2( "adc","dcda");
        System.out.println(b2);
    }
    /**
     * https://leetcode-cn.com/problems/permutation-in-string/solution/hua-dong-chuang-kou-suan-fa-qiu-fu-he-ch-m48c/
     * https://labuladong.gitee.io/algo/1/11/
     */
    public boolean checkInclusion2(String s1, String s2) {
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = s1.toCharArray();
        for (char aChar : chars) {
            compareMap.put(aChar,compareMap.getOrDefault(aChar,0) + 1);
        }
        char[] loopArr = s2.toCharArray();
        HashMap<Character, Integer> recordMap = new HashMap<>();
        int slow = 0;
        int valid = 0 ;
        for(int fast = 0 ; fast < loopArr.length ; fast++) {
            if(compareMap.containsKey(loopArr[fast])) {
                recordMap.put(loopArr[fast],recordMap.getOrDefault(loopArr[fast],0) + 1);
                if(compareMap.get(loopArr[fast]).equals(recordMap.get(loopArr[fast]))) {
                    valid++;
                }
            }
            // 上一个方法优先判断的是元素和元素的个数是否符合，这个是优先判断长度是否符合
            while (fast - slow + 1 >= s1.length()) {
                if(valid == compareMap.size()) {
                    return true;
                }
                if(recordMap.containsKey(loopArr[slow])) {
                    if(compareMap.get(loopArr[slow]).equals(recordMap.get(loopArr[slow]))) {
                        valid--;
                    }
                    recordMap.put(loopArr[slow],recordMap.get(loopArr[slow]) - 1);
                }
                slow++;
            }
        }
        return false;
    }

}
