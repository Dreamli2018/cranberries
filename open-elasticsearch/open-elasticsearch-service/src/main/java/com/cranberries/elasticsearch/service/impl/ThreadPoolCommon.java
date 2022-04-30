package com.cranberries.elasticsearch.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2022/04/30 19:04
 * @description ：
 */
public class ThreadPoolCommon {

    public static ExecutorService create(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 20, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        return threadPoolExecutor;

    }
}
