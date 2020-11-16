package com.zym.leetcode.daycode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

// 312. 戳气球
public class Number312 {
    public static void main(String[] args) {
        Integer nums[]= {3,1,2,1,5};
        List<Integer> list = Arrays.asList(nums);
        sortList(list);
    }

    public static  int getMaxSum(List<Integer> list) {
        int size = list.size();
        if(list.size() == 0) {
            return  0;
        } else if(size == 1 || size == 2 || size == 3) {
            int num = 1;
            for (int i = 0; i < list.size(); i++) {
                num = num * list.get(i);
            }
            return  num;
        }

        return  0;
    }

    public static int  sortList(List<Integer> list) {
//        list.sort(Comparator.naturalOrder());
        Integer min = Collections.min(list);
        return min;
    }

}
