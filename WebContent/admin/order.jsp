<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.min.js"></script>
<style type="text/css">
  .check{
     background: #fbf8e9;  
  }
</style>
<script type="text/javascript">
  $(function(){
		 //每页显示数目
	  $('#selects').change(function(){
	    	 var selects=$('#selects').val();
	    	 if(selects==1){
	    		 window.location.href="or_all.action?prePage=10";
	    	 }
	    	 if(selects==2){
	    		 window.location.href="or_all.action?prePage=20";
	    	 }
	    	 if(selects==3){
	    		 window.location.href="or_all.action?prePage=30";
	    	 }
	    	 if(selects==4){
	    		 window.location.href="or_all.action?prePage=40";
	    	 }
	     });
	  
  	  $('#refresh').click(function(){
  		 window.location.reload(); //刷新
  	  });
  	  
  	  //复选框功能
      $('.demo #check').click(function(){
    	  if($(this).is(":checked")){
           	  $(this).closest('tr').addClass("check");
    	  }else{
    		  $(this).closest('tr').removeClass("check");
    	  }
      });
  	  
  	  //发货操作
  	  $('#deliver').click(function(){
  		if(count()!=1){
			layer.msg("请选择一条要操作的数据！");
			return;
		}
  		var index=finindex();
  		var $v=$('#txt em').eq(index);//获取选中checkbox所在的em标签
  		var $id=$('.bordered #num').eq(index);//获取选中checkbox所在的id号
  		var status=clicksta(index);//获取选中checkbox所在的status值
  		id=$id.text();
  		id=$.trim(id);
  		v=$v.text();
  		v=$.trim(v);//清空空字符串
		if(v=="未完成"){
			url='or_num.action';
			args={'status':status,'id':id};
			$.post(url,args,function(result){
				var result=eval('('+result+')');
				if(result.success){
					$v.text("卖家已发货");
		    		layer.msg('已发货..', 3, -1);
				}else{
					layer.alert("系统出错");
				}
			});
		}else if(v=="卖家已发货"){
			layer.alert('已经发货当中ing..', 11, !1);
		}else if(v=="交易已完成"){
			layer.msg('交易已经完成、你想亏本吗！', 3, 13);
		}
  	  });
  	  
  	  //获取选中checkbox所在的status值
  	  function clicksta(index){
  		  var $status=$('#txt em').eq(index);
  		  status=$status.text();
  		  status=$.trim(status);
  		if(status=="未完成"){
  			status='2';
		}else{
			status='0';
		}
  		  status=parseInt(status);//强转为integer类型
  		  return status;
  	  }
  	  
  	  //获取check选择的个数
  	  function count(){
  		var selectedRows=$(".demo #check");
  		var checked_counts = 0;
  		for(var i=0;i<selectedRows.length;i++){
	  		if(selectedRows[i].checked){
	  		checked_counts++;
	  		}
  		}
  		return checked_counts;
  	  }
  	  
  	  //获取点击的checkbox索引
  	  function finindex(){
  		var selectedRows=$(".demo #check");
  		for(var i=0;i<selectedRows.length;i++){
  			if(selectedRows[i].checked){
  	   	      var index=i;//获取点击checkbox的索引
	  		}
  		}
  		  var v= index;
  		return v;
  	  }
 })
 
 //判断下拉菜单是否跟当前页记录数相同
function f(){
	  var selects=$('#selects').val();
	  var str=${pageBeans.prePage };
	  if(str==10){
		  $('#selects').get(0).options[0].selected=true;
	  }
	  if(str==20){
		  $('#selects').get(0).options[1].selected=true;
	  }
	  if(str==30){
		  $('#selects').get(0).options[2].selected=true;
	  }
	  if(str==40){
		  $('#selects').get(0).options[3].selected=true;
	  }
}
window.onload=f;
</script>
</head>
<body>
<div class="tag">
 <form action="or_all.action" method="post" id="form1">
    <table >
      <tr>
          <td>订单号：</td>
          <td>
               <input type="text" name="orderNum" class="search"/>
          </td>
          <td class="button"><a href="#" onclick="$('#form1').submit();">搜索</a></td>
          <td class="button"><a href="#" id="deliver">发货</a></td>
      </tr>
    </table>
    </form>
</div>
<div class="tag2">
<div class="demo">
      <s:if test="#request.admin_orders==null || #request.admin_orders.size()==0">
                              没有订单信息！
      </s:if>
      <s:else>
          <table border="1" cellpadding="10" cellspacing="0" class="bordered">
              <tr>
                 <th></th>
                 <th>编号</th>
                 <th>订单号</th>
                 <th>下订单人ID</th>
                 <th>下订单客户</th>
                 <th>创建时间</th>
                 <th>总金额</th>
                 <th>订单状态</th>
              </tr>
              <s:iterator value="#request.admin_orders">
              <tr>
                  <td><input type="checkbox" id="check" /></td>
                  <td id="num">${id }</td>
                  <td>${orderNum }</td>
                  <td>${userId.id }</td>
                  <td>${userId.user }</td>
                  <td>
                      <s:date name="orderTime" format="yyyy-MM-dd hh:mm:ss"/>
                  </td>
                  <td>${orderCost}</td>
                  <td class="button" id="txt">
                       <s:if test="status==1"><em>未完成</em></s:if>
                       <s:if test="status==2"><em>卖家已发货</em></s:if>
                       <s:if test="status==3"><em>交易已完成</em></s:if>
                  </td>
              </tr>
              </s:iterator>
          </table>
          </s:else>
 </div>
 <div class="doo">
     <div class="do">
	   <select id="selects">
	     <option value="1">10</option>
	     <option value="2">20</option>
	     <option value="3">30</option>
	     <option value="4">40</option>
	   </select>
	   <a href="or_all.action?page=${pageBeans.firstPage }"> &lt;&lt; </a>
	   <a href="or_all.action?page=${pageBeans.previousPage }">上一页</a>
	   <a href="or_all.action?page=${pageBeans.nextPage }">下一页</a>
	   <a href="or_all.action?page=${pageBeans.lastPage }"> &gt;&gt; </a>
	   <a href="#" id="refresh">刷新</a>
    </div>
	   <div style="float: right;">
		         第${pageBeans.currentPage }页
		        每页共${pageBeans.prePage }条记录
		        总共${pageBeans.allPage }页
		        总共${pageBeans.allRecords }条记录
	   </div>
   </div>
</div>
</body>
</html>