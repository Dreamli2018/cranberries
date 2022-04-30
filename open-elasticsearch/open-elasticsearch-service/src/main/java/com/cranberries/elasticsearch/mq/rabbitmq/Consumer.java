package com.cranberries.elasticsearch.mq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.cranberries.book.dto.request.BookDTO;
import com.cranberries.elasticsearch.config.mq.rabbitmq.RabbitmqConfig;
import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.service.BookSearchService;
import com.cranberries.elasticsearch.service.impl.ThreadPoolCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/08/26 16:45
 * @description ：
 */
@Component
@Slf4j
public class Consumer {

    @Autowired
    private BookSearchService bookSearchService;

    @RabbitListener(queues = {RabbitmqConfig.BOOK_QUEUE_NAME, RabbitmqConfig.MUSIC_QUEUE_NAME}, ackMode = "MANUAL")
    @RabbitHandler
    public void consume(String message){
        log.info("消息内容:{}", message);
        BookDTO bookDTO = JSON.parseObject(message, BookDTO.class);
        if (bookDTO != null && bookDTO.getId() != null){
            Book book = bookSearchService.queryById(bookDTO.getId());
            if (book == null || !book.getName().equals(bookDTO.getName())){
                synchronized (bookDTO) {
                    ThreadPoolCommon.create().execute(new Runnable() {
                        @Override
                        public void run() {
                            bookSearchService.save(null);
                        }
                    });
                }
            }
        }

    }
}
