package com.supermarket.service.impl;

import java.util.List;

import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.ProductType;
import com.supermarket.entities.User;

public class ProductTypeServiceImpl {

	private BaseDaoImpl<ProductType> baseDao;
	public void setBaseDao(BaseDaoImpl<ProductType> baseDao) {
		this.baseDao = baseDao;
	}
	
	public List<ProductType> getAll(){
		String hql="FROM ProductType";
		return baseDao.getAll(hql);
	}
	
	public void save(ProductType prot){
		baseDao.save(prot);
	}
	//分页
		public List<ProductType> getAll(PageBean pageBean){
			long ltotalSize=getCount();
			int totalSize=(int) ltotalSize;
			//根据当前页和总记录数初始化PageBean
			pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
			//获取当前页数据
			int maxResult=pageBean.getPrePage();//获取当前页总数
			int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
			StringBuffer hql=new StringBuffer("FROM ProductType");
			if(pageBean!=null){
				return baseDao.getAll(hql.toString(), firstResult,maxResult);
			}
			return null;
		}
		
		//获取总记录数
		public Long getCount(){
			String hql="select count(*) from ProductType";
			return baseDao.count(hql);
		}

	
	public void delete(ProductType productType){
		//String hql="DELETE FROM ProductType n WHERE n.id = ?";
		baseDao.delete(productType);
	}
	
	public List<ProductType> query(String proName){
		StringBuffer hql=new StringBuffer("FROM ProductType n WHERE");
		hql.append(" n.proName like ");
		hql.append("'%"+proName+"%'");
		return baseDao.getCount(hql.toString());
	}
	
	public ProductType get(Integer id){
		return baseDao.get(ProductType.class, id);
	}
}
