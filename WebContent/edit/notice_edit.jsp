<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#btn,#btn2{  
            border:medium none;  
            background: #9198c4; 
            position:absolute;
            height:35px;  
            color:#fff;  
            font-size:16px;  
            cursor:pointer;  
            font-weight:bold;  
            width: 100px;  
        }  
#btn:hover,
#btn2:hover{
         background:#3f89ec;
  }
</style>
</head>
<body>
    <s:form action="not_editSave.action" method="post">
         <s:textfield name="title" label="标题"></s:textfield>
         <s:hidden name="id"></s:hidden>
         <s:textarea name="content" label="内容" style="resize:none;height:150px;width:300px;" ></s:textarea>
         <s:submit value="保存" id="btn"></s:submit>
     </s:form>
</body>
</html>