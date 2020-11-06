package com.cranberries.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.cranberries.data.config.RabbitMqConfig;
import com.cranberries.data.mapper.SyncUserDataMapper;
import com.cranberries.data.service.SyncUserDataService;
import com.cranberries.userapi.api.UserService;
import com.cranberries.userapi.vo.ResultVO;
import com.cranberries.userapi.vo.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;


    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void listen(List<Integer> ids, Message message, Channel channel) {
        log.info("----------------" + JSON.toJSONString(ids) + "----------------");
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                ids.forEach(id -> {
                    ResultVO<User> resultVO = this.userService.getUserById(id);
                    User user = resultVO.getData();
                    this.mongoTemplate.save(user,"user");
                });
            }
        } catch (Exception e) {
            log.error("同步更新用户信息到mongoDB异常:{}", e.getMessage());
        }
    }

    @Override
    public void syncHandleUserData() {

    }
}
