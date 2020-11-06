package com.cranberries.user.service;

import com.cranberries.userapi.vo.ResultVO;
import com.cranberries.userapi.vo.User;

public interface UserService {

    ResultVO<String> register(User user);

    ResultVO<User> getUserById(int userId);

    void updateUser(User user);

    void sendUserIdList();
}
