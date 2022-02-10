package com.zym.java.tl;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("111");
        threadLocal.set("222");
        System.out.println(threadLocal.get());
    }
    @Test
    public void threadTest() throws  Exception{
        ThreadLocal<String> integerThreadLocal = new ThreadLocal<>();

        Random random = new Random();

        IntStream.range(0,5).forEach(e -> new Thread(() ->{
            integerThreadLocal.set(e + " " + random.nextInt(10));
            System.out.println("当前local和线程值分别是:" + integerThreadLocal.get());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception ex) {

            }
        }).start());
    }
}
