package com.zym.java.neibulei;

public class NeiBuLei {
    private int age = 20;

    public void eat() {
        Inner inner = new Inner();
        System.err.println(inner.value);
    }

    class Inner{
        private String value = "aaa";
        public void run() {
            eat();
            System.out.println(NeiBuLei.this.age);
        }
    }
}

class test{
    public static void main(String[] args) {
        NeiBuLei.Inner inner = new NeiBuLei().new Inner();
        inner.run();
    }
}
