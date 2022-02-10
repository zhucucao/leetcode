package com.zym.java.neibulei;

import java.util.HashMap;

public class Neibulei3 {
    private int age = 20;
    private static int age2 = 30;

    static class Inner{
        private int age4 = 5;
        public void show() {
            System.out.println(age2);
        }
    }
}

class Test{
    public static void main(String[] args) {
        Neibulei3.Inner in = new Neibulei3.Inner();
        in.show();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

    }
}
