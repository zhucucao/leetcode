package com.zym.newleetcode.数组.滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 在公司写的忘提交了 备份一个 明天到公司提交再更新一下
 */
public class leetcode76backup {
//    https://labuladong.gitee.io/algo/1/11/  滑动窗口模板

//    示例 1：
//
//    输入：s = "ADOBECODEBANC", t = "ABC"
//    输出："BANC"
//    示例 2：
//
//    输入：s = "a", t = "a"
//    输出："a"
//    示例 3:
//
//    输入: s = "a", t = "aa"
//    输出: ""
//    解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//    因此没有符合条件的子字符串，返回空字符串。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/minimum-window-substring
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public static void main(String[] args) {
        String s = new leetcode76backup().minWindow2("aaaaaaaaaaaabbbbbcdd", "abcdd");
        System.out.println(s);
    }

//"aaaaaaaaaaaabbbbbcdd"    "abcdd"
//           "abbbbbcdd"
    public String minWindow2(String s,String t) {
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = t.toCharArray();
        for (char aChar : chars) {
            compareMap.put(aChar,compareMap.getOrDefault(aChar,0) + 1);
        }
        HashMap<Character, Integer> recordMap = new HashMap<>();
        int slow = 0;
        int minLen = Integer.MAX_VALUE;
        String minStr = "";
        // 相比minWindow的优化点，minWindow中判断fast指针是否往后移动还需要逐一遍历与compareMap对比判断
        int valid = 0;
        char[] loopArr = s.toCharArray();
        for(int fast = 0 ; fast < loopArr.length ; fast++) {
            if(compareMap.containsKey(loopArr[fast])) {
                recordMap.put(loopArr[fast],recordMap.getOrDefault(loopArr[fast],0) + 1);
                // 相比minWindow的优化点，minWindow中判断fast指针是否往后移动还需要逐一遍历与compareMap对比判断
                // 通过valid变量的判断 即判断了 record中key的个数是否符合 而且还判断key的值是否符合。省去minWindow的遍历
                if(recordMap.get(loopArr[fast]).equals(compareMap.get(loopArr[fast]))) {
                    valid++;
                }
            }

            while (valid == compareMap.size()) {
                if(minLen > fast - slow + 1) {
                    minStr = s.substring(slow,fast + 1);
                    minLen  = fast - slow + 1;
                }
                if(recordMap.containsKey(loopArr[slow])) {
//                    recordMap.put(loopArr[slow],recordMap.get(loopArr[slow]) - 1);
//
//                    if(recordMap.get(loopArr[slow]) < compareMap.get(loopArr[slow])) {
//                        valid--;
//                    }
                     // 注释部分判断的另外一种方法 因为还未移除且key的值相同，移除后肯定就不相同了。
                    // record里面的符合key就减1
                    if(recordMap.get(loopArr[slow]).equals(compareMap.get(loopArr[slow]))) {
                        valid--;
                    }
                    recordMap.put(loopArr[slow],recordMap.get(loopArr[slow]) - 1);
                }
                slow++;
            }

        }

        return minStr;
    }

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> compareMap = new HashMap<>();
        char[] chars = t.toCharArray();
        for(char currentChar : chars) {
            compareMap.put(currentChar,compareMap.getOrDefault(currentChar,0) + 1);
        }

        HashMap<Character, Integer> recordHashMap = new HashMap<>();
        int slow = 0;
        int minLen = Integer.MAX_VALUE;
        String minString = "";
        char[] loopCharArr = s.toCharArray();
        for(int fast = 0 ; fast < loopCharArr.length ; fast++) {
            if(compareMap.containsKey(loopCharArr[fast])) {
                recordHashMap.put(loopCharArr[fast],recordHashMap.getOrDefault(loopCharArr[fast],0) + 1);
            }
            while (stopFast(compareMap,recordHashMap)) {
                if(minLen > fast - slow + 1) {
                    minString = s.substring(slow,fast + 1);
                    minLen = Math.min(minLen,fast - slow + 1);
                }
                if(recordHashMap.containsKey(loopCharArr[slow])) {
                    recordHashMap.put(loopCharArr[slow],recordHashMap.get(loopCharArr[slow]) - 1);
                    if(recordHashMap.get(loopCharArr[slow]) == 0) {
                        recordHashMap.remove(loopCharArr[slow]);
                    }
                }
                slow++;
            }
        }

        return minString;
    }

    public Boolean stopFast(HashMap<Character,Integer> compareMap, HashMap<Character,Integer> recordHashMap) {
        Boolean stopFast = true;
        if(recordHashMap.size() == compareMap.size()) {
            for (Character character : compareMap.keySet()) {
                if( compareMap.get(character) > recordHashMap.get(character)) {
                    return false;
                }
            }
        }else {
            stopFast = false;
        }
        return stopFast;
    }
}
