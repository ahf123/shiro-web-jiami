package com.ahf.shiro.session;

import javax.xml.transform.Source;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

//session监听类 
public class MySessionListener extends SessionListenerAdapter{
    //当有session创建时，触发
	@Override
	public void onStart(Session session) {
		System.out.println(session.getId());
	}
    
	//当有session停止时，触发
	@Override
	public void onStop(Session session) {
		System.out.println(session.getTimeout());
	}
    
	//当有session过期时，触发
	//但不会主动触发，需要再次访问时，即又要使用session时才会发现session过期，并触发
	@Override
	public void onExpiration(Session session) {
		System.out.println(session.getTimeout());
	}

}
