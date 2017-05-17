package com.supermarket.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private  SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
}
