package com.cranberries.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.cranberries.elasticsearch.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/11/05 11:28
 * @description ：
 */
@Component
@Slf4j
public class BookAsync {

    @Async
   public void asyncMethod(List<Book> books) {
        books.forEach(book -> {
            ThreadPoolCommon.create().execute(new Runnable() {
                @Override
                public void run() {
                    log.info("书籍信息：{}", JSON.toJSONString(book));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        System.out.println("异步方法执行时间:" + new Date());
    }
}
