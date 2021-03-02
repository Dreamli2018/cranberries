package com.cranberries.user.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2021/03/02 10:56
 * @description ：线程池通用工具类
 */
@Configuration
@EnableAsync
public class ThreadPoolUtils implements AsyncConfigurer {

    public static ExecutorService createDefaultExecutorService() {
        SynchronousQueue executorQueue = new SynchronousQueue();

        return new ThreadPoolExecutor(4, 256, 60L, TimeUnit.SECONDS,
                executorQueue);
    }
}
