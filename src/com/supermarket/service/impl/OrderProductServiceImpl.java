package com.supermarket.service.impl;

import java.util.List;

import com.supermarket.dao.impl.BaseDaoImpl;
import com.supermarket.entities.OrderProduct;

public class OrderProductServiceImpl {

	private BaseDaoImpl<OrderProduct> baseDao;
	public void setBaseDao(BaseDaoImpl<OrderProduct> baseDao) {
		this.baseDao = baseDao;
	}
	
	public void save(OrderProduct oProduct){
		baseDao.save(oProduct);
	}
	
	public List<OrderProduct> list(OrderProduct orderPro){
		String hql="FROM OrderProduct e WHERE e.orId.id=?";
		return baseDao.list(hql, orderPro.getOrId().getId());
	}
}
