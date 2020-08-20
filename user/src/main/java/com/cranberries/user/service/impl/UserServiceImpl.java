package com.cranberries.user.service.impl;

import com.cranberries.user.mapper.UserMapper;
import com.cranberries.user.model.User;
import com.cranberries.user.respnose.ResultVO;
import com.cranberries.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

//    @Resource
//    private UserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String redis_prefix_key = "user_id_";

    @Override
    public ResultVO<String> register(User user) {

        ResultVO<String> resultVO = new ResultVO<>();

        String result = null;

        if (userMapper.register(user) > 0) {
            result = "注册成功！";
            resultVO.setResultCode("0");
            resultVO.setResultMsg("Success");
            resultVO.setData(result);
        } else {
            log.warn("用户注册失败！");
            resultVO.setResultCode("2001");
            resultVO.setResultMsg("Failure");
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
                resultVO.setResultCode("2001");
                resultVO.setResultMsg("Failure");
                return resultVO;
            }
        } else {
            user = (User) redisTemplate.opsForValue().get(redis_prefix_key + userId);
        }

        resultVO.setResultCode("0");
        resultVO.setResultMsg("Success");
        resultVO.setData(user);
        return resultVO;
    }
}
