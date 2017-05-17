package com.supermarket.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.Product;

public class ProductServiceImpl {

	private BaseDaoImpl<Product> baseDao;
	public void setBaseDao(BaseDaoImpl<Product> baseDao) {
		this.baseDao = baseDao;
	}
	
	//左外连接多表查询前端分类显示商品
	public List<Product> getAll(){
		String hql="FROM Product p LEFT OUTER JOIN FETCH p.proType";
		return baseDao.getAll(hql);
	}
	
	//显示商品
	public List<Product> getAll(PageBean pageBean){
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		String hql="FROM Product";
		if(pageBean!=null){
			return baseDao.getAll(hql, firstResult,maxResult);
		}
		return null;
	}
	
	//大类
	/*public List<Product> find(String proType,Integer id,PageBean pageBean){
		List param = new LinkedList();
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM Product p");
		if(proType.equals("proT")){
			hql.append(" WHERE p.proType.id=?");//查询大类商品
			param.add(id);
		}
		if(proType.equals("promT")){
			hql.append(" WHERE p.proSmType.id=?");//查询小类商品
			param.add(id);
		}
		return baseDao.find(hql.toString(),param,firstResult,maxResult);
	}*/
	public List<Product> find(Product products,PageBean pageBean){
		List param = new LinkedList();
		long ltotalSize=getCount();
		int totalSize=(int) ltotalSize;
		//根据当前页和总记录数初始化PageBean
		pageBean=pageBean.init(pageBean, totalSize,pageBean.getPrePage());
		//获取当前页数据
		int maxResult=pageBean.getPrePage();//获取当前页总数
		int firstResult=(pageBean.getCurrentPage()-1)*maxResult;//获取当前页起始点
		StringBuffer hql=new StringBuffer("FROM Product p");
		if(products!=null){
			if(products.getProType()!=null){
				hql.append(" WHERE p.proType.id=?");//查询大类商品
				param.add(Integer.valueOf(products.getProType().getId()));
			}
			if(products.getProSmType()!=null){
				hql.append(" WHERE p.proSmType.id=?");//查询小类商品
				param.add(Integer.valueOf(products.getProSmType().getId()));
			}
		}
		if(pageBean!=null){
			return baseDao.find(hql.toString(),param,firstResult,maxResult);
		}
		return null;
	}
	
	//获取总记录数
	public Long getCount(){
		String hql="select count(*) from Product";
		return baseDao.count(hql);
	}
	
	public void delete(Product product){
		//String hql="DELETE FROM Product e WHERE e.id=?";
		baseDao.delete(product);
	}
	
	public void save(Product product){
		baseDao.save(product);
	}
	
	public Product get(Integer id){
		return baseDao.get(Product.class, id);
	}
	
	public List<Product> query(String proName){
		StringBuffer hql=new StringBuffer("FROM Product p");
		hql.append(" where p.proName like ");
		hql.append("'%"+proName+"%'");
		return baseDao.getCount(hql.toString());
	}
	
	public Product get(String proType, Integer id){
		StringBuffer hql=new StringBuffer("FROM Product p");
		if(proType.equals("proT")){
			hql.append(" WHERE p.proType.id=?");//查询大类商品
			System.out.println("222");
		}
		if(proType.equals("promT")){
			hql.append(" WHERE p.proSmType.id=?");//查询小类商品
			System.out.println("333");
		}
		return baseDao.get(hql.toString(), id);
	}
	
}
