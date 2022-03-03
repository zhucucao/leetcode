package com.zym.newleetcode.数组.模拟;

import java.util.ArrayList;
import java.util.List;

public class leetcode54 {

    public static void main(String[] args) {
        int arr[][] = new int[3][4];
        arr[0] = new int[]{1,2,3,4};
        arr[1] = new int[]{5,6,7,8};
        arr[2] = new int[]{9,10,11,12};
        List<Integer> list = new leetcode54().spiralOrder2(arr);
        System.out.println(list);
    }

    /**
     * 思想与59相同 看微信的那篇文章
     * 同样是定义四条线 up  left right down分别将矩阵划分
     * 依次从左到右 从上到下 从右往左 从下往上 遍历
     *  但是与59不同的是 59是n*n的矩阵，也就是无非是奇数或者偶数产生的矩阵
     *  3 * 3 或者 4 * 4 奇数的矩阵最内的一圈都是一个数字，偶数的矩阵最内的一圈4个数字
     * 这样对于奇数的矩阵最终的循环前：
     *  left 与 right 🈯的同一列  up与down 指向同一行
     * 在先从左到右填充后.up++以后：up > down 就避免了从上往下的重复填充(也避免了从下往上的填充)。
     * 然后right--以后right < left 就避免了从右往左的重复填充
     *
     * 对于偶数的矩阵最终的循环和 2 * 2矩阵一样
     *
     *
     * 对于此题并未规定是什么样的二维数组
     * 例如
     *  1  2  3   4
     *  5  6  7   8
     *  9  10 11 12
     *
     *  在外圈循环以后就剩了个6，7未遍历
     *  此时
     *      left 和 right 分别指向 1 ， 2 列
     *      up 和 down 一同指向了 1 行
     *      在从左往右填充后 6 ,7 后 up++ 避免了从上往下重复填充
     *      然后 right -- 指向了第一列 此时left right重叠列
     *      而后就重复填充了6 所以每次进行填充的时候都要判断一下结果集的size是否满足
     *
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

    /**
        与上面不同的是：这里判断 up 与down 小。right 和 up的大小来判断提前结束循环
     *
     * 这里的方法不需要记录已经走过的路径，所以执行用时和内存消耗都相对较小
     *
     * 首先设定上下左右边界
     * 其次向右移动到最右，此时第一行因为已经使用过了，可以将其从图中删去，体现在代码中就是重新定义上边界
     * 判断若重新定义后，上下边界交错，表明螺旋矩阵遍历结束，跳出循环，返回答案
     * 若上下边界不交错，则遍历还未结束，接着向下向左向上移动，操作过程与第一，二步同理
     * 不断循环以上步骤，直到某两条边界交错，跳出循环，返回答案
     *
     * 作者：youlookdeliciousc
     * 链接：https://leetcode-cn.com/problems/spiral-matrix/solution/cxiang-xi-ti-jie-by-youlookdeliciousc-3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        int left = 0; int right = matrix[0].length - 1;
        int up = 0 ; int down = matrix.length - 1;
        ArrayList<Integer> resultList = new ArrayList<>();
        while (true) {
           for(int i = left ; i <= right ; i++) {
               resultList.add(matrix[left][i]);
           }
           if(++up > down) {
               break;
           }


           for(int j = up; j <= down ; j++) {
               resultList.add(matrix[j][right]);
           }

           if(--right < left) {
               break;
           }


           for(int k = right ; k >= left ; k--) {
               resultList.add(matrix[down][k]);
           }
           if(--down < up) {
               break;
           }

           for(int m = down ; m >= up ; m--) {
               resultList.add(matrix[m][left]);
           }
           if(++left > right) {
               break;
           }
        }
        return resultList ;
    }

}
