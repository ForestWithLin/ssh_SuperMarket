package com.supermarket.entities;

import java.util.HashSet;
import java.util.Set;

public class ProductType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proName;
	private String remarks;
	private Set<Product> products= new HashSet<Product>();
	private Set<ProductSmallType> productSmallTypes=new HashSet<ProductSmallType>();
	public Set<ProductSmallType> getProductSmallTypes() {
		return productSmallTypes;
	}
	public void setProductSmallTypes(Set<ProductSmallType> productSmallTypes) {
		this.productSmallTypes = productSmallTypes;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
