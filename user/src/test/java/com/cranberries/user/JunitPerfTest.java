package com.cranberries.user;

import com.cranberries.user.model.User;
import com.cranberries.user.respnose.ResultVO;
import com.cranberries.user.service.UserService;
import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.annotation.JunitPerfRequire;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = UserApplication.class)
public class JunitPerfTest {

    @Autowired
    private UserService userService;

    @Test
//    @JunitPerfConfig(warmUp = 10, threads = 10, duration =1000, reporter = HtmlReporter.class)
//    @JunitPerfRequire(max = 500, min = 200, average = 300, timesPerSecond = 10, percentiles = {"20:200","30:300"})
    public void junitPerfTest(){
        ExecutorService executorService  =
                new ThreadPoolExecutor(2, 4, 10000, TimeUnit.MILLISECONDS
        , new LinkedBlockingDeque<>(1000000));

        long startTime = System.currentTimeMillis();

        String prefix = "test_";
        int size = 10000000;

        List<String> list = new ArrayList<>();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i < size; i++){
                    list.add(prefix + i);
                }
            }
        });


        long endTime = System.currentTimeMillis();

        System.out.println("执行时长" + (endTime - startTime) + "毫秒");

    }

    @Test
//    @JunitPerfConfig(warmUp = 10, threads = 10, duration =1000, reporter = HtmlReporter.class)
//    @JunitPerfRequire(max = 20, min = 1, average = 3, timesPerSecond = 4000, percentiles = {"20:2","30:3"})
    public void getUserTest(){

        ResultVO<User> userResultVO = userService.getUserById(3);
        User user = userResultVO.getData();
        System.out.println(user.toString());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    void randomTest(){
        String[] strArr = new String[]{"89917"};
        int bound = 10*10*10*10*10;

        for (int i = 1; i <= bound; i++){
            Random random = new Random();


            Integer num1 = random.nextInt(10);
            Integer num2 = random.nextInt(10);
            Integer num3 = random.nextInt(10);
            Integer num4 = random.nextInt(10);
            Integer num5 = random.nextInt(10);
            String s = num1.toString() + num2.toString() + num3.toString() + num4.toString() + num5.toString();

            if (Arrays.asList(strArr).contains(s)){
                System.out.println("i的值：" + i);
                System.out.println(s);
                break;
            }
        }
    }

    @Test
    void threadTest() {


    }

}
