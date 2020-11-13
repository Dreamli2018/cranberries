package com.cranberries.user;

import com.cranberries.user.model.User;
import com.cranberries.user.utils.BeanCompareUtils;
import org.junit.jupiter.api.Test;

/**
 * @author ：lidemin
 * @version ：V2.0
 * @program ：cranberries
 * @date ：Created in 2020/11/11 16:26
 * @description ：比较对象测试类
 */
public class CompareBeanTest {


    @Test
    void compareUserTest() {

        User oldUser = new User();
        oldUser.setPhone("18765908621");
        oldUser.setName("dreamLi2018");
        User newUser = new User();
        newUser.setName("dreamLi2020");
        newUser.setPhone("15201963513");

        boolean flag = BeanCompareUtils.compareBeans(oldUser, newUser);

        if (flag) {
            System.out.println("对象相同！");
        } else {
            System.out.println("对象不同！");

        }
    }
}
