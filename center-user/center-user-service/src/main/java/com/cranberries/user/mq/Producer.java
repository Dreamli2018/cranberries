package com.cranberries.user.mq;

import com.cranberries.user.config.MqConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/21 18:00
 * @description ：消息生产者
 */
@Component
public class Producer {

    private static final String queue = "my_queue";
    private static final String exchange = "my_exchange";
    private static final String exchangeType = "topic";
    private static final String exchangeRoutingKey = "my_routing_key.#";

    public static void send() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = MqConfig.getConnectionFactory();

        // 创建新的连接
        Connection connection = connectionFactory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(exchange, exchangeType, true,false,false, null);
        // 绑定队列到交换机
        channel.queueBind(queue,exchange, exchangeRoutingKey);

        // 消息内容
        String message = "Hello MQ !";
        // 发消息用的routingKey
        String routingKey = "my_routing_key.key1";
        channel.basicPublish(exchange, routingKey, null, message.getBytes());

        System.out.println("send message" + message);

        // 关闭连接
        channel.close();
        connection.close();

    }
}
