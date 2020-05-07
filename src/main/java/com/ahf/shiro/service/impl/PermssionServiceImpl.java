package com.ahf.shiro.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahf.shiro.dao.PermssionDao;
import com.ahf.shiro.dao.RoleDao;
import com.ahf.shiro.dao.UserDao;
import com.ahf.shiro.pojo.User;
import com.ahf.shiro.service.PermssionService;
import com.ahf.shiro.service.RoleService;
import com.ahf.shiro.service.UserService;

@Service
public class PermssionServiceImpl implements PermssionService {

	@Autowired
	private PermssionDao permssionDao;
    
	
	@Override
	public Set<String> queryPermssionNameByUserName(String username) {

		return permssionDao.queryPermssionNameByUserName(username);
	}
}
