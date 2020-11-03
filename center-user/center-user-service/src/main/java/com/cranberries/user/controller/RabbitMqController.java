package com.cranberries.user.controller;

import com.cranberries.userapi.api.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/22 11:36
 * @description ：消息发送者
 */
@RestController
@RequestMapping("/rabbit-mq")
public class RabbitMqController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @GetMapping("/send")
    public void send() {

        this.userService.sendUserIdList();

    }
}
