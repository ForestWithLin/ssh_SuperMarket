package com.supermarket.action;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.Comment;
import com.supermarket.entities.PageBean;
import com.supermarket.service.impl.CommentServiceImpl;
import com.supermarket.util.ResponseUtil;

public class CommentAction extends ActionSupport implements RequestAware,ModelDriven<Comment>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> request;
	private CommentServiceImpl commentServiceImpl;
	private Comment model;
	private Integer id;
	private int page=1;//分页数
	private int prePage=10;//当前页的记录数
	private String userName;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCommentServiceImpl(CommentServiceImpl commentServiceImpl) {
		this.commentServiceImpl = commentServiceImpl;
	}
	
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(userName==null){
			request.put("admin_comments",commentServiceImpl.getAll(pageBean));
		}else{
			request.put("admin_comments", commentServiceImpl.query(userName));
		}
		return "comt_all_query";
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
           this.request=arg0;		
	}
	
	public String list(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(4);
		request.put("pageBean", pageBean);
		request.put("admin_comments",commentServiceImpl.getAll(pageBean));
		request.put("manPage","comment_show.jsp");
		return "comt_list";
	}
	
	//save
	public String save() throws Exception{
		System.out.println(id);
		JSONObject result = new JSONObject();
		if(id==null){
			model.setComTime(new Date());
			result.put("success", "1");
		}else{
			model.setReplyTime(new Date());
			result.put("success", "2");
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		commentServiceImpl.save(model);
		return null;
	}
	public void prepareSave(){
		if(id==null){
			model=new Comment();
		}else{
			model=commentServiceImpl.get(id);
		}
	}
	
	public String delete(){
		commentServiceImpl.delete(model);
		return "comt_delete";
	}
	public void prepareDelete(){
		model=commentServiceImpl.get(id);
	}
	
	@Override
	public void prepare() throws Exception {}

	@Override
	public Comment getModel() {
		return model;
	}

}
