package com.ahf.shiro.exception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class MyException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(ex.getClass());
		ex.printStackTrace();
		if(ex instanceof IncorrectCredentialsException || ex instanceof UnknownAccountException){
            //跳转登录页面，重新登录
			modelAndView.setViewName("redirect:/user/login");
			
//			try {
//				response.getWriter().print(JSON.toJSON(new Error("sss")));
//				response.getWriter().flush();
//				response.getWriter().close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			modelAndView=null;
			
			 
        }else if(ex instanceof UnauthorizedException){// 角色不足  权限不足
            //跳转权限不足的页面
        	modelAndView.setViewName("redirect:/user/perms/error");
        }else if(ex instanceof UnauthenticatedException){//没有登录 没有合法身份
            //跳转登录页面，重新登录
        	modelAndView.setViewName("redirect:/user/login");
        }
		
		return modelAndView;
	}
}
