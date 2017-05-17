package com.supermarket.service.impl;


import java.util.List;

import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.User;

public class UserServiceImpl{
	
     private BaseDaoImpl<User> baseDao;
	public void setBaseDao(BaseDaoImpl<User> baseDao) {
		this.baseDao = baseDao;
	}
     //遍历User表
	public List<User> getAll() {
		String hql="FROM User";
		return baseDao.getAll(hql);
	}
	
	//分页
	public List<User> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM User");
		if(pageBean!=null){
			return baseDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return null;
	}
	
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from User";
		return baseDao.count(hql);
	}

	//获取User表root的密码
	public String getPassword(Integer id){
		return baseDao.get(id);
	}
	
	//根据账号获取记录
	public User getId(String user){
		return baseDao.getId(user);
	}
	
	public boolean validateLastName(String user){
		//如果值为空（也就是说数据库里没有匹配的用户名）返回true
		return baseDao.validateLastName(user)==null;
	}
	
	//验证用户是否已有（不为空值表示已有）
	public boolean get(String user,String password) {
         return baseDao.get(user, password)!=null;
	}

	//删除信息
	public void delete(User user){
		//String hql="DELETE FROM User e WHERE e.id=?";
		baseDao.delete(user);
	}
	
	//保存信息
	public void save(User user){
		baseDao.save(user);
	}

	//页面回显
	public User get(Integer id){
		return baseDao.get(User.class,id);
	}
	
	//模糊查询
	public List<User> getCount(String user){
		StringBuffer hql=new StringBuffer("FROM User u where");
		hql.append(" u.user like ");
		hql.append("'%"+user+"%'");
		return baseDao.getCount(hql.toString());
	}
}
