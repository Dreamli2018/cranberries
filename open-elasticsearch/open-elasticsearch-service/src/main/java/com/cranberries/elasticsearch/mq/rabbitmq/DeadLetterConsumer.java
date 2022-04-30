package com.cranberries.elasticsearch.mq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.cranberries.book.dto.request.BookDTO;
import com.cranberries.elasticsearch.config.mq.rabbitmq.RabbitmqConfig;
import com.cranberries.elasticsearch.entity.Book;
import com.cranberries.elasticsearch.service.BookSearchService;
import com.cranberries.elasticsearch.service.impl.ThreadPoolCommon;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/11/23 09:32
 * @description ：
 */

@Component
@Slf4j
public class DeadLetterConsumer {

    @Autowired
    private BookSearchService bookSearchService;

    @RabbitHandler
    @RabbitListener(queues = RabbitmqConfig.DEAD_LETTER_QUEUE, ackMode = "MANUAL")
    public void consume(String message, Channel channel) throws IOException {
        log.info("死信消息内容:{}", message);
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
        channel.basicAck(1000L, false);
        log.info("消费死信消息完成");
    }
}
