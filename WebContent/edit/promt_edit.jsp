<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#form4').submit(function(){
		var f=$('#name').val();
		if(f==""){
			alert("请填上名称");
			return false;
		}
	})
})
</script>
</head>
<body>
<div class="div1">
  <s:form action="promt_save.action" method="post" id="form4">
     <s:if test="id != null">
         <s:textfield name="proName" label="名称" disabled="true"></s:textfield>
         <s:hidden name="id"></s:hidden>
     </s:if>
     <s:else>
         <s:textfield name="proName" label="名称" id="name"></s:textfield>
     </s:else>
		 <s:select list="#session.prodTypes" listKey="id"
		  listValue="proName" name="productType.id" label="商品大类">
		 </s:select>
         <s:textarea name="remarks" label="备注" style="resize:none;height:150px;width:200px;" ></s:textarea>
         <s:if test="id!=null">
		       <input type="submit" value="修改" />
	     </s:if>
	     <s:else>
		       <input type="submit" id="btn" value="保存" />
	     </s:else>
     </s:form>
</div> 
</body>
</html>