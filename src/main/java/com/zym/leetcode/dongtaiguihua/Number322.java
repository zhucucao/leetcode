package com.zym.leetcode.dongtaiguihua;

import org.jetbrains.annotations.TestOnly;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 腾讯2面题
 */
public class Number322 {
//    public static void main(String[] args) {
//        diedaiSolve(new int[]{86,419,83,408},6249);
//    }
    // 贪心算法 有局限 可看下面正确解决办法的链接解释
//    public static void diedaiSolve(int[] coins,int amount) {
//        List<Integer> list = Arrays.stream(coins).boxed().collect(Collectors.toList());
//        list.sort(Comparator.naturalOrder());
//        int recordSum  = 0;
//        int currentIcon = 0;
//        int yushu = 0;
//        boolean exist = true;
//        for(int i = list.size()-1 ; i >= 0; i--) {
//            currentIcon = list.get(i);
//            recordSum += amount / currentIcon ;
//            yushu = amount % currentIcon;
//            amount = yushu;
//            if(list.indexOf(amount) !=-1) {
//                recordSum+=1;
//                break;
//            }
//            if(yushu == 0) {
//                break;
//            }
//            if(i == 0 && list.indexOf(yushu) == -1) {
//                exist = false;
//            }
//        }
//        System.out.println(exist ? recordSum : -1);
//    }


    public static void t1(int amount) {
        amount = 15;
        int [] f = new int[amount + 1];
        int cost;
        f[0] = 0;
        for(int i = 1; i <= amount; i++){
            cost = Integer.MAX_VALUE;
            if(i - 1 >=0){
                cost = Math.min(cost, f[i-1] + 1);
            };
            if(i - 5 >=0) {
                cost = Math.min(cost, f[i-5] + 1);
            };
            if(i - 11 >=0){
                cost = Math.min(cost, f[i-11] + 1);
            }
            f[i]=cost;
        }
        System.out.println(f[amount]);
    }
// -------------------------------------------------------------------------------------------------
//    public static void main(String[] args) {
////        t1(15);
//
//        int[] coins = {186,419,83,408};
//        int amout = 6249;
//        t2(coins,amout);
//    }

    /**
     * https://leetcode-cn.com/problems/coin-change/solution/zi-di-xiang-shang-dong-tai-gui-hua-by-pendygg/
     * https://www.bilibili.com/video/BV1G7411B7Et/?spm_id_from=333.788.videocard.0
     * https://www.cnblogs.com/ysw-go/p/11935928.html
     * https://leetcode-cn.com/problems/coin-change/solution/zi-di-xiang-shang-dong-tai-gui-hua-by-pendygg/
     * @param coins
     * @param amount
     */
    public static int t2(int[]coins,int amount) {
        // 相当于记录从 0 至 amount 之间每一个数 对应的能凑出这个数的 硬币个数(循环对硬币数组中的所有面值计算)的最优解
        // 最后得到的 f[amount] 就是凑到 amount 这个数值硬币个数的最优解
        int [] f = new int[amount + 1];
        int cost;
        f[0] = 0;
        int currentCoin = 0;
        for(int i = 1 ; i <= amount ; i++) {
            cost =  Integer.MAX_VALUE;
            for(int j = 0 ; j < coins.length ; j++) {
                currentCoin= coins[j];
                if(i - currentCoin >= 0) {
                    if(f[i - currentCoin] != Integer.MAX_VALUE) {
                        cost = Math.min(cost,f[i - currentCoin] + 1);
                    }
                }
            }
            f[i] = cost;
        }
        System.out.println(f[amount]);
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }



    /**
     * https://leetcode-cn.com/problems/coin-change/solution/322-by-ikaruga/
     * 贪心 + 剪枝
     */
//    public static void main(String[] args) {
//        int[] coins = {1,2,5};
//        for(int i = 0 ; i < coins.length -1 ; i++) {
//            for(int j = 0 ; j < coins.length - 1 -i; j++) {
//                if(coins[j] < coins[j+1]) {
//                    int temp = coins[j];
//                    coins[j] = coins[j+1];
//                    coins[j + 1] = temp;
//                }
//            }
//        }
//        int amount = 11;
////        Arrays.sort(coins,new Comparator<Integer>(){
////            @Override
////            public int compare(Integer o1, Integer o2) {
////                return o2 - o1;
////            }
////        });
//        coinChange(coins,amount,0,0);
//        System.out.println(minNeedCount == Integer.MAX_VALUE ? -1 : minNeedCount);
//    }
    static Integer minNeedCount = Integer.MAX_VALUE;
    public  static void coinChange(int[] coins, int amount,int index, int count) {
        if(amount == 0) {
            minNeedCount = Math.min(count,minNeedCount);
            return;
        }
        if(index == coins.length) {
            return;
        }
        for(int i = amount / coins[index] ; i >= 0 && i + count < minNeedCount; i--) {
            coinChange(coins,amount - (i * coins[index]),index+1,count + i);
        }
    }


    //--------------------------------------------------------------------------------------------------------------------------------------


    public static void main(String[] args) {
        int i = coinChange(new int[]{1, 3}, 3);
        System.out.println(i);
    }

    //
    public static int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private static int coinChange(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
    }

}
