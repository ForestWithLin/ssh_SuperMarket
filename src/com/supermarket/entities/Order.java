package com.supermarket.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orderNum;
	private Date orderTime;
	private Float orderCost;
	private Integer status;
	private User userId;
	private Set<OrderProduct> orderProducts=new HashSet<OrderProduct>();
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Float getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(Float orderCost) {
		this.orderCost = orderCost;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNum=" + orderNum + ", orderTime="
				+ orderTime + ", orderCost=" + orderCost + ", status=" + status
				+ ", userId=" + userId + ", orderProducts=" + orderProducts
				+ "]";
	}
}
