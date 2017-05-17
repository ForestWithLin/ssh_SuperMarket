package com.supermarket.action;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.New;
import com.supermarket.entities.PageBean;
import com.supermarket.service.impl.NewsServiceImpl;
import com.supermarket.util.ResponseUtil;

public class NewsAction extends ActionSupport implements RequestAware,Preparable,ModelDriven<New>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,Object> request;
	private New model;
	private NewsServiceImpl newsServiceImpl;
	private Integer id;
	private String newName;
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
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNewsServiceImpl(NewsServiceImpl newsServiceImpl) {
		this.newsServiceImpl = newsServiceImpl;
	}
	//遍历new表
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(newName==null){
			request.put("admin_news",newsServiceImpl.getAll(pageBean));
		}else{
			request.put("admin_news",newsServiceImpl.query(newName));
		}
		return "news_all_query";
	}
	
	public String list(){
		request.put("manPage","news_show.jsp");
		request.put("news",newsServiceImpl.getAll());
		return "news_list";
	}
	
	public String delete(){
		if(newsServiceImpl.get(id)!=null){
			newsServiceImpl.delete(model);
		}
		return "news_delete_save";
	}
	public void prepareDelete(){
		model=newsServiceImpl.get(id);
	}
	
	//修改信息
	public String edit(){
		return "news_edit";
	}
	//回显
	public void prepareEdit(){
    	model=newsServiceImpl.get(id);
    }
	
	//新闻栏弹层显示
	public String show() throws Exception{
		this.model=newsServiceImpl.get(id);
		JSONObject result=new JSONObject();
		result.put("success",this.model);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	//添加保存信息
	public String inputSave(){
		model.setCreatTime(new Date());
		newsServiceImpl.save(model);
		return "news_delete_save";
	}
	public void prepareInputSave(){
		model=new New();
	}
	
	//保存信息
	public String editSave(){
		newsServiceImpl.save(model);
		return "news_delete_save";
	}
	public void prepareEditSave(){
		model=newsServiceImpl.get(id);
	}

	//用request获取页面数据
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}
	@Override
	public void prepare() throws Exception {}
	@Override
	public New getModel() {
		return model;
	}
	
}
