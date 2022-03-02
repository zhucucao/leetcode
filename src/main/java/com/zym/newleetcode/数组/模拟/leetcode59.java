package com.zym.newleetcode.数组.模拟;

import java.util.Arrays;

/**
 * 螺旋矩阵ii
 * https://leetcode-cn.com/problems/spiral-matrix-ii/submissions/
 */

//
public class leetcode59 {

    /**
     *
     * 输入一个数，用一个二维数组将这个数的平方按顺时针存储起来
     *

            2 -> 4    1 2
                      4 3

           3 -> 9    1 2 3
                     8 9 4
                     7 6 5

        4 -> 16     1  2  3  4
                    12 13 14 5
                    11 16 15 6
                    10  9  8 7

        5 -> 25     1  2  3  4  5
                    16 17 18 19 6
                    15 24 25 20 7
                    14 23 22 21 8
                    13 12 11 10 9

     核心思想：要求按顺时针插入这个二维数组，我们把一次顺时针填充视为一个循环，从外圈往内圈依次进行填充
     例如 1 2  只有一圈，那么按顺时针填充一圈就完成了
         4 3

         1 2 3 按顺时针填充完最外圈以后，剩了内圈的一个9 再次填充
         8 9 4
         7 6 5

         1  2  3  4     同样按顺时针进行填充外圈以后 剩了 13  14  15 16 再次对内圈进行循环
         12 13 14 5
         11 16 15 6
         10  9  8 7

     tips:观察以后：发现 1 循环1次  2 循环 1 次 。 3循环2次 4循环2  5 循环3次 6循环3次
     即-》根据输入的数得到。如果 n 为偶数，循环次数为 n次。 如果 n为奇数 则 循环次数为 n/2+1


     循环是从  左上 -> 右上 -> 右下 -> 左下 -》左上 (回到原点)
     如果用二维坐标来表示。这四个过程
        左上 -> 右上  行不变列增
        右上 -> 右下  列不变行增
        右下 -> 左下  行不变列减
        右下 -> 右上  列不变行减

     我们为每个节点定义一个 横坐标 x 和 纵坐标 y 来表示这四个点之间的坐标关系 然后逐一进行填充
     ⚠️：在外圈循环以后进行内圈循环的时候，四个点的坐标是变化的
        左上 行列都增加  右上：行增加列减少
        左下 行减少列增加 右下 行减少列减少

     */
    public int[][] generateMatrix(int n) {
        // 计算一共有几个圈需要循环
        int loopCount = n % 2 == 0 ? n : n / 2 +1;
        int[][] arr = new int[n][n];
        int count = 0;
        // 分别是左上 右上 右下 左下的四个坐标
        int leftBegin = 0;

        int right_up_x = 0;
        int right_up_y = n - 1;

        int right_down_x = n - 1;
        int right_down_y = n - 1;

        int left_down_x = n - 1;
        int left_down_y = 0;
        for(int i = 0 ; i < loopCount ; i++) {

            for(int j = leftBegin ; j <= right_up_y ; j++) {
                arr[leftBegin][j] =  ++count;
            }

            for(int k = right_up_x + 1; k <= right_down_y ; k++) {
                arr[k][right_up_y] = ++count;
            }

            for(int l = right_down_y - 1 ; l >= left_down_y ; l--) {
                arr[right_down_x][l] = ++count;
            }

            for(int m = left_down_x - 1 ; m > leftBegin ; m--) {
                arr[m][left_down_y] = ++ count;
            }
            // 左上 行列都增加
            leftBegin++;

            // 行增加列减少
            right_up_x++;
            right_up_y--;

            // 行减少列减少
            right_down_x--;
            right_down_y--;

            // 列不变行减
            left_down_x--;
            left_down_y++;

        }
        return arr;
    }


    public static void main(String[] args) {
        int[][] ints = new leetcode59().generateMatrix2(3);
        System.out.println(Arrays.deepToString(ints));
    }


    /**
     * https://mp.weixin.qq.com/s?__biz=MzI0NjAxMDU5NA==&mid=2475922670&idx=1&sn=c07f56c74d859a7a9d934e3c36488cb9&chksm=ff22f363c8557a75f2e036cb1e0f4bce6fee53ad1ee0012f1ac689703316e88a18c7bd6d49c1&token=1145569493&lang=zh_CN#rd
     * @param n
     * @return
     */

    /**
     * 此处定义了 left right  up down 来定义四个边界 上下左右
     * 顺时针即 上->右->下->左
     * 上->从左到右填充  右：从上往下填充
     * 下->从右往左      左：从下往上
     *
     * 当上边界填充完后 up往下移 因为up代表的这一行填充完毕了
     * 当右边界填充玩后 right往左移 因为right代表的这一列填充完毕了
     * 当下边界填充完毕后 down往上移 因为down代表的这一行填充完毕了
     * 当左边界填充完毕后 left往右移 因为left代表的这一列填充完毕了
     * 开启内圈填充
     * 以此循环
     * 直到填充所有的数
     */

    public int[][] generateMatrix2(int n) {
        int left = 0 ;
        int right = n - 1;
        int up = 0;
        int down = n - 1;
        int count = 0;

        int[][] arr = new int[n][n];

        while (count <= n * n) {
            for(int i = left ; i <= right ; i++) {
                arr[left][i] = ++count;
            }
            up++;
            for(int j = up ; j <= down ; j++) {
                arr[j][down] = ++count;
            }
            right--;

            for(int k = right; k >= left; k--) {
                arr[down][k] = ++count;
            }
            down--;

            for(int k = down ; k >= left ; k--) {
                arr[down][left] = ++count;
            }
            left++;
        }
        return arr;
    }
}
