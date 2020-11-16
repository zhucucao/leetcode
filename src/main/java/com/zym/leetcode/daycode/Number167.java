package com.zym.leetcode.daycode;

// 两数之和 II - 输入有序数组
public class Number167 {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
    }

    public static void twoSum(Integer[] numbers, int target) {
        // 数组中位下标
        int middleIndex = numbers.length %2 == 0 ? numbers.length / 2 - 1: numbers.length / 2;
        int middleNum = numbers[middleIndex];
        // 大于  和 小于等于
    }
}
