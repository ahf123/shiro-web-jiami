package com.ahf.shiro.service;

import com.ahf.shiro.pojo.User;

public interface UserService {
	 public User queryUserByUserName(String username);
	 public Integer insertUser(User user);
}
