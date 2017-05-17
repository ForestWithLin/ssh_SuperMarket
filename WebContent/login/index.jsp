<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="layer/layer.min.js"></script>
<script type="text/javascript" src="scripts/valiLogin.js"></script>
   <style>  
   *{margin: 0;padding: 0;}
       body  
        {  
            font-size: 16px; vertical-align: middle;  
        }  
         input
        {  
            width: 200px;  
            height: 23px;
        }  
        #top li{
           display: inline;
        }
        .btn1{  
            text-decoration:none;
            display:inline;
			font-family: 'trebuchet MS', 'Lucida sans', Arial;
			font-size:16px;
            color:blue;
        }  
        .btn2{  
            text-decoration:none;
            display:inline;
			font-family: 'trebuchet MS', 'Lucida sans', Arial;
			font-size:16px;
            color:blue;
        }  
        .div_di{
	       position:absolute;
	       bottom:0px;
	       background-color:#abb1a3;
	       width:100%;
	       height:50px;
	       color:white;
	       font-size:30px;
	       text-align: center;
	       line-height:50px;
        }
        .login_main_div  
        {  
            position:absolute;
            left:2%;
        }  
        .login_main_table  
        {  
            float: left; padding-left: 50px;margin-bottom: 0px;  
        }  
        #erroDiv  
        {  
            height: 10px; padding-top: 5px; padding-bottom: 5px;  
        }  
        #erroDiv0  
        {  
            height: 20px; padding-top: 5px; padding-bottom: 5px;  
        }  
        .loginBtn  
        {  
            border:medium none;  
            background:#3f89ec;  
            position:absolute;
            left:50%;
            height:35px;  
            color:#fff;  
            font-size:16px;  
            cursor:pointer;  
            font-weight:bold;  
            width: 220px;  
        }  
    </style>  
        <script>
        $(function(){  
        	//调用layer扩展方法
         	 layer.use('extend/layer.ext.js')
         	 //管理员登录页面层
            $("#root").click(function(){  
            $.layer({
                type: 1,
                title: ['管理员登录', 'background:#9198c4;'],
                area: ['500', '200'],//宽高
                border: [0], //去掉默认边框
                shade: [0.5 , '#000' , true], //遮罩
                closeBtn: [0, true], //默认关闭按钮
                shift: 'top', //从顶动画弹出
                page: {
                    html: ' <div class="login_main_div"><form id="form1" method="post"><table class="login_main_table" width="350" border="0" align="right" cellpadding="0" cellspacing="0"><tbody><tr><td><div id="erroDiv"></div></td></tr><tr><td>用户名: <input  class="username" name="user" type="text" id="UserName"></td></tr><tr><td style="padding-top: 12px">密&nbsp;&nbsp;码 :<input class="userPwd"  name="password" type="password" id="tbPassword"></td></tr><tr><td ><div id="erroDiv0"></div></td></tr><tr><td ><input id="login" value="登录"  type="button" onclick="return froot();" class="loginBtn"></td></tr></tbody></table></form></div> '
                }
            });
            });
        	//弹出登录注册页面层
            $("#loh").click(function(){  
                    layer.tab({
		                data:[
		                    {title: '登录', content:'<div class="login_main_div"><form id="form1" method="post"><table class="login_main_table" width="350" border="0" align="right" cellpadding="0" cellspacing="0"><tbody><tr><td><div id="erroDiv0"></div></td></tr><tr><td>用户名: <input  class="username" name="user" type="text" id="tbUserName"></td></tr><tr><td style="padding-top: 12px">密&nbsp;&nbsp;&nbsp;码:<input class="userPwd"  name="password" type="password" id="tbPassword"></td></tr><tr><td><div id="erroDiv0"></div></td></tr><tr><td ><input id="login" value="登录"  type="button" onclick="return fuser();" class="loginBtn"></td></tr></tbody></table></form></div><div class="div_di">亲、登录失败？去注册账号吧！</div>'}, 
		                    {title: '注册', content:'<div class="login_main_div"><form id="fm" method="post"><table class="login_main_table" width="350" border="0" align="right" cellpadding="0" cellspacing="5"><tr><td><div id="erroDiv"></div></td></tr><tr><td class="field">用&nbsp;户&nbsp;&nbsp;名：<input type="text" class="user" name="user" id="reUser" onblur="change()"/></td></tr><tr><td class="field">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="text" class="password" name="password" id="rePassw"/></td></tr><tr><td class="field">确认密码：<input type="text" id="rePassws"/></td></tr><tr><td class="field">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" class="email" name="email" id="reEmail"/></td></tr><tr><td class="field">地&nbsp;&nbsp;&nbsp;&nbsp;址：<input type="text" class="address" name="address" id="reAddress"/></td></tr><tr><td><div id="erroDiv"></div></td></tr><tr><td class="field"><input type="button" value="注册" class="loginBtn" onclick="return regis();"/></td><td class="field"></tr></table></form></div>'}
		                ],
		                area: ['600px', '250px'] //宽度，高度
		            });
            });
        })  
        </script>
	</head>
	<body>  
<div id="top">  
   <ul>
       <li><a id="loh" class="btn1" href="#">用户登录</a> </li>
       <li><a id="root" class="btn2" href="#">管理员登录</a> </li>
        <li><a href="comt_list.action" style="display: inline;">留言</a></li>
   </ul>
</div>  