package com.cranberries.user;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author ：lidemin
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2020/09/29 19:06
 * @description ：
 */
public class CalTest {

    @Test
    void testDoubleCal() {
        int num = 500;
        int size = 3;

        double rate = 6.9858;
        double price = 100;


        BigDecimal num1 = new BigDecimal(Double.valueOf(num));
        BigDecimal size1 = new BigDecimal(Double.valueOf(size));
        BigDecimal rate1 = new BigDecimal(Double.valueOf(rate));
        BigDecimal price1 = new BigDecimal(Double.valueOf(price));

//        double total1 = num1.divide(size1).multiply(rate1).multiply(price1).doubleValue();

        BigDecimal multiply = rate1.multiply(price1);
        BigDecimal divide = num1.divide(size1, 4,BigDecimal.ROUND_HALF_UP);
        double multiply1 = divide.multiply(rate1).multiply(price1).doubleValue();
        System.out.println("总产值：" + multiply1);

    }

    @Test
    void castStringToDouble() {
        Double obj = 6.5816;

        Double rate = Double.valueOf(obj.toString());

        System.out.println(rate);
    }

    @Test
    void testCal() {
        int a = 2;
        Double b = a* 0.3;

        System.out.println(b);
    }

    @Test
    void name() {
        String str = "油管（Hydraulic Hose）";

        String str1 = "油管（Hydraulic Hose）";

        System.out.println(str.equals(str1));
    }
}
