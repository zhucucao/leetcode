package com.zym.java.testAnnotation;

import java.lang.annotation.*;

public class MyTestAnnotation {


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    @Documented
    public @interface TestAnnotation{
        int price() default 100;
        String desc() default "打折价";
    }


    @TestAnnotation(price = 200,desc = "腿打折")
    class Father{

    }

    class Son extends Father{

    }

     static class ttt{
        public static void main(String[] args) {
            // 获取son的类对象
            Class<Son> sonClass = Son.class;
            // 获取son类的注解类型 不报错
            TestAnnotation declaredAnnotation = sonClass.getDeclaredAnnotation(TestAnnotation.class);
        }
    }

    @TestAnnotation(price = 200,desc = "不打折")
    class MyTest{

    }
}
