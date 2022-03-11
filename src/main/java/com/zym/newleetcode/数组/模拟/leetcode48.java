package com.zym.newleetcode.数组.模拟;

import java.util.Arrays;

public class leetcode48 {
    public static void main(String[] args) {
        int arr[][] = new int[3][3];
        arr[0] = new int[]{1,2,3};
        arr[1] = new int[]{4,5,6};
        arr[2] = new int[]{7,8,9};

        new leetcode48().rotate(arr);
    }


    // 新数组存储法 由于旋转后，第i行变成了第n-1-i列，第j列变成了第j行
    // 得到 arr[i][j] = arr[j][n-1-i]
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] storageMatrix = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                storageMatrix[j][n - 1 - i] = matrix[i][j];
            }
        }
        for(int x = 0 ; x < n ; x++) {
            matrix[x] = storageMatrix[x];
        }
    }

    // 原地翻转法
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

    }

    // 对角线翻折 然后纵向翻折
}
