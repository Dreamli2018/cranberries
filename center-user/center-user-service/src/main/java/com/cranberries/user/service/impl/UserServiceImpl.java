package com.cranberries.user.service.impl;

import com.cranberries.user.config.MqConfig;
import com.cranberries.user.mapper.UserMapper;
import com.cranberries.userapi.api.UserService;
import com.cranberries.userapi.vo.ResultVO;
import com.cranberries.userapi.vo.User;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RestController("/api-syncUserInfo")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String redis_prefix_key = "user_id_";

    public static final String routing_key = "my_routing_key.key1";

    @Override
    public ResultVO<String> register(User user) {

        ResultVO<String> resultVO = new ResultVO<>();

        String result = null;

        if (userMapper.register(user) > 0) {
            result = "注册成功！";
            // 保存用户信息到mongoDB
            this.mongoTemplate.save(user);
            String exchange = null;

            String routingKey = null;

            Object obj = null;
            this.rabbitTemplate.convertAndSend(exchange, routingKey, user);
            resultVO.setCode("200");
            resultVO.setMessage("Success");
            resultVO.setData(result);
        } else {
            log.warn("用户注册失败！");
            resultVO.setCode("2001");
            resultVO.setMessage("Failure");
        }


        return resultVO;
    }

    @Override
    public ResultVO<User> getUserById(int userId) {
        ResultVO<User> resultVO = new ResultVO<>();

        // 从redis缓存中查询用户信息
        User user;
        if (redisTemplate.opsForValue().get(redis_prefix_key + userId) == null) {// 不存在，则走数据库查询
            user = userMapper.getUserById(userId);
            if (user != null) {
                // 缓存数据到redis中, 10秒过期
                redisTemplate.opsForValue().set(redis_prefix_key + userId, user, 60000, TimeUnit.MILLISECONDS);
            } else {
                log.info("未查询到用户信息！");
                resultVO.setCode("2001");
                resultVO.setMessage("Failure");
                return resultVO;
            }
        } else {
            user = (User) redisTemplate.opsForValue().get(redis_prefix_key + userId);
        }

        resultVO.setCode("0");
        resultVO.setMessage("Success");
        resultVO.setData(user);
        return resultVO;
    }

    @Override
    public void updateUser(User user) {
        if (user != null && user.getId() != null) {
            this.userMapper.updateUser(user);
            // 同时更新mongoDB中数据
            Query query = new Query().addCriteria(Criteria.where("_id").is(5));
            this.mongoTemplate.updateFirst(query, Update.update("name", user.getName()), "user");
        } else {
            log.error("请求参数不能为空！");
        }
    }

    @Override
    public void sendUserIdList() {
        // 查询所有用户id
        List<Integer> idList = this.userMapper.queryIdList();
        if (CollectionUtils.isNotEmpty(idList)) {
            rabbitTemplate.convertAndSend(MqConfig.EXCHANGE, routing_key, idList);
        }
    }
}
