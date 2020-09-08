package com.cranberries.data.service.impl;

import com.cranberries.data.mapper.SyncUserDataMapper;
import com.cranberries.data.service.SyncUserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2020/09/06 12:17
 * @description ：同步用户数据服务
 */

@Service
@Slf4j
public class SyncUserDataServiceImpl implements SyncUserDataService {

    @Autowired
    private SyncUserDataMapper syncUserDataMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    @RabbitListener(queues = "user.update")
    public void syncHandleUserData(List<Integer> idList) {

        String exchange = null;

        String routingkey
                = null;
        Object obj = null;
        this.rabbitTemplate.convertAndSend(exchange, routingkey, obj);
    }
}
