package com.supermarket.entities;

import java.util.HashSet;
import java.util.Set;

public class ProductSmallType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Set<Product> products=new HashSet<Product>();//一个小类可能有多个商品
	private ProductType productType;//一个大类包含着多个小类
	private String proName;
	private String remarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "ProductSmallType [id=" + id 
				+ ", proName=" + proName
				+ ", remarks=" + remarks + "]";
	}
}
