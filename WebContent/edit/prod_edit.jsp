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
	$('#form1').submit(function(){
		var f=$('#file').val();
		var txtName=$('#txtName').val();
		if(txtName==""){
			alert("商品名称不能为空");
			return false;
		}
		if(f==""){
			alert("请选择图片");
			return false;
		}
	});
	//二级联动
	$("#select1").change(function() {
		$('#select2').find("option").remove();//清空原有的值
        if ($("#select1").val() > 0) {
                $.post("promt_list.action", { proType: $("#select1").val() }, function(result) 
                {
                	var result=eval('('+result+')');//转为对象
                	$.each(result, function(i){//遍历对象
               		  /*$.each(item, function(key, val) {
               		    $("<option value='"+val+"'>"+val+"</option>").appendTo($("#select2"))      
               		  });*/
               		  $("<option value='"+result[i].id+"'>"+result[i].proName+"</option>").appendTo($("#select2"));
               		});
               });
         }
    });
});
</script>
</head>
<body>
   <div class="div1">
   <s:fielderror />
   <s:actionerror />
   <form action="prod_save.action" method="post" id="form1" enctype="multipart/form-data">
   <s:hidden name="id"></s:hidden>
   <s:textfield name="proName" label="商品名称" id="txtName"></s:textfield>
   <br/>
   <s:textfield name="proCount" label="存库" onkeypress="this.value=this.value.replace(/[^\d+$]/g, '')"></s:textfield>
   <br/>
   <s:textfield name="proPrice" label="金额" onkeypress="this.value=this.value.replace(/[^\d+$]/g, '')"></s:textfield>
   <br/>
   <s:select list="#session.prodTypes" listKey="id"
      listValue="proName" name="proType.id" label="商品大类" id="select1" value="id">
    </s:select>
   <br/>
        <!--  <s:select list="#session.proSmallTypes" listKey="id"
          listValue="proName" name="proSmType.id" id="select2" >
          </s:select> -->
              商品小类<select id="select2" name="proSmType.id">
          </select>
   <br/>
   <s:textarea name="content" rows="7" cols="40" style="resize: none;" label="商品内容"></s:textarea>
   <br/>
   <s:if test="id!=null">
	   <s:file label="上传图片" name="propic"></s:file>
	   <br/>
       <input type="submit" value="修改" />
   </s:if>
   <s:else>
	   <s:file label="上传图片" name="propic" id="file"></s:file>
	   <br/>
        <input type="submit" id="btn" value="保存" />
   </s:else>
   </form>
   </div> 
</body>
</html>