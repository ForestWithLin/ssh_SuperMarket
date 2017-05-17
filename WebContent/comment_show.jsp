<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="layer/layer.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#submit').click(function(){
		 var nickName=$("#nickName").val();
		 var content=$("#content").val();
		 var title=$("#title").val();
		 nickName=$.trim(nickName);
		 content=$.trim(content);
		 title=$.trim(title);
		 if(title=="" || nickName==""){
     		 alert("不能有空值");
     		 return false;
		 } else{
		        url='comt_save.action';
		 		args=$('#form2').serialize();
		 		$.post(url,args,function(result){
		 			var result=eval('('+result+')');
		 			if(result.success=="1"){
		 				 $('#nickName').val("");
		 	     		 $('#content').val("");
		 	     		 $('#title').val("");
		 				alert("留言成功");
		 		    	location.reload(); // 刷新本页
		 			}else{
		 		    	alert("留言失败");
		 			}
		 		});
		       }
	   });
	});
</script>
</head>
<body>
<div style=" position:absolute; left:200px; width: 80%; height:600px; overflow: hidden;">
    <h5>留言信息</h5>
    <hr>
       <div style="position: relative;margin: 0 0 0 0;">
	　　　　<s:iterator value="#request.admin_comments">
	        <ul>
				<li>
					<dl>
						<dt>标题：${title}</dt>
						<dt>内容：${content}</dt>
						<dd class="author">
							网友：${userName }&nbsp;&nbsp;&nbsp;&nbsp;
							回复日期：<s:date name="comTime" format="yyyy-MM-dd hh:mm:ss"/>
						</dd>
						<dd>官方回复：${replyContent }&nbsp;&nbsp;&nbsp;&nbsp;
						   回复日期：<s:date name="replyTime" format="yyyy-MM-dd hh:mm:ss"/>
						</dd>
					</dl>
			    </li>
		     </ul>
			 <br />
	     </s:iterator>
	    </div>
			   <div>
				<form  method="post" id="form2">
					<table>
						<tr>
							<td>标题：</td>
							<td><input type="text" id="title" name="title" />
							</td>
						</tr>
						<tr>
							<td>留言内容：</td>
							<td><textarea rows="3" cols="5" id="content" name="content" ></textarea> 
							</td>
						</tr>
						<tr>
							<td>昵称：</td>
							<td><input type="text"  id="nickName"  name="userName" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
							<input type="button" id="submit" value="提交留言" />
							</td>
						</tr>
					</table>
				</form>
			</div>
 
		<div style="position: absolute; bottom:10px; right: 10px;">
		   <a href="comt_list.action?page=${pageBean.firstPage }"> &lt;&lt; </a>
		   <a href="comt_list.action?page=${pageBean.previousPage }">上一页</a>
		   <a href="comt_list.action?page=${pageBean.nextPage }">下一页</a>
		   <a href="comt_list.action?page=${pageBean.lastPage }"> &gt;&gt; </a>
		</div>
</div>
</body>
</html>