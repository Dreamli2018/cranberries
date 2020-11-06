package com.cranberries.user;

import com.cranberries.user.config.MqConfig;
import com.cranberries.user.model.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/22 11:24
 * @description ：消息队列测试类
 */
@Component
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class MqTest {

    @RabbitListener(queues = {MqConfig.QUEUE})
    void listen(User msg, Message message, Channel channel) {

        log.info("receive a message " + msg);
    }
}
