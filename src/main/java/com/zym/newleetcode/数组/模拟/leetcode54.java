package com.zym.newleetcode.数组.模拟;

import java.util.ArrayList;
import java.util.List;

public class leetcode54 {

    public static void main(String[] args) {
        int arr[][] = new int[3][4];
        arr[0] = new int[]{1,2,3,4};
        arr[1] = new int[]{5,6,7,8};
        arr[2] = new int[]{9,10,11,12};
        List<Integer> list = new leetcode54().spiralOrder(arr);
        System.out.println(list);
    }

    /**
     * 思想与59相同 看微信的那篇文章
     * @param matrix
     * @return
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        // 行数
        int m = matrix.length;
        // 列数
        int n = matrix[0].length;
        int left = 0 ;
        int up = 0;
        int right = n - 1;
        int down = m - 1;

        ArrayList<Integer> resultList = new ArrayList<>();
        while (resultList.size() < m * n) {

            for(int i = left ; i <= right && resultList.size() < m*n ; i++) {
                resultList.add(matrix[up][i]);
            }
            up++;
            for(int j = up ; j <= down && resultList.size() < m*n; j++) {
                resultList.add(matrix[j][right]);
            }
            right--;

            for(int k = right ; k >= left && resultList.size() < m*n; k--) {
                resultList.add(matrix[down][k]);
            }
            down--;

            for(int l = down; l>=up && resultList.size() < m*n; l--) {
                resultList.add(matrix[l][left]);
            }
            left++;

        }
        return resultList;
    }
}
