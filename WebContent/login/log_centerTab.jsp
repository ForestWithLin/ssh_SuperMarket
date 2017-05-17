<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="jnBrandTab">
	          <h2 title="品牌活动">品牌活动</h2>
	          <ul>
	              <li><a title="111" href="#1">品</a></li>
	              <li><a title="111" href="#2">牌</a></li>
	              <li><a title="111" href="#3">活</a></li>
	              <li><a title="111" href="#4">动</a></li>
	          </ul>
	       </div>
	        <div id="jnBrandContent">
	              <div id="jnBrandList">
	                  <s:iterator value="#application.products">
	                  <ul>
	                       <li>
	                           <a href="prod_show.action?id=${id }" class="JS_live"><img alt="222" src="${proPic }"></a>
	                           <span><a href="prod_show.action?id=${id }">${proName }</a></span>
	                        </li>
	                  </ul>
	                  </s:iterator>
	              </div>
	        </div>
</body>
</html>