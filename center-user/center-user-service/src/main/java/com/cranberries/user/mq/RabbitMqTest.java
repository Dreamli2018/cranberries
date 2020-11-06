package com.cranberries.user.mq;

import com.cranberries.userapi.vo.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/22 11:28
 * @description ：消息接收者
 */
@Component
@Slf4j
public class RabbitMqTest {

//    @RabbitListener(queues = {MqConfig.QUEUE})
    public void listen(User msg, Message message, Channel channel) throws InterruptedException {
        Thread.sleep(10000);
        log.info("receive a message " + msg);
    }
}
