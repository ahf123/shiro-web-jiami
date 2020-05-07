package com.ahf.shiro.dao;

import org.apache.ibatis.annotations.Param;

import com.ahf.shiro.pojo.User;

public interface UserDao {
    public User queryUserByUserName(@Param("username")String username);
    public Integer insertUser(User user);
}
