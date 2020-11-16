package com.zym.leetcode.dongtaiguihua;

import java.util.Arrays;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 *
 * 0 <= n <= 100
 */
public class NumberOffer1Of1 {
    public static void main(String[] args) {
        initArr(5);
    }
    public int fib(int n) {
        return 0;
    }
    public static void initArr(int n) {
        int[] intArr = new int[n+1];
        intArr[0] = 0;
        intArr[1] = 1;
        for(int i = 2 ; i <=n ; i++) {
            intArr[i] = intArr[i-1] + intArr[i-2];
        }
        System.out.println(Arrays.toString(intArr));
    }
}
