package com.cranberries.user.service;

import com.cranberries.user.model.User;
import com.cranberries.user.respnose.ResultVO;

public interface UserService {

    ResultVO<String> register(User user);

    ResultVO<User> getUserById(int userId);

    void updateUser(User user);
}
