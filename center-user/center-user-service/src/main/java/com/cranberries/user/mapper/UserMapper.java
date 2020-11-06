package com.cranberries.user.mapper;

import com.cranberries.userapi.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer register(User user);

    //    @Select("select * from user where id = #{userId}")
    User getUserById(@Param(value = "userId") int userId);

    void updateUser(@Param("user") User user);

    List<Integer> queryIdList();
}
