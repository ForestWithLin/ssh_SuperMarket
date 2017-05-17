package com.supermarket.service.impl;

import java.util.List;

import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.Comment;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.User;

public class CommentServiceImpl {

	private BaseDaoImpl<Comment> baseDao;
	public void setBaseDao(BaseDaoImpl<Comment> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<Comment> getAll(){
		String hql="FROM Comment";
		return baseDao.getAll(hql);
	}
	
	//分页
	public List<Comment> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM Comment");
		if(pageBean!=null){
			return baseDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return null;
	}
	
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from Comment";
		return baseDao.count(hql);
	}

	public void delete(Comment comment){
		//String hql="DELETE FROM Comment n WHERE n.id = ?";
		baseDao.delete(comment);
	}
	
	public void save(Comment comment){
		baseDao.save(comment);
	}
	
	public List<Comment> query(String userName){
		StringBuffer hql=new StringBuffer("FROM Comment c WHERE");
		hql.append(" c.userName like ");
		hql.append("'%"+userName+"%'");
		return baseDao.getCount(hql.toString());
	}
	
	public Comment get(Integer id){
		return baseDao.get(Comment.class, id);
	}
}
