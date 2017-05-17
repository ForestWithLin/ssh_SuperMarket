<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
  function retu(){
	  location.href='login.jsp';
  }
</script>
</head>
<body>
<center>
<h4>REGIS PAGE</h4>
<div class="div1">
   <form action="user_save.action" method="post">
   <table>
        <tr>
        <td>用户名：</td>
           <td class="field">
               <input type="text" class="user" name="user"/>
           </td>
        </tr>
        <tr>
        <td>密码：</td>
           <td class="field">
               <input type="text" class="password" name="password"/>
           </td>
        </tr>
        <tr>
        <td>邮箱：</td>
           <td class="field">
               <input type="text" class="email" name="email"/>
           </td>
        </tr>
        <tr>
        <td>地址：</td>
           <td class="field">
               <input type="text" class="address" name="address"/>
           </td>
        </tr>
        <tr>
           <td class="field">
               <input type="submit" value="注册" />
           </td>
           <td class="field">
               <input type="button" value="回退" onclick="retu()"/>
           </td>
        </tr>
   </table>
   </form>
</div>
</center>
</body>
</html>