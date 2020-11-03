package com.cranberries.userapi.api;


import com.cranberries.userapi.vo.ResultVO;
import com.cranberries.userapi.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user", contextId = "syncUserInfo")
public interface UserService {

    ResultVO<String> register(User user);

    @GetMapping("/api-syncUserInfo/getUserById")
    ResultVO<User> getUserById(int userId);

    void updateUser(User user);

    @GetMapping("/api-syncUserInfo/sendUserIdList")
    void sendUserIdList();
}
