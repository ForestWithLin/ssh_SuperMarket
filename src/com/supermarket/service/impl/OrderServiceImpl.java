package com.supermarket.service.impl;

import java.util.List;
import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.Order;
import com.supermarket.entities.PageBean;

public class OrderServiceImpl {

	private BaseDaoImpl<Order> baseDao;
	public void setBaseDao(BaseDaoImpl<Order> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<Order> getAll(){
		String hql="FROM Order";
		return baseDao.getAll(hql);
	}
	
	//分页
	public List<Order> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM Order");
		if(pageBean!=null){
			return baseDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return null;
	}
	
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from Order";
		return baseDao.count(hql);
	}
	
	public void save(Order order){
		baseDao.save(order);
	}
	
	public void Num(Integer status,Integer id){
		baseDao.getNum(status, id);
	}
	
	public List<Order> query(String orNo){
		StringBuffer hql=new StringBuffer("FROM Order o WHERE");
		hql.append(" o.orderNum like ");
		hql.append("'%"+orNo+"%'");
		return baseDao.getCount(hql.toString());
	}
	
	//获取顾客购买的商品
	public List<Order> show(Order order){
		String hql="FROM Order o WHERE  o.userId.id=?";
		return baseDao.list(hql, order.getUserId().getId());
	}
}
