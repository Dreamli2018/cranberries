package com.cranberries.userapi.api;


import com.cranberries.userapi.vo.ResultVO;
import com.cranberries.userapi.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", contextId = "syncUserInfo", path = "/api-syncUserInfo")
public interface UserService {

    @PostMapping("/register")
    ResultVO<String> register(User user);

    @GetMapping("/getUserById")
    ResultVO<User> getUserById(@RequestParam(value = "id") int id);

    @PostMapping("/updateUser")
    void updateUser(User user);

    @GetMapping("/sendUserIdList")
    void sendUserIdList();
}
