package com.zym.leetcode.erfenchazhao;

public class Num374 {

    public static void main(String[] args) {
        int target = 6;
        int left = 0 ;
        int right = 10 - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2 ;
            int midVal = mid + 1;
            if(midVal  == target) {
                System.out.println(midVal);
                break;
            }else if(midVal < target) {
                left = mid + 1 ;
            }else if(midVal > target) {
                right = mid - 1;
            }
        }
    }

}
