package com.vincent.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		//openSession() 与 getCurrentSession() 的区别是什么？
		//为什么使用openSession() 可以，使用getCurrentSession() 出错.
		return this.sessionFactory.getCurrentSession();
	}
}
