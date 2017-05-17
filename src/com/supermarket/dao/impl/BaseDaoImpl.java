package com.supermarket.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.supermarket.dao.HibernateUtil;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.Product;
import com.supermarket.entities.User;

public class BaseDaoImpl<T> extends HibernateUtil{

	//查询管理员密码
	public String get(Integer id) {
		String hql="SELECT u.password FROM User u WHERE u.id=?";
		Query query=getSession().createQuery(hql).setInteger(0, id);
		return  (String) query.uniqueResult();
	}
	
	public User getId(String user){
		String hql="FROM User e WHERE e.user=?";
		return (User) getSession().createQuery(hql).setString(0, user).uniqueResult();
	}
	
	//用户名查询
	//验证信息
	public User validateLastName(String user){
		String hql="FROM User e WHERE e.user=?";
		Query query=getSession().createQuery(hql).setString(0, user);
		return (User) query.uniqueResult();
	}
	
	//登录查询
	public User get(String user,String password) {
		String hql="FROM User u WHERE u.user=? AND u.password=?";
		Query query=getSession().createQuery(hql).setString(0, user).setString(1, password);
		return (User) query.uniqueResult();
	}
	
	//分类展示商品
	public List<T> find(String hql,List<Object> param,int firstResult,int maxResults){
		Query query=getSession().createQuery(hql);
		//取出放在数组里的id号
		 if ((param != null) && (param.size() > 0)) {
		      for (int i = 0; i < param.size(); i++) {
		    	  query.setParameter(i, param.get(i));
		      }
		 }
		return query.setFirstResult(firstResult).setMaxResults(maxResults).list();
	}
	
	//修改订单状态
	public void getNum(Integer status,Integer id){
		String hql="UPDATE Order o SET o.status="+status+" WHERE o.id="+id; 
		getSession().createQuery(hql).executeUpdate();
	}
	
	//获取表总数
	public Long count(String hql){
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	//用户订单遍历
	public List<T> list(String hql,Integer id){
		return getSession().createQuery(hql).setInteger(0, id).list();
	}
	
	//遍历/更新
	public List<T> getAll(String hql){
		return getSession().createQuery(hql).list();
	}
	//分页
	public List<T> getAll(String hql, int firstResult,int maxResults) {
		Query query=getSession().createQuery(hql);
		return query.setFirstResult(firstResult).setMaxResults(maxResults).list();
	}
	
	//保存/修改
	public void save(T o){
		getSession().saveOrUpdate(o);
	}
	
	//删除
	public void delete(T o){
		getSession().delete(o);
	}
	
	public T get(String hql,Integer id){
		return (T) getSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}
	
	//回显页面
	public T get(Class<T> c,Integer id){
		return (T)getSession().get(c, id);
	} 
	
	//模糊查询
	public List<T> getCount(String hql){
		List<T> query= getSession().createQuery(hql).list();
		return query;
	}

}
