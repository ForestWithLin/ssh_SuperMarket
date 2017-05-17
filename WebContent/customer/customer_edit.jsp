<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#an').click(function(){
		var s1=document.getElementById('passw1').value;
		var s2=document.getElementById('passw2').value;
		var s3=document.getElementById('email').value;
		var s4=document.getElementById('address').value;
	    var reg3=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;//邮箱正则表达式
	    var reg4=/^(\w| [\u4e00-\u9fa5] )+$/;//地址正则表达式
	    if(s1!=s2||s1==null){
	    	alert("密码不一致");
	    	s1="";
	    	s2="";
	    }else{
	    	if(reg3.test(s3)==false||s3==null)
	    	{alert('请输入合邮箱');
	    	s3="";
	    	}
	    	else if(reg4.test(s4)==false||s4==null)
	    	{alert('请输入合法地址');
	    	s4="";
	    	}else{
	    		url="user_modify.action";
	 			args={'id':'${customer.id}','password':s1,'email':s3,'address':s4};
	 			$.post(url,args,function(result){
	 				var result=eval('('+result+')');
	 				if(result.success){
	 					alert("修改成功,下次登录见效");
	 		    		location.reload(); // 刷新本页
	 				}
	 			});
	    	}
	    }
		   
	});
})

   function edit(){
	   var demo=document.getElementById('edit');
	   var btn=document.getElementById('tt');
	   if(demo.style.display="none"){
     	   demo.style.display="block";
	   }
   }
</script>
</head>
<body>
<div id="right">
     <h5>个人管理</h5>
     <hr>
    <div id="right_manage">
		 <div id="manage">
			<table class="bordered">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>Email</th>
					<th>地址</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>${customer.id}</td>
					<td>${customer.user}</td>
					<td>${customer.email}</td>
					<td>${customer.address}</td>
					<td><a href="javascript:void(0)" onclick="edit()" id="tt">修改</a></td>
				</tr>
			</table>
		</div>
     </div>
	     <div id="edit">
	         <table style="width: 100%;text-align: center;">
	             <tr>
	                 <td colspan="2"><h4 style="display: inline;">修改信息</h4></td>
	             </tr>
	             <tr>
	                 <td>密码:</td>
	                 <td width="80%"><input type="text" name="password" id="passw1"/></td>
	             </tr>
	             <tr>
	                 <td>确认密码:</td>
	                 <td width="80%"><input type="text" name="password2" id="passw2"/></td>
	             </tr>
	             <tr>
	                 <td>邮箱:</td>
	                 <td width="80%"><input type="text" name="email" id="email"/></td>
	             </tr>
	             <tr>
	                 <td>地址:</td>
	                 <td width="80%"><input type="text" name="address" id="address"/></td>
	             </tr>
	             <tr>
	                 <td colspan="2"><button id="an">确定</button></td>
	             </tr>
	         </table>
	     </div>
</div>
</body>
</html>