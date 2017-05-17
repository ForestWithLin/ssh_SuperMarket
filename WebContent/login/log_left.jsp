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
 <h2 title="商品分类">商品分类</h2>
	          <div class="jnDatainfo">
          	     <s:iterator value="#application.proTypes">
	              <h3>${proName  }</h3>
	              <ul>
          	              <s:iterator value="productSmallTypes">
       	                     <li><a href="prod_s_show.action?promT=${id }">${proName }</a></li>
          	              </s:iterator>
	              </ul>
	              <br class="clear" />
                 </s:iterator>
	          </div>
</body>
</html>