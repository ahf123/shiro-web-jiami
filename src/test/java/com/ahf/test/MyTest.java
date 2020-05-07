package com.ahf.test;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

	@Test
	public void test01() {
		String password="123";//明文
		String salt=UUID.randomUUID().toString();//盐
		Integer num=10000;//迭代次数
		//推荐使用toBase64
		//使用Md5加密
		String pwd=new Md5Hash(password, salt, num).toBase64();
		String pwd1=new Md5Hash(password,salt,num).toString();
        System.out.println(pwd);
        System.out.println(pwd1);
         
        //使用sha加密
        String pwd2=new Sha256Hash(password, salt, num).toBase64();
        String pwd3=new Sha256Hash(password, salt, num).toString();
        
        System.out.println(pwd2);
        System.out.println(pwd3);
	}
	
	@Test
	public void test02() {
		Subject subject = SecurityUtils.getSubject();
		//获取session
		Session session = subject.getSession();
		//session超时时间，单位：毫秒；0马上过期，正数，则空闲对应毫秒后过期，负数则不会过期
		session.setTimeout(-1);
		//session存、取值
		session.setAttribute("name", "111");
		session.getAttribute("name");
		//获取sessionid
		session.getId();
		//销毁session
		session.stop();
	}
	
	
}
