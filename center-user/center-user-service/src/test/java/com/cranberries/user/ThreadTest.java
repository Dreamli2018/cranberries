package com.cranberries.user;

public class ThreadTest {

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                world();
            }
        };

        t.start();
//        t.run();
        System.out.println("Hello ");
    }

    static void world() {
        System.out.println("world");
    }
}
