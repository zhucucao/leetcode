package com.zym.leetcode.bsf;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/bfs-kuang-jia
public class Num757 {
    public static void main(String[] args) {
        java.lang.String[] dead = {"8888"};
        String target = "0009";
        int i = openLock(dead, target);
        System.out.println(i);
    }

    public static  int openLock(String[] deadends, String target) {
        HashSet deadSet = new HashSet<String>();
        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        if(deadSet.contains("0000")) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visitSet = new HashSet<>();

        queue.add("0000");
        visitSet.add("0000");
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                String poll = queue.poll();

                if(deadSet.contains(poll)) {
                    continue;
                }
                if(poll.equalsIgnoreCase(target)) {
                    return level;
                }

                for(int index = 0 ; index < 4; index++) {
                    String upStr = upNumber(poll, index);
                    String downStr = downNumber(poll, index);
                    if(!visitSet.contains(upStr)) {
                        queue.add(upStr);
                        visitSet.add(upStr);
                    }
                    if(!visitSet.contains(downStr)) {
                        queue.add(downStr);
                        visitSet.add(downStr);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public  static  String upNumber(String num,int index) {
        char[] charArray = num.toCharArray();
        if(charArray[index] == '9') {
            charArray[index] = '0';
        }else {
            charArray[index] +=1;
        }
        return new String(charArray);


    }

    public static String downNumber(String num,int index) {
        char[] charArray = num.toCharArray();
        if(charArray[index] == '0') {
            charArray[index] = '9';
        }else {
            charArray[index] -=1;
        }
        return new String(charArray);

    }
}
