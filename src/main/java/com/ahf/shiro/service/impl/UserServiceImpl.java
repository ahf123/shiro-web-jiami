package com.ahf.shiro.service.impl;

import java.util.UUID;


import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ahf.shiro.contstant.Myconstant;
import com.ahf.shiro.dao.UserDao;
import com.ahf.shiro.pojo.User;
import com.ahf.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public User queryUserByUserName(String username) {
		User user = userDao.queryUserByUserName(username);
		return user;
	}

	@Override
	public Integer insertUser(User user) {
		//加密
		String salt=UUID.randomUUID().toString();
		String password = new Sha256Hash(user.getPassword(), salt, Myconstant.INTERCOUNT).toBase64();
        //设置密文
		user.setPassword(password);
		//设置盐
		user.setSalt(salt);
		return userDao.insertUser(user);
	}
}
