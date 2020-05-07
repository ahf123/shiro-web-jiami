package com.ahf.shiro.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface RoleDao {
	//绑定参数
   public Set<String> queryRoleNameByUserName(@Param("username")String username);
}
