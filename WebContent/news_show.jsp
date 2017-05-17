<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="layer/layer.min.js"></script>
<script type="text/javascript">
function newclick(id){  
	 var url="news_show.action";
	 var args={"id":id};
		 $.post(url,args,function(result){
			 var result=eval('('+result+')');
			 var newName=result.success.newName;
			 $('#content_show').text(result.success.content);
			 var date=new Date(result.success.creatTime.time);
		   		date=date.getFullYear()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds()+"";
			 $('#time_show').text(date);
			   $.layer({
			       type: 1,
			       title: [newName, 'background:#9198c4;'],
			       area: ['400', '250'],//宽高
			       border: [5, 0.5, '#000'], //去掉默认边框
			       shade: [0], //遮罩
			       closeBtn: [0, true], //默认关闭按钮
			       //shift: 'top', //从顶动画弹出
			       page: {
			       	dom:'#show'
			       }
			   });
		  })	
   }
</script>
</head>
<body>
<div class="list">
<s:iterator value="#application.news">
         <ul>
            <li>
	            <a href="javascript:newclick(${id});">
	            <em>${newName }</em>
	            </a>
            </li>
         </ul>
</s:iterator>
	<div id="show" style="display: none;width: 400px; height: 220px;">
	     <p id="content_show"></p>
	     
	 <div style="position: absolute; bottom: 0; width: 100%;height: 25px;background-color: #666666; color: #FFFFFF; text-align: center; line-height: 25px;">
	     发布时间：<p id="time_show" style="display:inline;"></p>
	 </div>
	 </div>
 </div>
</body>
</html>