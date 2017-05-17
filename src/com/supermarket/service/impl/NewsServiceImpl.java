package com.supermarket.service.impl;

import java.util.List;
import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.New;
import com.supermarket.entities.PageBean;
public class NewsServiceImpl {

	private BaseDaoImpl<New> basDao;
	public void setBasDao(BaseDaoImpl<New> basDao) {
		this.basDao = basDao;
	}
	//获取表
	public List<New> getAll(){
		String hql="FROM New";
		return basDao.getAll(hql);
	}
	
	//分页
	public List<New> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM New");
		if(pageBean!=null){
			return basDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return null;
	}
	
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from New";
		return basDao.count(hql);
	}
	
	public List<New> query(String newName){
		StringBuffer hql=new StringBuffer("FROM New u where");
		hql.append(" u.newName like ");
		hql.append("'%"+newName+"%'");
		return basDao.getCount(hql.toString());
	}
	
	//保存信息
	public void save(New news){
		basDao.save(news);
	}
	
	//回显信息
	public New get(Integer id){
		return basDao.get(New.class, id);
	}
	
	public void delete(New news){
		//String hql="DELETE FROM New n WHERE n.id=?";
		basDao.delete(news);
	}
}
