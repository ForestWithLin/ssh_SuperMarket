<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详细页</title>
<script type="text/javascript" src="scripts/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="layer/layer.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/login.css" />
<style type="text/css">
input,img{vertical-align:middle;}/*input与img水平线上对齐*/
</style>
<script type="text/javascript">
   $(function(){
		/*--------搜索框--------*/
		$('#inputSearch').focus(function(){
		    $(this).addClass('focus');	
		    if($(this).val()==this.defaultValue){
		    	$(this).val("");
		    }
		}).blur(function(){
			 $(this).removeClass('focus');	
			    if($(this).val()==""){
			    	$(this).val(this.defaultValue);
			    }
	    });
	   
	   $('#btn_food').click(function(){
		   var price=$('#price').text();
		   var tt=$('#txt').val();
		   var count=tt;//库存
		   if('${customer.user}'==""){
			   layer.msg("请登录");
		   }else if(tt==0 || tt==null){
			   alert("请输入数量");
			   return false;
		   }else{
			   url="or_save.action";
		       args={"price":price,"counts":tt};
			   $.post(url,args,function(result){
				   var result=eval("("+result+")");
				   if(result.success){
					   $.layer({
						    shade: [0],
						    title: ['已加入购物车', 'background:#9198c4;'],
						    area: ['500','200'],
						    border: [5, 0.5, '#000'],
						    dialog: {
						        msg: page(),
						        btns: 1,                    
						        type: 1,
						        btn: ['确定'],
						        yes: function(){
						        	alert("已成功购买，请到个人信息中心查询订单号");
						        	$('#txt').val("0");//重置为零
						        	location.reload(); // 刷新本页
						        }
						    }
					   });
				   }else{
					   layer.msg("系统出错");
				   }
			   });
		   }
	   });
   })
	   //数值相加
	  function jia(){
		   var txt=$('#txt');
		   var counts=$('#price').text();
		   var price=${product.proPrice};
		   var count=${product.proCount};//库存
		   if(txt.val()>=count){
			   layer.tips('库存只有那么多..', '#jia', {guide: 1, time: 2});
		   }else{
			   var tt=parseInt(txt.val())+1;
	           txt.val(tt);		   
	           var option=parseInt(tt)*parseInt(price);
	           $('#price').text(option);
		   }
	   }
   
       //检验
       function check(){
    	   var txt=$('#txt');
    	   var counts=$('#price').text();
		   var price=${product.proPrice};//价格
		   var count=${product.proCount};//库存
		   if(txt.val()>=count){
			   var option=parseInt(count)*parseInt(price);
			   txt.val(count);
		   }else{
			   var option=parseInt(txt.val())*parseInt(price);
		   }
		   $('#price').text(option);
       }
       
	   //数值相减
	  function jian(){
		   var txt=$('#txt');
		   var counts=$('#price').text();
		   var count=${product.proPrice};
           if(txt.val()==0){
        	   layer.tips('亲，已经是最小值了', '#jia', {guide: 1, time: 2});
           }else{
			   var tt=parseInt(txt.val())-1;
	           txt.val(tt);		   
	           var option=parseInt(tt)*parseInt(count);
	           $('#price').text(option);
           }
	   }
	   
	   //购物车商品信息
	   function page(){
		   var name=$('#name').text();
		   var type=$('#type').text();
		   var count=$('#txt').val();
		   var price=$('#price').text();
	       var page="<h2>商品信息如下：</h2>"
				    + "你购买的产品是："+name
				    +"<br/> 类型："+type
				    +"<br/> 数量："+count
				    +"<br/> 总计："+price;
	       return page;
	   }
	   
</script>
</head>  
<body>  
    <div id="header">  
       <jsp:include page="login/log_top.jsp"></jsp:include>
    </div>  
    <div id="content">
    <div class="shopBar">
	        <div id="jnDatalog">
	            <jsp:include page="login/log_left.jsp"></jsp:include>
	        </div>
		    <div id="jnProitem">
		       <div class="jnProImg">
    		        <img width="548" height="400" src="${product.proPic }">
		       </div>
		       <div id="jnProShow">
		            <ul>
		               <li><a href="#">产品介绍</a></li>
		               <li><a href="#">产品属性</a></li>
		               <li><a href="#">产品功能</a></li>
		            </ul>
		       </div>
		       <div id="jntxt">121111122</div>
		    </div>
		    <div id="jnDetails">
		       <h2 id="name">${product.proName }</h2>
		       <br class="clear"/>
		       <p>${product.content }</p>
		       <br class="clear"/>
               <h3>价     格：</h3><p>${product.proPrice }元</p>
		       <br class="clear"/>
		       <br class="clear"/>
               <h3>存     库：</h3><p>${product.proCount }</p>
		       <br class="clear"/>
		       <br class="clear"/>
               <h3>尺     寸：</h3><p>未选择</p>
		       <br class="clear"/>
		       <br class="clear"/>
               <h3>类     型：</h3><p id="type">${product.proType.proName }</p>
		       <br class="clear"/>
		       <br class="clear"/>
               <h3>数    量：</h3> 
               <div style="display: inline;">
	               <a href="javascript:void(0);" onclick="jian();"><img alt="jian" height="25" width="25" src="images/jian.jpg"></a>
	               <input type="text" style="width: 30px" onkeypress="this.value=this.value.replace(/[^\d+$]/g, '')" value="0" id="txt" onblur="check();"/>
	               <a href="javascript:void(0);" onclick="jia();" id="jia"><img alt="jia" height="25" width="25" src="images/jia.jpg"></a>
               </div>
		       <br class="clear"/>
		       <br class="clear"/>
               <h3>总    计：</h3><p id="price">00.00</p>元
		       <br class="clear"/>
		       <br class="clear"/>
               <input type="button" value="放入购物车" id="btn_food" class="btn"/>
		    </div>
	    </div>
    </div>
</body>  
</html>

