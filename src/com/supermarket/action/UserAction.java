package com.supermarket.action;


import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.supermarket.entities.PageBean;
import com.supermarket.entities.User;
import com.supermarket.service.impl.UserServiceImpl;
import com.supermarket.util.ResponseUtil;

public class UserAction extends ActionSupport implements RequestAware,ModelDriven<User>,Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Map<String,Object> request;
    private String user;
    private User model;
    private String password;
    private Integer id;
    private String email;
    private String address;
    private Map<String,Object> session=ActionContext.getContext().getSession();
    private UserServiceImpl userServiceImpl;
    private int page=1;//分页数
	private int prePage=10;//当前页的记录数
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	//点击按钮设置id
    public void setId(Integer id) {
		this.id = id;
	}
    public void setUserServiceImpl(
			UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
    //获取用户名
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	//获取密码
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//遍历用户表
	public String all(){
		PageBean pageBean=new PageBean();
		//指定当前页
		pageBean.setCurrentPage(page);
		pageBean.setPrePage(prePage);
		request.put("pageBeans", pageBean);
		if(user==null){
			request.put("admin_users",userServiceImpl.getAll(pageBean));
		}else{
			request.put("admin_users",userServiceImpl.getCount(user));//模糊查询用户名
		}
    	return "user_all_query";
    }
	
	//登录验证
    public String regis() throws Exception{
    	JSONObject result = new JSONObject();
    	if(user.equals("root") && this.password.equals(userServiceImpl.getPassword(1))){
    		session.put("user",model);
    		result.put("success", "1");
    	}
    	else if(userServiceImpl.get(user,password)){
    		    session.put("customer", model);
    			result.put("success", "1");
    		}else{
    			result.put("success", "2");
    		}
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
	    return null;
    }
    public void prepareRegis(){
        model=new User();
        //根据登陆账号获取这一条记录
    	if(id==null){
    		model=userServiceImpl.getId(user);
    	}
    }
    
    public String root(){
    	return "user_root";
    }
    
    //退出系统清空缓存
    public String logout()
    	    throws Exception
    	  {
    	    session.clear();
    	    return "user_logout";
    	  }
    
    //点击按钮删除信息
    public String delete() throws Exception{
    	JSONObject result = new JSONObject();
    	try{
    		userServiceImpl.delete(model);	
    	}catch(Exception e){  //获取删除时外键异常
    		result.put("success", Boolean.valueOf(true));
    	}
 	    ResponseUtil.write(ServletActionContext.getResponse(), result);
 	    return null;
    }
    public void prepareDelete(){
    	model=userServiceImpl.get(id);
    }
    
	//用request获取页面数据
    @Override
    public void setRequest(Map<String, Object> arg0) {
    	this.request=arg0;
    }
    
    public String edit(){
    	
    	return "user_edit";
    }
    //页面回显
    public void prepareEdit(){
    	if(id!=null){
    		model=userServiceImpl.get(id);
    	}
    }
    
    //主系统修改密码验证
    public String modify()
    	    throws Exception
    	  {
    	    System.out.println(id+password+address+email);
    	    model=userServiceImpl.get(id);
    	    model.setPassword(password);
    	    model.setEmail(email);
    	    model.setAddress(address);
    	    userServiceImpl.save(model);
    	    JSONObject result = new JSONObject();
    	    result.put("success", Boolean.valueOf(true));
    	    ResponseUtil.write(ServletActionContext.getResponse(), result);
    	    return null;
    	  }
    
    //注册验证用户名
    public String validateUser() throws Exception{
    	JSONObject date = new JSONObject();
    	if(userServiceImpl.validateLastName(user)){
    		date.put("success", "1");
    	}else{
    		date.put("success", "0");
    	}
    	ResponseUtil.write(ServletActionContext.getResponse(), date);
    	return null;
    }
    
    //注册验证
    public String valiregis() throws Exception{
    	model.setCreateTime(new Date());
		userServiceImpl.save(model);
		JSONObject result = new JSONObject();
		result.put("success", Boolean.valueOf(true));
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
    public void prepareValiregis(){
    	model=new User();
    }
    
    //保存信息如果是修改信息则不用创建时间
    public String save(){
    		if(id==null){
    			model.setCreateTime(new Date());
    		}
    		userServiceImpl.save(model);
    	return "user_delete_save";
	}
	public void prepareSave(){
		if(id==null){
			model=new User();
		}else{
			model=userServiceImpl.get(id);
		}
	}
	
	public String uri(){
		request.put("manPage","customer_edit.jsp");
		return "success";
	}
	@Override
	public void prepare() throws Exception {}
	@Override
	public User getModel() {
		return model;
	}
}
