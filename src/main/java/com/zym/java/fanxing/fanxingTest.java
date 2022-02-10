package com.zym.java.fanxing;

class FanXingTest<T> {
    public T t;

    public void setData(T t) {
        this.t = t;
    }

    public T getData(T t) {
        return t;
    }

    public <T> void addT(T t) {
        System.out.println(t.getClass());
    }

    public static void main(String[] args) {
        FanXingTest<String> fanXingTest = new FanXingTest<>();

        fanXingTest.setData("ttt");

        fanXingTest.addT(123);

        Box<String, Integer> box = new Box<>();

        box.add("str1",123);


    }


    static class Box<T,S> {
        private T t;
        private S s;

        public void add(T t, S s) {
            this.t = t;
            this.s = s;
        }

        public T getT() {
            return t;
        }

        public S getS() {
            return  s;
        }


    }

}
