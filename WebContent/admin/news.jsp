<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.min.js"></script>
<script>
$(function(){
	$('#tianjia').click(function(){
		$.layer({
			 type: 1,
	         title: ['添加信息', 'background:#9198c4;'],
	         area: ['500', '250'],//宽高
	         border: [0], //去掉默认边框
	         shade: [0.5 , '#000' , true], //遮罩
	         closeBtn: [0, true], //默认关闭按钮
			page: {
	            html: '<div class="div1"><form action="news_inputSave.action" method="post">标题：<input type="text" class="title" name="newName"/><br/>内容：<textarea name="content" style="resize:none;height:150px;width:300px;"></textarea><br/><input type="submit" value="保存" /></form></div> '
	        }
		}); 
	});
	 //每页显示数目
	  $('#selects').change(function(){
	    	 var selects=$('#selects').val();
	    	 if(selects==1){
	    		 window.location.href="news_all.action?prePage=10";
	    	 }
	    	 if(selects==2){
	    		 window.location.href="news_all.action?prePage=20";
	    	 }
	    	 if(selects==3){
	    		 window.location.href="news_all.action?prePage=30";
	    	 }
	    	 if(selects==4){
	    		 window.location.href="news_all.action?prePage=40";
	    	 }
	     });
	  
	  $('#refresh').click(function(){
	  		 window.location.reload(); //刷新
	  	  });
})

//判断下拉菜单是否跟当前页记录数相同
function f(){
	  var selects=$('#selects').val();
	  var str=${pageBeans.prePage };
	  if(str==10){
		  $('#selects').get(0).options[0].selected=true;
	  }
	  if(str==20){
		  $('#selects').get(0).options[1].selected=true;
	  }
	  if(str==30){
		  $('#selects').get(0).options[2].selected=true;
	  }
	  if(str==40){
		  $('#selects').get(0).options[3].selected=true;
	  }
}
window.onload=f;
</script>
</head>
<body>
<div class="tag">
 <form action="news_all.action" method="post" id="form1">
    <table >
      <tr>
          <td>标题：</td>
          <td>
               <input type="text" name="newName" class="search"/>
          </td>
          <td class="button"><a href="#" onclick="$('#form1').submit();">搜索</a></td>
          <td class="button"><a href="#" id="tianjia">添加</a></td>
      </tr>
    </table>
    </form>
</div>
<div class="tag2">
<div class="demo">
      <s:if test="#request.admin_news==null || #request.admin_news.size()==0">
                              没有新闻信息！
      </s:if>
      <s:else>
          <table border="1" cellpadding="10" cellspacing="0" class="bordered">
              <tr>
                 <th>编号</th>
                 <th>标题</th>
                 <th>内容</th>
                 <th>发布时间</th>
                 <th>修改</th>
                 <th>删除</th>
              </tr>
              <s:iterator value="#request.admin_news">
              <tr>
                  <td>${id }</td>
                  <td>${newName }</td>
                  <td>${content }</td>
                  <td>
                  <s:date name="creatTime" format="yyyy-MM-dd hh:mm:ss"/>
                  </td>
                  <td class="button">
                     <a href="news_edit.action?id=${id }" id="edit">Edit</a>
                  </td>
                  <td class="button">
                     <a href="news_delete.action?id=${id }">Delete</a>
                  </td>
              </tr>
              </s:iterator>
          </table>
          </s:else>
 </div>
 <div class="doo">
 <div class="do">
	   <select id="selects">
	     <option value="1">10</option>
	     <option value="2">20</option>
	     <option value="3">30</option>
	     <option value="4">40</option>
	   </select>
	   <a href="news_all.action?page=${pageBeans.firstPage }"> &lt;&lt; </a>
	   <a href="news_all.action?page=${pageBeans.previousPage }">上一页</a>
	   <a href="news_all.action?page=${pageBeans.nextPage }">下一页</a>
	   <a href="news_all.action?page=${pageBeans.lastPage }"> &gt;&gt; </a>
	   <a href="#" id="refresh">刷新</a>
    </div>
	   <div style="float: right;">
		         第${pageBeans.currentPage }页
		        每页共${pageBeans.prePage }条记录
		        总共${pageBeans.allPage }页
		        总共${pageBeans.allRecords }条记录
	   </div>
   </div>
</div>
</body>
</html>