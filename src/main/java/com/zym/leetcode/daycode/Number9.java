package com.zym.leetcode.daycode;

// 判断是否为回文数
// ok
public class Number9 {

    //
    public static void main(String[] args) {
        boolean palindrome = isPalindrome2(010);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome2(int x) {
        if(x < 0) {
            return  false;
        }
        int div = 1;
        while (x / div >= 10) {
            div*=10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if(left != right) {
                return  false;
            }
            x = x % div / 10;
            div = div / 100;
        }
        return true;
    }
//    作者：MisterBooo
//    链接：https://leetcode-cn.com/problems/palindrome-number/solution/dong-hua-hui-wen-shu-de-san-chong-jie-fa-fa-jie-ch/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public static  boolean isPalindrome(int x) {
        char[] charArray = String.valueOf(x).toCharArray();
        if(charArray.length == 1) {
            return  true;
        }
        if(charArray.length == 0) {
            return  false;
        }
        int len = charArray.length;
        int loopCount = len / 2 ;
        // 如果是偶数长度 先比较中间两位 如果中间两位不一致 则不是
        if(loopCount %2 == 0) {
            if(charArray[loopCount -1] != charArray[loopCount]) {
                return  false;
            }
        }
        char beginC = 'b';
        char endC = 'a';
        for(int i = 0 ; i < loopCount ; i++) {
            beginC = charArray[i];
            endC = charArray[len - 1 - i];
            if(beginC != endC) {
                return  false;
            }
        }
        return  true;
    }
}
