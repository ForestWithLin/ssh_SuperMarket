package com.supermarket.entities;

import java.util.HashSet;
import java.util.Set;


public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proName;
	private Long proCount;
	private Long proPrice;
	private ProductType proType;
	private ProductSmallType proSmType;
	private String proPic;
	private String content;
	private Set<OrderProduct> orderProducts =new HashSet<OrderProduct>();
	public ProductSmallType getProSmType() {
		return proSmType;
	}
	public void setProSmType(ProductSmallType proSmType) {
		this.proSmType = proSmType;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public Long getProCount() {
		return proCount;
	}
	public void setProCount(Long proCount) {
		this.proCount = proCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Long getProPrice() {
		return proPrice;
	}
	public void setProPrice(Long proPrice) {
		this.proPrice = proPrice;
	}
	public ProductType getProType() {
		return proType;
	}
	public void setProType(ProductType proType) {
		this.proType = proType;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", proName=" + proName + ", proCount="
				+ proCount + ", proPrice=" + proPrice + ", proType=" + proType
				+ ", proSmType=" + proSmType + ", proPic=" + proPic
				+ ", content=" + content 
				+ "]";
	}
}
