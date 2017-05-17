package com.supermarket.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.ProductSmallType;
import com.supermarket.service.impl.ProductSmallTypeServiceImpl;
import com.supermarket.service.impl.ProductTypeServiceImpl;
import com.supermarket.util.ResponseUtil;

public class ProductSmallTypeAction extends ActionSupport implements RequestAware,ModelDriven<ProductSmallType>,Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,Object> request;
	private ProductSmallTypeServiceImpl proSmallTypeService;
	private ProductSmallType model;
	private Integer id;
	private Map<String,Object> session=ActionContext.getContext().getSession();
    private int page=1;//分页数
	private int prePage=10;//当前页的记录数
	private String proName;
	private ProductTypeServiceImpl productTypeServiceImpl;
	private int proType;
	public int getProType() {
		return proType;
	}
	public void setProType(int proType) {
		this.proType = proType;
	}
	public void setProductTypeServiceImpl(
			ProductTypeServiceImpl productTypeServiceImpl) {
		this.productTypeServiceImpl = productTypeServiceImpl;
	}
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
	public void setProSmallTypeService(
			ProductSmallTypeServiceImpl proSmallTypeService) {
		this.proSmallTypeService = proSmallTypeService;
	}
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(proName==null){
			request.put("admin_proSmallTypes",proSmallTypeService.getAll(pageBean));
		}else{
			request.put("admin_proSmallTypes", proSmallTypeService.query(proName));
		}
		return "promt_all_query";
	}
	
	//二级联动
	public String list() throws Exception{
		List<ProductSmallType> list=proSmallTypeService.find(proType);
		JsonConfig jsonConfig = new JsonConfig();//建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
		jsonConfig.setExcludes(new String[]{"products","productType","remarks"}); //将不要属性(关联的属性)加入在数组中
		JSONArray result=JSONArray.fromObject(list, jsonConfig);
		ResponseUtil.write(ServletActionContext.getResponse(),result);
		return null;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
           		this.request=arg0;
	}

	public String delete() throws Exception{
		JSONObject result=new JSONObject();
		try{
			proSmallTypeService.delete(model);
		}catch(Exception e){ //获取删除时外键异常
			System.out.println(e);
			result.put("success",Boolean.valueOf(true));
		}
		ResponseUtil.write(ServletActionContext.getResponse(),result);
		return null;
	}
	public void prepareDelete(){
		model=proSmallTypeService.get(id);
	}
	
	//添加信息
	public String input(){
		session.put("prodTypes",productTypeServiceImpl.getAll());
		return "promt_input";
	}
	
	//修改信息
	public String edit(){
		session.put("prodTypes",productTypeServiceImpl.getAll());
		return "promt_list_edit";
	}
	//回显
	public void prepareEdit(){
		if(id!=null){
			model=proSmallTypeService.get(id);
		}
    }
	
	//添加保存信息
	public String save() throws IOException{
		if(id==null){
			proSmallTypeService.save(model);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('添加成功');parent.location.reload();</script>");
			return null;
		}else{
			proSmallTypeService.save(model);
			return "promt_delete_save";
		}
	}
	public void prepareSave(){
		if(id==null){
			model=new ProductSmallType();
		}else{
			model=proSmallTypeService.get(id);
		}
	}
	
	@Override
	public void prepare() throws Exception {}

	@Override
	public ProductSmallType getModel() {
		return model;
	}
}
