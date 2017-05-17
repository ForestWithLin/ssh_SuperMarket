package com.supermarket.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.Order;
import com.supermarket.entities.OrderProduct;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.Product;
import com.supermarket.entities.User;
import com.supermarket.service.impl.OrderProductServiceImpl;
import com.supermarket.service.impl.OrderServiceImpl;
import com.supermarket.util.ResponseUtil;

public class OrderAction extends ActionSupport implements RequestAware,ModelDriven<Order>,Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderServiceImpl orderServiceImpl;
	private Map<String,Object> request;
	private Order model;
	private Integer productId;//购买的商品id号
	private String price;//购买的商品价格
	private Integer id;
	private String orderNum;
	private OrderProductServiceImpl orderProductServiceImpl;
	private Integer counts;//购买的商品总数
	private Integer status;
    private int page=1;//分页数
	private int prePage=10;//当前页的记录数
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public void setOrderProductServiceImpl(
			OrderProductServiceImpl orderProductServiceImpl) {
		this.orderProductServiceImpl = orderProductServiceImpl;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
		this.orderServiceImpl = orderServiceImpl;
	}
	
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(orderNum==null){
			request.put("admin_orders",orderServiceImpl.getAll(pageBean));
			request.put("orders",orderServiceImpl.getAll());
		}else{
			request.put("admin_orders", orderServiceImpl.query(orderNum));//查询订单
		}
		return "or_all_query";
	}
	
	//保存操作
	public String save() throws Exception{
		System.out.println(counts+price);
		//获取储存在SessionMap中的对象
		Map<String,Object> session=ActionContext.getContext().getSession();
		User customer=(User) session.get("customer");
		Product product=(Product) session.get("product");
		System.out.println("user "+customer.getId()+"\n "+product.getProName()+price);
		model.setUserId(customer);
		model.setOrderTime(new Date());
		
		Date orderNum=new Date();
		model.setOrderNum(orderNum.getTime()+"");//将日期time转为订单号
		model.setStatus(1);
		
		model.setOrderCost(Float.parseFloat(price));//订单金额
		
			OrderProduct orderProduct=new OrderProduct();
			orderProduct.setNum(counts);
			orderProduct.setProId(product);
			orderProduct.setOrId(model);
			Set orderProducts = new HashSet();
			orderProductServiceImpl.save(orderProduct);
			orderProducts.add(orderProduct);
		model.setOrderProducts(orderProducts);
		orderServiceImpl.save(model);
		System.out.println("222");
		JSONObject result=new JSONObject();
		result.put("success",Boolean.valueOf(true));
		ResponseUtil.write(ServletActionContext.getResponse(),result);
		return null;
	}
	public void prepareSave(){
		model=new Order();
	}

	//页面显示
	public String show(){
		return "success";
	}
	
	//跳转页面显示
	public String uri(){
		Map<String,Object> session=ActionContext.getContext().getSession();
		User customer=(User) session.get("customer");
		 if (this.model == null) {
		      this.model = new Order();
		    }
		 this.model.setUserId(customer);
		request.put("orders",orderServiceImpl.show(model));
		request.put("manPage","customer_order.jsp");
		return "success";
	}
	
	//修改订单状态
	public String num() throws Exception{
		orderServiceImpl.Num(status, id);
		JSONObject result = new JSONObject();
 	    result.put("success", Boolean.valueOf(true));
 	    ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
        this.request=arg0;
	}
	@Override
	public Order getModel() {
		return model;
	}
	@Override
	public void prepare() throws Exception {}
}
