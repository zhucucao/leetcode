package com.zym.leetcode.dongtaiguihua;

public class Number509 {
    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 给定 N，计算 F(N)。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
     * 示例 2：
     *
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
     * 示例 3：
     *
     * 输入：4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
     *  
     *
     * 提示：
     *
     * 0 ≤ N ≤ 30
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fibonacci-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * https://blog.csdn.net/qq30211478/article/details/78335142
     * 关于递归和迭代写的便于理解的文章
     * 0,1，1，2，3，5，8
     */
    public static void main(String[] args) {
//        int result = fib(4);
//        System.out.println(result);

//        int N = 0;
//        int[] arr = new int[N + 1];
//        int result = recordSolve(arr, N);
//        System.out.println(result);

//        int result = dpSolve(1);
//        System.out.println(result);
        // 迭代解法
        int i = loopSolve(4);
        System.out.println(i);
    }
    // 迭代解法
    public static int loopSolve(int N) {
        if(N == 0) {
            return  0;
        }
        if(N == 1 || N==2) {
            return 1;
        }
        int prev = 1,current = 1;
        int sum = 0;
        for(int i = 3; i<=N ; i++) {
            sum = prev + current;
            prev = current;
            current = sum;
        }
        return sum;
    }
    // dpTable解法 自底向上
    public static int dpSolve(int N) {
        if(N == 0) {
            return 0;
        }
        if(N == 1) {
            return  1;
        }
        int[] intArr = new int[N + 1];
        intArr[1] = 1; intArr[2] = 1;
        for(int i = 3 ; i <=N ; i++) {
            intArr[i] = intArr[i-1] + intArr[i-2];
        }
        return intArr[N];

    }
    // 带备忘录的递归解法
    public static  int recordSolve(int[]recordArr, int N) {
        if(N == 0) {
            return 0;
        }
        if(N ==1 || N==2) {
            return 1;
        }
        if(recordArr[N] != 0) {
            return  recordArr[N];
        }
        recordArr[N] = recordSolve(recordArr,N-1) + recordSolve(recordArr, N-2);
        return recordArr[N];
    }

    // 暴力解法 递归
    public static int fib(int n) {
        if(n ==0) {
            return 0;
        }
        if(n ==1) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }
}
