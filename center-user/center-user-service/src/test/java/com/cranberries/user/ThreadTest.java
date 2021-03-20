package com.cranberries.user;

import com.cranberries.user.utils.Args;
import com.cranberries.user.utils.ThreadPoolUtils;
import com.cranberries.userapi.api.UserService;
import com.cranberries.userapi.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserApplication.class)
public class ThreadTest {

    @Autowired
    private UserService userService;

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


    @Test
    void testThreadPool() {
        long start = System.currentTimeMillis();
        ThreadPoolUtils.createDefaultExecutorService(Args.corePoolSize).execute(() -> {
            for (int i = 0; i <= 1000; i++) {
                User user = new User();
                user.setIdCard("411321198910203918" + i);
                userService.register(user);
            }
        });
        long end = System.currentTimeMillis();

        System.out.println("执行耗时：" + (end - start) + "ms");
    }

    @Test
    void testRegister() {
        long start = System.currentTimeMillis();

        for (int i = 0; i <= 1000; i++) {
            User user = new User();
            user.setIdCard("411321198910203918" + i);
            userService.register(user);
        }

        long end = System.currentTimeMillis();

        System.out.println("执行耗时：" + (end - start) + "ms");
    }

}
