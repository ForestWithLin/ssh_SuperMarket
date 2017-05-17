<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript" src="scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="scripts/mainLogin.js"></script>
<link type="text/css" rel="stylesheet" href="css/login.css" />
<style type="text/css">
.list{
	  position:absolute; 
	  left:200px;
	  width: 80%;
	  height:560px;
	  overflow: hidden;
}
.list #doo{
    position: absolute;
    bottom:10px;
    right: 10px;
}
.list #doo a{
	background-color: #E4E4E4;
	color: #000000;
	display: inline-block;
	height: 20px;
	line-height: 20px;
	padding: 0 10px;
}
.list li{
    position: relative;
    padding: 30px 0 0 10px;
}
.list span{
	background-color: #EFEFEF;
	color: #666666;
	display: block;
	font-size: 14px;
	line-height: 24px;
	overflow: hidden;
	position: absolute;
	text-align: center;
	width: 183px;
}
</style>
</head>  
<body>  
            <!-- 标题层 -->
<div id="header">  
        <jsp:include page="login/log_top.jsp"></jsp:include>
</div>  

           <!-- 内容层 -->
<div id="content">
    <div class="shopBar">
             <!-- 左侧导航栏 -->
	        <div id="jnDatalog">
	         <jsp:include page="login/log_left.jsp"></jsp:include>
	        </div>
	        <s:if test="#request.manPage==null || #request.manPage.size()==0">
	             <!-- 中间层广告-->
			    <div id="jnIamg">
			        <jsp:include page="login/log_centerImg.jsp"></jsp:include>
			    </div>
			    
			    <!-- 中间转换层-->
			    <div id="jnBrand">
			           <jsp:include page="login/log_centerTab.jsp"></jsp:include>
			    </div>
			     <!-- 右侧标题栏 -->
			    <div id="jnNotice">
				       <jsp:include page="login/log_right.jsp"></jsp:include>
				</div>
	        </s:if>
	        <s:else>
	           <jsp:include page="${manPage }"></jsp:include>
	        </s:else>
		    
		    
	</div>
</div>
</body>  
</html>

