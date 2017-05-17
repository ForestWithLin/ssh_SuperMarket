package com.supermarket.entities;

import java.util.Date;

public class Comment {

	private Integer id;
	private String title;
	private String content;
	private String userName;
	private String replyContent;
	private Date comTime;
	private Date replyTime;
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getComTime() {
		return comTime;
	}
	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
}
