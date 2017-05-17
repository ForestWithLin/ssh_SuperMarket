package com.supermarket.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.Product;
import com.supermarket.service.impl.ProductServiceImpl;
import com.supermarket.service.impl.ProductSmallTypeServiceImpl;
import com.supermarket.service.impl.ProductTypeServiceImpl;
import com.supermarket.util.ResponseUtil;

public class ProductAction extends ActionSupport implements RequestAware,ModelDriven<Product>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> request;
	private ProductServiceImpl productService;
	private Product model;
	private String proName;
	private Integer id;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private int page=1;//分页数
	private int prePage=10;//当前页的记录数
	private Integer proT;//商品大类型id号
	private Integer promT;//商品小类的id号
	private File propic;//文件图片对象
	private String propicFileName;//文件图片名称
	private ProductTypeServiceImpl productTypeServiceImpl;
	private ProductSmallTypeServiceImpl smallTypeServiceImpl;
	public Integer getPromT() {
		return promT;
	}
	public void setPromT(Integer promT) {
		this.promT = promT;
	}
	public void setSmallTypeServiceImpl(
			ProductSmallTypeServiceImpl smallTypeServiceImpl) {
		this.smallTypeServiceImpl = smallTypeServiceImpl;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getPropicFileName() {
		return propicFileName;
	}
	public void setPropicFileName(String propicFileName) {
		this.propicFileName = propicFileName;
	}
	public File getPropic() {
		return propic;
	}
	public void setPropic(File propic) {
		this.propic = propic;
	}
	public Integer getProT() {
		return proT;
	}
	public void setProT(Integer proT) {
		this.proT = proT;
	}
	public void setProductTypeServiceImpl(
			ProductTypeServiceImpl productTypeServiceImpl) {
		this.productTypeServiceImpl = productTypeServiceImpl;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	//分页信息
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(proName==null){
			request.put("admin_products",productService.getAll(pageBean));
		}else{
			request.put("admin_products",productService.query(proName));
		}
		return "prod_all_query";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
           		this.request=arg0;
	}

	
	public String delete() throws Exception{
		JSONObject result = new JSONObject();
		try{
			productService.delete(model);
		}catch(Exception e){
			result.put("success", Boolean.valueOf(true));
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	public void prepareDelete(){
		model=productService.get(id);
	}
	
	//添加信息
	public String input(){
		session.put("prodTypes",productTypeServiceImpl.getAll());
		session.put("proSmallTypes",smallTypeServiceImpl.getAll());
		return "prod_input";
	}
	
	//修改信息
	public String edit(){
		session.put("proSmallTypes",smallTypeServiceImpl.getAll());
		session.put("prodTypes",productTypeServiceImpl.getAll());
		return "prod_list_edit";
	}
	//回显
	public void prepareEdit(){
		if(id!=null){
			model=productService.get(id);
		}
    }
	//后台显示商品
	public String show(){
		this.model=this.productService.get(id);
		Map<String,Object> session=ActionContext.getContext().getSession();
        session.put("product",this.model);
		return "prod_show";
	}
	/*//前端分类显示商品
	public String s_show(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(10);
		request.put("pageBean", pageBean);
		if(proT!=null){
			request.put("s_show",productService.find("proT",proT,pageBean));
		}
		if(promT!=null){
			request.put("s_show",productService.find("promT",promT,pageBean));
		}
		request.put("manPage","product_show.jsp");
		return "success";
	}*/
	public String s_show(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(10);
		request.put("pageBean", pageBean);
		System.out.println(model);
		request.put("s_show",productService.find(model,pageBean));
		request.put("manPage","product_show.jsp");
		return "success";
	}
	public void prepareS_show(){
		if(proT!=null){
			model=productService.get("proT",proT);
			System.out.println("prot"+model);
		}
		if(promT!=null){
			model=productService.get("promT",promT);
			System.out.println("promt");
		}
		
	}
	
	//保存信息
	public String save() throws Exception{
		System.out.println(id+":"+propic);
		if(propic!=null){
			//日期转换
			Date date=new Date();
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
			//设置上传图片名称
			String imgName=now;
			//获取存储图片的路径：放在服务器上
			ServletContext servletContext=ServletActionContext.getServletContext();
			String realPath=servletContext.getRealPath("/images/product");
			//设置图片文件夹
			String imgFile=imgName+".jpg";
			//根据路径和图片文件名创建新的文件
			File saveFile=new File(realPath, imgFile);
			//把上传的文件(proPic)拷贝到保存的路径(saveFile)下
			FileUtils.copyFile(this.propic, saveFile);
			if(id==null){
				this.model.setProPic("images/product/"+imgFile);
				productService.save(model);
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('保存成功');parent.location.reload();</script>");
				System.out.println(model);
				return null;
			}else{
				this.model.setProPic("images/product/"+imgFile);
				return "prod_delete_save";
			}
		}
		productService.save(model);
		System.out.println(model);
		return "prod_delete_save";
	}
	public void prepareSave(){
		if(id!=null){
			model=productService.get(id);
		}else{
			model=new Product();
		}
	}
	
	@Override
	public void prepare() throws Exception {}
	@Override
	public Product getModel() {
		return model;
	}
}
