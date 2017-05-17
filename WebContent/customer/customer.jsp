<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/mainLogin.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<style type="text/css">
.jnDatainfo1 h3{
	border-bottom: 1px solid #EEEEEE;
	height: 24px;
	line-height: 24px;
	width: 164px;
	margin-bottom: 5px;
	margin-left: 20px;
}
.jnDatainfo1 h3 a{
	color: #AAAAAA;
	display: inline-block;
	line-height: 20px;
	padding: 0 10px;
}
.jnDatainfo1 h3 a:hover{
	color: #666666;
	text-decoration: none;
}
h2{
    margin-bottom: 5px;
}
#right{
    width: 80%;
    height: 560px;
    left: 200px;
	margin: 0 11px 0 0;
	overflow: hidden;
	position: absolute;
}
.ui-blue{
     border:medium none;  
     background:#666666;  
     height:25px;  
     color:#fff;  
     font-size:16px;  
     cursor:pointer;  
     font-weight:bold;  
     width: 50px; 
}
#search{
     position:absolute;
     right: 15%;
     margin-top: 20px;
}
#manage{
     position: absolute;
     margin-top: 55px;
     margin-left:20px;
     width: 95%;
     overflow: auto;
     height: 480px;
}
#right #edit{
    display:none;
    position: relative;
    border-radius: 10px;
    top: 30%;
    left: 25%;
    width:500px;
    border: 1px solid #C0C0C0;
    box-shadow: 0 0 2px #000;
}
</style>
<script type="text/javascript">
   $(function(){
	   $('#btn').click(function(){
		  alert('${customer.email}'); 
	   });
   })
</script>
</head>  
<body>  
            <!-- 标题层 -->
<div id="header">  
        <jsp:include page="../login/log_top.jsp"></jsp:include>
</div>  

           <!-- 内容层 -->
<div id="content">
    <div class="shopBar">
             <!-- 左侧导航栏 -->
	        <div id="jnDatalog">
	         <h2 title="个人信息管理">用户管理</h2>
		          <div class="jnDatainfo1">
			          <h3><a href="user_uri.action">个人信息修改</a></h3>
		          </div>
	         <h2 title="个人信息管理">订单管理</h2>
		          <div class="jnDatainfo1">
			          <h3><a href="or_uri.action">个人订单信息</a></h3>
		          </div>	
	         </div>
			          <jsp:include page="${manPage }"></jsp:include>
	 </div>
</div>
</body>  
</html>

