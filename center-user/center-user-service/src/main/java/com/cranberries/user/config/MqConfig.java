package com.cranberries.user.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/21 19:53
 * @description ：RabbitMQ配置
 */
@Configuration
public class MqConfig {

    public final static String QUEUE = "my_queue";
    public final static String EXCHANGE = "my_exchange";
    public final static String ROUTING_KEY = "my_routing_key.#";

    public static ConnectionFactory getConnectionFactory() {
        return null;
    }


    // 声明队列
    @Bean(QUEUE)
    public Queue queue() {
        return new Queue(QUEUE);
    }

    // 声明交换机
    @Bean(EXCHANGE)
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    // 绑定交换机和队列
    @Bean
    public Binding binding(@Qualifier(EXCHANGE) Exchange exchange, @Qualifier(QUEUE) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

}
