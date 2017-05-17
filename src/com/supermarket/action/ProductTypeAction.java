package com.supermarket.action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.ProductType;
import com.supermarket.service.impl.ProductTypeServiceImpl;
import com.supermarket.util.ResponseUtil;

public class ProductTypeAction extends ActionSupport implements RequestAware,ModelDriven<ProductType>,Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,Object> request;
	private ProductTypeServiceImpl proTypeService;
	private ProductType model;
	private Integer id;
    private int page=1;//分页数
	private int prePage=10;//当前页的记录数
	private String proName;
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
	public void setProName(String proName) {
		this.proName = proName;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProTypeService(ProductTypeServiceImpl proTypeService) {
		this.proTypeService = proTypeService;
	}
	
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(proName==null){
			request.put("admin_proTypes",proTypeService.getAll(pageBean));
		}else{
			request.put("admin_proTypes", proTypeService.query(proName));
		}
		return "prot_all_query";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
           		this.request=arg0;
	}

	public String edit(){
		return "prot_edit";
	}
	public void prepareEdit(){
		if(id!=null){
			model=proTypeService.get(id);
		}
	}
	
	public String delete() throws Exception{
		JSONObject result=new JSONObject();
		try{
			proTypeService.delete(model);
		}catch(Exception e){ //获取删除时外键异常
			System.out.println(e);
			result.put("success",Boolean.valueOf(true));
		}
		ResponseUtil.write(ServletActionContext.getResponse(),result);
		return null;
	}
	public void prepareDelete(){
		model=proTypeService.get(id);
	}
	
	//添加保存信息
	public String save(){
		proTypeService.save(model);
		return "prot_delete_save";
	}
	public void prepareSave(){
			model=new ProductType();
	}
	
	//修改保存信息
		public String editSave(){
			proTypeService.save(model);
			return "prot_delete_save";
		}
		public void prepareEditSave(){
			model=proTypeService.get(id);
		}
	
	@Override
	public void prepare() throws Exception {}

	@Override
	public ProductType getModel() {
		return model;
	}
}
