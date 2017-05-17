package com.supermarket.service.impl;

import java.util.List;
import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.Notice;
import com.supermarket.entities.PageBean;
public class NoticeServiceImpl {
	
	private BaseDaoImpl<Notice> baseDao;
	public void setBaseDao(BaseDaoImpl<Notice> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<Notice> getAll(){
		String hql="FROM Notice";
		return baseDao.getAll(hql);
	}
	
	//分页
	public List<Notice> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM Notice");
		if(pageBean!=null){
			return baseDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return null;
	}
		
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from Notice";
		return baseDao.count(hql);
	}
	
	public void save(Notice notice){
		baseDao.save(notice);
	}
	
	public void delete(Notice notice){
		//String hql="DELETE FROM Notice n WHERE n.id = ?";
		baseDao.delete(notice);
	}
	
	public List<Notice> query(String title){
//		String hql="FROM Notice n WHERE n.title like '%"+title+"%'";
		StringBuffer hql=new StringBuffer("FROM Notice n WHERE");
		hql.append(" n.title like ");
		hql.append("'%"+title+"%'");
		return baseDao.getCount(hql.toString());
	}
	
	public Notice get(Integer id){
		return baseDao.get(Notice.class, id);
	}
}
