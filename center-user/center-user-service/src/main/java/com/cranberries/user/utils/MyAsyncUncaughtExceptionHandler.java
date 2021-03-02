package com.cranberries.user.utils;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author ：lidemin
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/03/02 13:19
 * @description ：
 */
public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {

    }
}
