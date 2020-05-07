package com.ahf.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import com.ahf.shiro.pojo.User;
import com.ahf.shiro.service.PermssionService;
import com.ahf.shiro.service.RoleService;
import com.ahf.shiro.service.UserService;

import lombok.Setter;

//验证身份和权限
@Setter  
public class MyRealm extends AuthorizingRealm {
	
	//通过容器注入
	private UserService userService;
	private RoleService roleService;
	private PermssionService permssionService;
	
	/**
	 * 作用：查询权限信息，并返回即可，不用任何对比 何时触发： /user/query = roles["admin"] /user/insert=
	 * perms["user:insert"] <shiro:hasRole <shiro:hasPermission
	 * 查询方式：通过用户名查询，该用户的权限/角色/信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("在realm中查询权限");
		// 获取当前用户用户名
		String username = (String) principals.getPrimaryPrincipal();
		// 查询当前用户的所有权限信息
		// 从容器中获取查询用户信息的实例
		//RoleService roleService = ContextLoader.getCurrentWebApplicationContext().getBean(RoleService.class);
		//PermssionService permssionService = ContextLoader.getCurrentWebApplicationContext().getBean(PermssionService.class);
		// 查询当前用户的角色信息
		Set<String> roles = roleService.queryRoleNameByUserName(username);
		// 查询当前用户的权限信息
		Set<String> permssions = permssionService.queryPermssionNameByUserName(username);
		// 创建 SimpleAuthorizationInfo, 并设置其 roles 属性.和权限返回
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
		simpleAuthorizationInfo.setStringPermissions(permssions);
		return simpleAuthorizationInfo;
	}

	/**
	 * 作用：查询身份信息，并返回即可，不做任何对比 查询方式：通过用户名查询用户信息 何时触发：subject.login(token)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("在Realm中查询身份");
		// 获取用户登录时发送来的用户名
		String username = (String) token.getPrincipal();
		// 从容器中获取查询用户信息的实例
		//UserService userService = ContextLoader.getCurrentWebApplicationContext().getBean(UserService.class);
		// 查询用户信息
		User user = userService.queryUserByUserName(username);
		System.out.println(user);
		// 判断用户信息是否为null
		if (user == null) {
			return null;// 会抛出异常
		}
		// 将用户信息封装在AuthenticationInfo中
//		return new SimpleAuthenticationInfo(user.getUsername(), // 数据库中
//				user.getPassword(), // 数据库中的密码
//				this.getName());// realm的标识
        //在最后返回用户认证info时，添加一个属性，ByteSource.Util.bytes(user.getSalt())=盐
		//用于密码对比
		//登录认证身份，认证时realm会调用比对器比对密文
		return new SimpleAuthenticationInfo(
				user.getUsername(), 
				user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), 
				this.getName());
	}
}

