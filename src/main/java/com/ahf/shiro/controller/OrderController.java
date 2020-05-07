package com.ahf.shiro.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//shiro注解开发
@Controller
@RequestMapping("/order")
public class OrderController {
    
	@RequiresUser  //要求登录或者记住我
	@RequestMapping("/query")
	public String queryOreder() {
		System.out.println("query order");
		return "index";
	}
	
	@RequiresAuthentication //要求登录
	//要求有admin或者guest的角色
	@RequiresRoles(value = {"admin","guest"},logical = Logical.OR)
	@RequestMapping("/save")
	public String saveOrder() {
		System.out.println("save order");
		return "index";
	}
	
	@RequiresAuthentication  //要求登录
	//要求 对user有delet和query的权限
    @RequiresPermissions(value={"user:delete","user:query"},logical = Logical.AND)
    @RequestMapping("/delete")
    public String deleteOrder(){
        System.out.println("delete Order");
        return "index";
    }

    @RequiresAuthentication //要求登录
    @RequestMapping("/update")
    public String updateOrder(){
        System.out.println("update Order");
        return "index";
    }
}
