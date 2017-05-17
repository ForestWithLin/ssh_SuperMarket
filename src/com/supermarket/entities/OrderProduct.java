package com.supermarket.entities;

public class OrderProduct implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer num;
	private Order orId;
	private Product proId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer counts) {
		this.num = counts;
	}
	public Order getOrId() {
		return orId;
	}
	public void setOrId(Order orId) {
		this.orId = orId;
	}
	public Product getProId() {
		return proId;
	}
	public void setProId(Product proId) {
		this.proId = proId;
	}
}
