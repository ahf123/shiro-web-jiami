package com.ahf.shiro.dao;

import java.util.Set;

public interface PermssionDao {
	public Set<String> queryPermssionNameByUserName(String username);
}
