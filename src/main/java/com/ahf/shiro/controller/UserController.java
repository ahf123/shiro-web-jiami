package com.ahf.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahf.shiro.pojo.User;
import com.ahf.shiro.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/login")//登录页面
	public String login() {
		System.out.println("goto login pages");
		return "login";
	}

	@PostMapping("/login")//登录功能
	public String login(User user) {
		System.out.println(user.getUsername());
		System.out.println("login ");
        
		// 获取subject 调用login(注意：在web.xml文件中已经创建Securitymanager,并使用SecurityUtils封装)
		Subject subject = SecurityUtils.getSubject();
		// 创建登录令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());;
		// 验证登录，登录失败会抛出异常，则交给异常解析器处理
		
		//如果需要记住我的话，需要在token中设置
		token.setRememberMe(true);//shiro默认支持"记住我"，只要有此设置则自动运作
		
		subject.login(token);
		return "index";// 登录成功，跳转首页
	}
	//登录成功后，权限校验
	@GetMapping("/all") 
	public String queryAllUsers() {
		System.out.println("query all users");
		return "index";
	}
   
	@RequiresAuthentication
	@RequestMapping("/perms/error") //权限不足
	public String permsError() {
		System.out.println("权限不足");
		return "perm_error";
	}
	
	@GetMapping("/regist")
	public String regist() {
		System.out.println("goto regist pages");
		return "regist";
	}
	
	@RequestMapping("/regist")
	public String regist(User user) {
		userService.insertUser(user);
		return "redirect:/user/login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/user/login";
	}
	
}
