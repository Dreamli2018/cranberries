package com.cranberries.user;

import com.cranberries.userapi.vo.User;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2021/02/23 11:35
 * @description ：测试流式编程
 */
public class StreamTest {

    @Test
    void test01() {
        List<User> list = new ArrayList<>(2048);
        String idCard = "41132119891020";
        for (int i = 0; i < 2048; i++) {
            User user = new User();
            if (i % 5 == 0) {
                user.setGender(1);
            } else {
                user.setGender(0);
            }
            user.setAge(i);
            user.setIdCard(idCard + i);
            list.add(user);
        }

        if (CollectionUtils.isNotEmpty(list)) {
            Stream<User> userStream = list.stream();
//            int sum = userStream.filter(user -> user.getGender() == 0).mapToInt(User::getAge).sum();
//            System.out.println("男性年龄总和：" + sum);
            List<Integer> ageList = userStream.filter(Objects::nonNull).filter(user -> user.getGender() == 0).map(User::getAge).collect(Collectors.toList());
            System.out.println("男性年龄集合：" + ageList);
        }
    }

    @Test
    void cpuTest() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println("本机cpu核数：" + cpuNum);
    }
}
