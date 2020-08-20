package com.cranberries.user;

import java.util.ArrayList;

/**
 * @author ：lidemin
 * @version ：V2.0
 * @program ：store
 * @date ：Created in 2020/06/18 19:20
 * @description ：This is a test class
 */
public class TestAnnotation {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ArrayList<String> list = new ArrayList<>(10000);

        for (int i = 0; i < list.size(); i++) {
            list.add("item" + i);
            System.out.println("test new branch");
        }

    }
}
