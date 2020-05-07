package com.ahf.shiro.service;

import java.util.Set;

public interface RoleService {
	public Set<String> queryRoleNameByUserName(String username);
}
