package com.cranberries.user;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class GcTest {

    private static final int _1MB = 1024*1024;

    @Test
    void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
//        allocation1 = new byte[30 * _1MB];
//        allocation2 = new byte[30 * _1MB];
//        allocation3 = new byte[30 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    @Test
    void testPretenureSizeThreshold() {

        byte[] allocation4 = new byte[4 * _1MB];
    }

    @Test
    void testDate() {
        // 设置过期有效期为当前时间往后延一天
        Date date = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);
        date = calendar.getTime();

        System.out.println("当前日期：" + date);

        System.out.println(System.currentTimeMillis());
    }
}
