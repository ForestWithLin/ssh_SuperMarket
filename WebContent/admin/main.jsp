<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上商城后台管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	// 打开新标签
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+url+"'></iframe>"
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
	
	function openPasswordModifyDialog(){
		url="user_modify.action";
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
	}
	
	function modifyPassword(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				var oldPassword=$("#oldPassword").val();
				var newPassword=$("#newPassword").val();
				var newPassword2=$("#newPassword2").val();
				if(!$(this).form("validate")){
					return false;
				}
				if(oldPassword!='${user.password }'){
					$.messager.alert('系统提示','用户密码输入错误！');
					return false;
				}
				if(newPassword!=newPassword2){
					$.messager.alert('系统提示','确认密码输入错误！');
					return false;
				}
				return true;
			},
			success:function(result)
		    {
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert('系统提示','密码修改成功，下一次登录生效！');
					closePasswordModifyDialog();
				}else{
					$.messager.alert('系统提示','修改密码失败');
					return;
				}
		    }
		});
	}
	
	function closePasswordModifyDialog(){
		$("#dlg").dialog("close");
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#newPassword2").val("");
	}
	
	function logout(){
		$.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
			if(r){
				window.location.href='${pageContext.request.contextPath}/user_logout.action';
			}
		});
	}
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
<table style="padding: 5px;" width="100%">
	<tr>
		<td width="50%"><img src="${pageContext.request.contextPath}/images/01.png"/></td>
		<td valign="bottom" align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${user.user }</font></td>
	</tr>
</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px;">
			     <font color="red" size="10">个人项目后台管理系统</font>
			     <br>
			     <font color="red" size="10">负责人：林伟健</font>
			     <br>
			     <font color="red" size="10">学号：20120303127</font>
			</div>
		</div>
	</div>
</div>
<div region="west" style="width: 200px;" title="导航菜单" split="true">
<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="用户管理"  data-options="selected:true,iconCls:'icon-user'" style="padding:10px;">
			<a href="javascript:openTab('用户管理','user_all.action','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理用户</a>
		</div>
		<div title="商品管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
			<a href="javascript:openTab('商品管理','prod_all.action','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品</a>
			<a href="javascript:openTab('商品大类','prot_all.action','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">商品大类</a>
			<a href="javascript:openTab('商品小类','promt_all.action','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">商品小类</a>
		</div>
		<div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:10px">
			<a href="javascript:openTab('订单管理','or_all.action','icon-order')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理订单</a>
		</div>
		<div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:10px">
			<a href="javascript:openTab('留言管理','comt_all.action','icon-comment')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理留言</a>
		</div>
		<div title="公告管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
			<a href="javascript:openTab('公告管理','not_all.action','icon-notice')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理公告</a>
		</div>
		<div title="新闻管理"  data-options="iconCls:'icon-news'" style="padding:10px">
			<a href="javascript:openTab('新闻管理','news_all.action','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理新闻</a>
		</div>
		<div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px">
			<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
		</div>
</div>
</div>
<div region="south" style="height: 25px;padding: 5px;" align="center">
	个人微博 <a href="http://weibo.com/u/2257600491" target="_blank">www.weibo.com/u/2257600491</a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 220px;padding: 10px 20px" 
 closed="true" buttons="#dlg-buttons" >
 <form id="fm" method="post">
 	<table cellspacing="4px;">
 		<tr>
 			<td>用户名：</td>
 			<td><input type="hidden" name="id" id="id" value="${user.id }"><input type="text" name="user" id="userName" readonly="readonly" value="${user.user }" style="width: 200px;" /></td>
 		</tr>
 		<tr>
 			<td>原密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="passoword" id="oldPassword" style="width: 200px;" required="true" /></td>
 		</tr>
 		<tr>
 			<td>新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="password" id="newPassword" style="width: 200px;" required="true"  /></td>
 		</tr>
 		<tr>
 			<td>确认新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="newPassword2" id="newPassword2" style="width: 200px;" required="true" /></td>
 		</tr>
 	</table>
 </form>
</div>
<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>