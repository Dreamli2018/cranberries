package com.cranberries.elasticsearch.config.mq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/08/26 16:47
 * @description ：
 */
@Configuration
public class RabbitmqConfig {

    public static final String BOOK_EXCHANGE_NAME = "BOOK_EXCHANGE";

    public static final String BOOK_QUEUE_NAME = "BOOK_QUEUE";

    public static final String MUSIC_QUEUE_NAME = "MUSIC_QUEUE";

    public static final String BOOK_TOPIC = "BOOK_TOPIC";

    public static final String MUSIC_TOPIC = "MUSIC_TOPIC";

    public static final String DEAD_LETTER_EXCHANGE_NAME = "DEAD_LETTER_EXCHANGE";

    public static final String DEAD_LETTER_QUEUE = "DEAD_LETTER_QUEUE";

    public static final String DEAD_LETTER_ROUTING_KEY = "DEAD_LETTER";

    //死信队列 交换机标识符
    public static final String X_DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";

    //死信队列交换机绑定键标识符
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";



    @Bean
    public DirectExchange topicExchange(){
//        Map<String, Object> pros = new HashMap<>();
//        pros.put("x-delayed-message", "topic");
        DirectExchange topicExchange = new DirectExchange(BOOK_EXCHANGE_NAME, true, false);
//        topicExchange.setDelayed(true);
        return topicExchange;
    }

    @Bean
    public Queue bookQueue(){
        // 将普通队列绑定到死信队列交换机上
        Map<String, Object> args = new HashMap<>(2);
        //args.put("x-message-ttl", 5 * 1000);//直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活
        //这里采用发送消息动态设置延迟时间,这样我们可以灵活控制
        args.put(X_DEAD_LETTER_QUEUE_KEY, DEAD_LETTER_EXCHANGE_NAME);
        args.put(X_DEAD_LETTER_ROUTING_KEY, DEAD_LETTER_ROUTING_KEY);
        return new Queue(BOOK_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public Queue musicQueue(){
        return new Queue(MUSIC_QUEUE_NAME, true);
    }

    @Bean
    public Binding bookBinding(){
        return BindingBuilder.bind(bookQueue()).to(topicExchange()).with(BOOK_TOPIC);
    }

    @Bean
    public Binding musicBinding(){
        return BindingBuilder.bind(musicQueue()).to(topicExchange()).with(MUSIC_TOPIC);
    }

    @Bean
    public DirectExchange deadExchange(){
        DirectExchange directExchange = new DirectExchange(DEAD_LETTER_EXCHANGE_NAME);
        return directExchange;
    }

    @Bean
    public Queue deadQueue(){
        Queue queue = new Queue(DEAD_LETTER_QUEUE, true);
        return queue;
    }

    @Bean
    public Binding deadBinding(){

        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }
}
