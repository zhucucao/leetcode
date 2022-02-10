package com.zym.java.neibulei;

public class neibulei2 {
    private int age = 20;

    class Inner2{
        public int age =25;

        public void say() {
            int age =30;
            System.out.println("1:"+age);
            System.out.println("2:"+this.age);
            System.out.println("3:"+neibulei2.this.age);
        }
    }
}

class test2{
    public static void main(String[] args) {
        neibulei2.Inner2 inner2 = new neibulei2().new Inner2();
        inner2.say();
    }
}
