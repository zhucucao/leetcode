package com.zym.java.finallyTest;

public class FinallyTest {

    public static void main(String[] args) {
        int i = tryTest();
        System.out.println("返回:" + i);
    }

    public static int tryTest() {
        try{
            System.out.println(Integer.parseInt("sss"));
            System.out.println("----");
            return 1;
        }catch (NullPointerException e) {
            System.err.println("error");
        }catch (Exception e){
            return 2;
        }finally {
            System.out.println("finally执行");
            return 3;
        }
    }
}
