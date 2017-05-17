package com.supermarket.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.Product;
import com.supermarket.entities.ProductSmallType;

public class ProductSmallTypeServiceImpl {

	private BaseDaoImpl<ProductSmallType> baseDao;
	public void setBaseDao(BaseDaoImpl<ProductSmallType> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<ProductSmallType> getAll(){
		String hql="FROM ProductSmallType";
		return baseDao.getAll(hql);
	}
	
	public void save(ProductSmallType prot){
		baseDao.save(prot);
	}
	//分页
	public List<ProductSmallType> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM ProductSmallType");
		if(pageBean!=null){
			return baseDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return null;
	}
	
	//分类分页
	public List<ProductSmallType> find(Integer proType,PageBean pageBean){
		List param = new LinkedList();//创建数组，把遍历关联的商品信息放进去
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM ProductSmallType");
		if(pageBean!=null){
			return baseDao.getAll(hql.toString(), firstResult,maxResult);
		}
		return baseDao.find(hql.toString(),param,firstResult,maxResult);
	}
	
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from ProductSmallType";
		return baseDao.count(hql);
	}

	
	public void delete(ProductSmallType ProductSmallType){
		//String hql="DELETE FROM ProductSmallType n WHERE n.id = ?";
		baseDao.delete(ProductSmallType);
	}
	
	public List<ProductSmallType> query(String proName){
		StringBuffer hql=new StringBuffer("FROM ProductSmallType n WHERE");
		hql.append(" n.proName like ");
		hql.append("'%"+proName+"%'");
		return baseDao.getCount(hql.toString());
	}
	
	//二级联动
	public List<ProductSmallType> find(Integer proType){
		String hql="FROM ProductSmallType e WHERE e.productType.id=?";
		return baseDao.list(hql, proType);
	}
	
	public ProductSmallType get(Integer id){
		return baseDao.get(ProductSmallType.class, id);
	}
}
