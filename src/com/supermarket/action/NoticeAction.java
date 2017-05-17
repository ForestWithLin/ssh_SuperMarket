package com.supermarket.action;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.Notice;
import com.supermarket.entities.PageBean;
import com.supermarket.service.impl.NoticeServiceImpl;
import com.supermarket.util.ResponseUtil;

public class NoticeAction extends ActionSupport implements RequestAware,ModelDriven<Notice>,Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	private Notice model;
	private NoticeServiceImpl noticeService;
	private String title;
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
	public void setTitle(String title) {
		this.title = title;
	}
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNoticeService(NoticeServiceImpl noticeService) {
		this.noticeService = noticeService;
	}
	
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(title==null){
			request.put("admin_notices",noticeService.getAll(pageBean));
		}else{
			request.put("admin_notices", noticeService.query(title));
		}
		return "not_all_query";
	}
	
	public String list(){
		request.put("manPage","notice_show.jsp");
		request.put("notices", noticeService.getAll());
		return "not_list";
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}
	
	public String edit(){
		return "not_edit";
	}
	public void prepareEdit(){
		model=noticeService.get(id);
	}
	
	public String show() throws Exception{
		this.model=noticeService.get(id);
		JSONObject result=new JSONObject();
		result.put("success",this.model);
		ResponseUtil.write(ServletActionContext.getResponse(),result);
		return null;
	}
	
	public String delete(){
		noticeService.delete(model);
		return "not_delete_save";
	}
	public void prepareDelete(){
		model=noticeService.get(id);
	}
	
	//添加保存信息
	public String inputSave(){
		model.setCreateDate(new Date());
		noticeService.save(model);
		return "not_delete_save";
	}
	public void prepareInputSave(){
		model=new Notice();
	}

	
	//修改保存信息
	public String editSave(){
		noticeService.save(model);
		return "not_delete_save";
	}
	public void prepareEditSave(){
		model=noticeService.get(id);
	}

	@Override
	public void prepare() throws Exception {}

	@Override
	public Notice getModel() {
		return model;
	}

}
