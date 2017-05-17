<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="list">
	           <h5>全部商品</h5>
                 <hr>
	            <s:iterator value="#request.s_show">
					<ul style="float:left; display: inline;">
					     <li>
					         <a href="prod_show.action?id=${id }" ><img width=185 height=188 src="${proPic }"></a>
					         <span><a href="prod_show.action?id=${id }">${proName }</a></span>
					      </li>
					</ul>
				</s:iterator>
				<div id="doo">
				   <a href="prod_s_show.action?page=${pageBean.firstPage }"> &lt;&lt; </a>
				   <a href="prod_s_show.action?page=${pageBean.previousPage }">上一页</a>
				   <a href="prod_s_show.action?page=${pageBean.nextPage }">下一页</a>
				   <a href="prod_s_show.action?page=${pageBean.lastPage }"> &gt;&gt; </a>
				</div>
	        </div>
</body>
</html>