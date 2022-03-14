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



    /**
     *  原地翻转法  最符合要求 且空间复杂度最低
     *  该题：确定旋转公
     *
        1.新数组存储法 由于旋转后，第i行变成了第n-1-i列，第j列变成了第j行
        得到 arr[i][j] = arr[j][n-1-i]。这是位于i,j位置的元素经过顺时针旋转后变到了 j,n-1-i的坐标
        2.   A  B  C  D
             E  F  G  H
             I  J  K  L
             M  N  O  P
        由1得： n=4的二维数组旋转，如果起始元素是，我们用 temp变量记录A的值，用 M 覆盖A ,P覆盖M,D覆盖P,随后用temp也就是A的值覆盖D
       （为什么是倒着覆盖，如果顺时针覆盖，A->D->P->M,相当于每次覆盖我们都要记录要覆盖的值，用于覆盖下一个元素，逆时针覆盖只需要记录首位元素）

         公式：arr[x][y] = arr[y][n-1-x];这是顺时针两个元素的坐标关系，我们现在是逆时针覆盖
         A:arr[i][j];  temp=A;
         M->A:  已知A的坐标，倒推M的坐标 即：M arr[行][列] = arr[i][j] -> m坐标为 arr[n-1-j][i] 。
                (因为M的列变成了行。那么A的行i就是M的列，M的行变成了n-1-x列，即n-1-M的行坐标等于A的列，n-1-M的行=j推出M的行等于  n - 1-j)
        P->M: 已知M坐标：arr[n-1-j][i]  ->p坐标  arr[n-1-i][n-1-j];p的列变成了M的行，p的列等于n-1-j.M的列是由n-1-P的行得来。即n-1-P行=i.P行=n-1-i
        D->P: 已知p坐标：arr[n-1-i][n-1-j] -> D坐标: arr[j][n-1-i];
        A-D: arr[j][n-1-i] = temp;
        这一轮交换了4个元素：temp = A = arr[i][j]
                    M->A      arr[i][j] = arr[n-1-j][i];
                    P->M      arr[n-1-j][i] = arr[n-1-i][n-1-j];
                    D->P      arr[n-1-i][n-1-j] = arr[j][n-1-i];
                    A->D      arr[j][n-1-i] = temp
        这样相当于每次交换了4个元素，我们只要按循环将这个二维数组交换完毕即可
       3. 确定循环次数
         n为偶 和 n为奇两种情况 (画个图就理解了）
            n为偶：一共n的平方个元素，每次交换4个，一共需要n的平方/4 也就是以长 n/2 宽 n/2这块面积的元素作为起始元素交换即可完成
                 例如 n =4  一共 16个元素，一共交换4回即可完成。也就是  0<i<n/2    0<j<n/2 得到n为4时。 0<=i<2   0<=j<2
                 0<=i<n/2    0<=j<n/2
            n为奇: 中间元素不用动，也就是 （n的平方-1）/4 也就是 n-1/2 和 n+1/2 这块面积的元素作为起始元素交换即可完成
                例如n=5 一共25个元素，中间的元素不用动，即 0<=i<2  0<=j<3  即 i取 0，1。j取 0，1，2一共6个元素，每次4个一共能交换24个，中间不用动
                 n-1/2 和 n+1/2
        这里外层循环 取 n/2 ，内层取  n+1/2 兼容了奇偶两种情况
        对于外层 n/2，n为奇数的时候,n-1/2 和n/2的结果是相同的
        对于内层 n+1/2, n为偶数的时候,n/2 和 n+1/2的结果是相同的。
        这样就兼容了奇偶两种情况的循环次数

     本题是个数学题，总结规律题
     1.确定旋转前后的坐标变化，得到坐标公式，即 arr[i][j]=arr[j][n-1-i];
     2.确定每次循环交换的元素以及交换元素的坐标关系
         这一轮交换了4个元素：temp = A = arr[i][j]
         M->A      arr[i][j] = arr[n-1-j][i];
         P->M      arr[n-1-j][i] = arr[n-1-i][n-1-j];
         D->P      arr[n-1-i][n-1-j] = arr[j][n-1-i];
         A->D      arr[j][n-1-i] = temp
     3.确定循环面积的元素即：0<=i<n/2  0<=j<n+1/2


     */
    public static void main(String[] args) {
        int arr[][] = new int[3][3];
        arr[0] = new int[]{1,2,3};
        arr[1] = new int[]{4,5,6};
        arr[2] = new int[]{7,8,9};

        new leetcode48().rotate33(arr);
    }

    public void rotate33(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0 ; i < n/2 ; i++) {
            for(int j = 0 ; j < (n+1)/2 ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }

    // 对角线翻折 然后纵向翻折(不写了，看链接参考)
}
