package com.cranberries.book.mq.rabbitmq;

import com.cranberries.book.config.mq.rabbitmq.RabbitmqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/08/26 16:42
 * @description ：
 */
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Message message, String topic){
        rabbitTemplate.convertAndSend(RabbitmqConfig.BOOK_EXCHANGE_NAME, topic, message.getBody());
    }

    public void sendDelay(Message message, String topic, Integer delay){
        rabbitTemplate.convertAndSend(RabbitmqConfig.BOOK_EXCHANGE_NAME, topic, message.getBody(), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
//                messageProperties.setDelay(delay);
                // 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
                messageProperties.setExpiration(1000*10 + "");
                return message;
            }
        });
    }
}
