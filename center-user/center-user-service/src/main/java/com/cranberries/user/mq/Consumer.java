package com.cranberries.user.mq;

import com.cranberries.user.config.MqConfig;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/21 19:50
 * @description ：消息消费者
 */
public class Consumer {

    private static final String queue = "my_queue";
    private static final String exchange = "my_exchange";
    private static final String exchangeType = "topic";
    private static final String exchangeRoutingKey = "my_routing_key.#";

    public static void receive() throws IOException, TimeoutException {
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
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body);
            }
        };
        channel.basicConsume(queue, true, consumer);

        System.out.println("send message" + message);

        // 关闭连接
        channel.close();
        connection.close();

    }


}
