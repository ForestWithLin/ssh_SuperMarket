<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.min.js"></script>
<script type="text/javascript">
     function confirmReceive(id){
    	 $.layer({
    		    shade: [0],
    		    area: ['auto','auto'],
    		    dialog: {
    		        msg: '是否确认收货？',
    		        btns: 2,                    
    		        type: 4,
    		        btn: ['确定','取消'],
    		        yes: function(){
    		        	url='or_num.action';
    		 			args={'status':3,'id':id};
    		 			$.post(url,args,function(result){
    		 				var result=eval('('+result+')');
    		 				if(result.success){
    		 		    		alert('交易成功', 1, 1);
    		 		    		location.reload(); // 刷新本页
    		 				}else{
    		 					layer.alert("系统出错");
    		 				}
    		 			});
    		        }, no: function(){
    		            layer.msg('取消成功', 1, 2);
    		        }
    		    }
    		});
     }
</script>
</head>
<body>
<!-- 右侧信息栏-->
<div id="right">
     <h5>订单管理</h5>
     <hr>
    <div id="right_manage">
        <div id="search">
            <form action="" method="post">
                                      订单号：<input type="text" class="text" name="" /> 
            <button class="ui-blue">查询 </button>
         </form>
        </div>
	       <div id="manage">
	           <table class="bordered">
	                <s:iterator value="#request.orders">
					<tr style="background-color: #f7f4eb">
						<td colspan="4">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单号：${orderNum }  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下单时间：${orderTime }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 状态：
						<s:if test="status==1">未发货</s:if>
						<s:if test="status==2"><input type="button" style="border: 1px solid red;cursor:pointer; " value="确认收货" onclick="confirmReceive(${id })"/></s:if>
						<s:if test="status==3">交易完成</s:if>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总金额：${orderCost }&nbsp;(元)
					    </td>
					</tr>
	                  <s:iterator value="orderProducts">
	                  <tr>
	                      <td width="50%"><img width=185 height=188 src="${proId.proPic }"><a href="prod_show.action?id=${proId.id }">${proId.proName }</a></td>
	                      <td width="17%">总价：${orderCost }</td>
	                      <td width="17%">数量：${num }</td>
	                      <td>单价：${proId.proPrice }</td>
	                  </tr>
	                  </s:iterator>
	                 </s:iterator>
	           </table>
	       </div>
     </div>
</div>
</body>
</html>