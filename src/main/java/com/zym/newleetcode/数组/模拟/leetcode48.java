package com.zym.newleetcode.数组.模拟;

import java.util.Arrays;

public class leetcode48 {


    /**
     * https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     * https://leetcode-cn.com/problems/rotate-image/solution/48-xuan-zhuan-tu-xiang-fu-zhu-ju-zhen-yu-jobi/
     * https://leetcode-cn.com/problems/rotate-image/solution/48-xuan-zhuan-tu-xiang-chao-jian-ji-yi-d-nuau/
     */

    /**
     *      1 2 3         7   4  1
     *      4 5 6  ->     8   5  2
     *      7 8 9         9   6  3
     */
    // 旋转以后 第 i 行 变成了第 n-1-i列 ，而第j列变成了第j行
    //以 1 元素为例子，顺时针旋转后 arr[0][0] 变到了 arr[0][3-1-0] = arr[0][2]
    // 即公式 arr[i][j] = arr[j][n-1-i]


    // 借助新矩阵完成法
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] storageArr = new int[n][n];
        // 借助新数组存储旋转后的数组
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                storageArr[j][n-i-i] = matrix[i][j];
            }
        }

        // 然后再复制给原数组满足题目要求
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                matrix[i][j] = storageArr[i][j];
            }
        }

        System.out.println(Arrays.deepToString(matrix));
    }

    public static void main(String[] args) {
        int arr[][] = new int[3][3];
        arr[0] = new int[]{1,2,3};
        arr[1] = new int[]{4,5,6};
        arr[2] = new int[]{7,8,9};

        new leetcode48().rotate(arr);
    }


    // 新数组存储法 由于旋转后，第i行变成了第n-1-i列，第j列变成了第j行
    // 得到 arr[i][j] = arr[j][n-1-i]
    public void rotate22(int[][] matrix) {
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


    // 原地翻转法  最符合要求 且空间复杂度最低



    // 对角线翻折 然后纵向翻折
}
