package com.supermarket.entities;

public class PageBean {

	private int currentPage=1;//当前页
	private int previousPage=0;//上一页
	private int nextPage=2;//下一页
	private int firstPage=1;//首页
	private int lastPage;//尾页
	private int prePage;//每页记录数
	private int allRecords;//总记录数
	private int allPage;//总页数
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getAllRecords() {
		return allRecords;
	}
	public void setAllRecords(int allRecords) {
		this.allRecords = allRecords;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	
	//根据当前页和总记录数初始化PageBean的各个参数    totalSize为总记录数
	public PageBean init(PageBean pageBean,int totalSize,int prePage){
		pageBean.setAllRecords(totalSize);//设置总记录条数
		pageBean.setAllPage(totalSize % pageBean.getPrePage()==0 ?
				totalSize/pageBean.getPrePage():totalSize/pageBean.getPrePage()+1);//设置总页数
		pageBean.setFirstPage(1);//设置首页数
		pageBean.setLastPage(pageBean.getAllPage());//设置尾页数
		if(pageBean.getCurrentPage()==1){
			pageBean.setPreviousPage(1);//设置上一页数
		}else{
			pageBean.setPreviousPage(pageBean.getCurrentPage()-1);
		}
		if(pageBean.getCurrentPage()==pageBean.getAllPage()){
			pageBean.setNextPage(pageBean.getAllPage());//设置下一页数
		}else{
			pageBean.setNextPage(pageBean.getCurrentPage()+1);
		}
		return pageBean;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", previousPage="
				+ previousPage + ", nextPage=" + nextPage + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + ", prePage=" + prePage
				+ ", allRecords=" + allRecords + ", allPage=" + allPage + "]";
	}
}
