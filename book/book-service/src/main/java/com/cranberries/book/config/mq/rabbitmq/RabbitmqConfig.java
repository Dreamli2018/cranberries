package com.cranberries.book.config.mq.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/08/26 10:06
 * @description ：
 */
@Configuration
public class RabbitmqConfig {

    public static final String BOOK_EXCHANGE_NAME = "BOOK_EXCHANGE";

    public static final String BOOK_TOPIC = "BOOK_TOPIC";

    public static final String MUSIC_TOPIC = "MUSIC_TOPIC";

    public static final String DEAD_LETTER_EXCHANGE_NAME = "DEAD_LETTER_EXCHANGE";

    @Bean
    public DirectExchange topicExchange(){
//        Map<String, Object> pros = new HashMap<>();
//        pros.put("x-delayed-", "topic");
        DirectExchange topicExchange = new DirectExchange(BOOK_EXCHANGE_NAME, true, false);
//        topicExchange.setDelayed(true);
        return topicExchange;
    }
}
