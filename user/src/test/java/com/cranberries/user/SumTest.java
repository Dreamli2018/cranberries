package com.cranberries.user;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/08/27 17:24
 * @description ：测试求集合的和值
 */
public class SumTest {

    @Test
    void sumListTest() {

        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(5);
        list.add(19);
        list.add(25);
        int temp = 0;
        for (int i = 0; i < list.size(); i++) {
            temp += list.get(i);

        }


        System.out.println("集合数据的和：" + temp);
    }
}
