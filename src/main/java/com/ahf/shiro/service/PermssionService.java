package com.ahf.shiro.service;

import java.util.Set;

public interface PermssionService {
	public Set<String> queryPermssionNameByUserName(String username);
}
