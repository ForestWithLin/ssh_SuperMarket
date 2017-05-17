<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function logout(){
	if(confirm('您确定要退出系统吗？')){
		window.location.href='user_logout.action';
	}
}
</script>
</head>
<body>
 <div class="contWidth">
          <a href="#" class="logo"><img alt="JJJJJ" src="images/01.png"></a>
          <div class="search">
              <input type="text" id="inputSearch" class="txtsearch" value="请输入商品名称"/>
          </div>
	      <div id="skin">
	        <s:if test="#session.customer!=null">
	             <div>
	                                                    尊敬的<a href="${pageContext.request.contextPath }/or_show.action">${customer.user }</a>顾客
	                                                    <a href="javascript:logout()">注销</a>
	                                                     <a href="comt_list.action" style="display: inline;">留言</a>
	             </div>
	        </s:if>
	        <s:else>
	            <jsp:include page="index.jsp"></jsp:include>
	        </s:else>
	      </div>
	      <!-- 导航栏 -->
	      <div class="mainNav" id="nav">
	          <s:iterator value="#application.proTypes">
            	<ul class="nav">
            		<li>
            			<a href="prod_s_show.action?proT=${id }">${proName }</a>
            		</li>
            	</ul>
	          </s:iterator>
            	<div  style="display: inline;float: right;padding-right:30px;z-index: 100;">
            			<a href="${pageContext.request.contextPath }/login.jsp" style="color: #fff;font-weight: bold;">首页</a>
            	</div>
	      </div>
     </div>
</body>
</html>