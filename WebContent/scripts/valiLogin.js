 //<--------以下为登录验证--------->
        function fuser(){
         var user=$('#tbUserName').val();
         var password=$('#tbPassword').val();
         user=$.trim(user);
         password=$.trim(password);
    	 if(user=="")	
         { 	
    		layer.tips('用户名不能为空', '#tbUserName',  {guide: 1, time: 2});
      	    return false;
         }else if(password==""){
    		layer.tips('密码不能为空', '#tbPassword', {guide: 1, time: 2});
    		return false;
         }else if(user=="root"){
        	 layer.msg('你无权限使用该账号');
        	 return false;
         }
    	 var url="user_regis.action";
	     var args=$('#form1').serialize();
	      $.post(url,args,function(result){
	    	  var result=eval('('+result+')');
	    	  if(result.success=="2"){
	    		  alert('亲、登陆失败');
	    		  return false;
	    	  }else if(result.success=="1"){
	    		 window.location.href="login.jsp";
	    	  }else{
	    		  alert('服务器出错');
	    		  return false;
	    	  }
	       });
     }
        
        function froot(){
        	var root=$('#UserName').val();
        	var password=$('#tbPassword').val();
        	root=$.trim(root);
        	password=$.trim(password);
        	 if(root=="")	
             { 	
        		layer.tips('用户名不能为空', '#UserName',  {guide: 1, time: 2});
          	    return false;
             }else if(password==""){
        		layer.tips('密码不能为空', '#tbPassword', {guide: 1, time: 2});
        		return false;
             }
        	 
        	 var url="user_regis.action";
    	     var args=$('#form1').serialize();
    	      $.post(url,args,function(result){
    	    	  var result=eval('('+result+')');
    	    	  if(result.success=="2"){
    	    		  alert('测试版账号为root，密码123456');
    	    		  return false;
    	    	  }else if(result.success=="1"){
    	    		 window.location.href="user_root.action";
    	    	  }else{
    	    		  alert('服务器出错');
    	    		  return false;
    	    	  }
    	       });
        }
        
  //-----------以下为注册验证-----------------
        //注册用户名验证
  function change(){
			var val=$("#reUser").val();
			val=$.trim(val);
			var $this=$("#reUser");
			if(val!=""){
				$("#erroDiv>font").remove();
				var url="user_validateUser.action";
				var args={"user":val};
				$.post(url,args,function(date){
					var date=eval('('+date+')');
					if(date.success=="1"){
						$("#erroDiv").append("<font color='green'>√ 用户名可用</font>");
					}
					else if(date.success=="0"){
						$("#erroDiv").append("<font color='red'>× 用户名不可用</font>");
					}
					else{
						alert("服务器错误！");
					}
				});
			}else{
				layer.tips('用户名不能为空', '#reUser',  {guide: 1, time: 2});
				$("#reUser").val("");
				$this.focus();
			}
  }
    //注册验证
    function regis()
    {
 	    var reg3=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;//邮箱正则表达式
 	    var reg4=/^(\w| [\u4e00-\u9fa5] )+$/;//地址正则表达式
 	    var s3=$('#reEmail').val();
 	    var s4=$('#reAddress').val();
		 var password=$('#rePassw').val();
		 var password1=$('#rePassws').val();
     	 password=$.trim(password);
     	 password1=$.trim(password1);
     	 if(password=="" && password1==""){
     		layer.tips('密码不能为空', '#rePassw', {guide: 1, time: 2});
     		return false;
          }else{
        	  if(password1!=password){
        		  alert("密码不一致");
        		  password="";
        		  password1="";
        		  return false;
        	  }else
   	    	   {
        		  if(reg3.test(s3)==false||s3==null)
	    	        {
        			  layer.tips('请输入合法邮箱', '#reEmail', {guide: 1, time: 2});
	    	          s3="";
	    	          return false;
	    	        }
			    	else if(reg4.test(s4)==false||s4==null)
			    	{
				    		layer.tips('请输入合法地址', '#reAddress', {guide: 1, time: 2});
				    	    s4="";
				    	    return false;
			    	}else{
		   	    		var url="user_valiregis.action";
		   		    	var args=$('#fm').serialize();
		   		        $.post(url,args,function(result){
		   		    	  var result=eval('('+result+')');
		   		    	  if(result.success){
		   		    		  alert('注册成功！');
		   		    		  $('#reUser').val("");
		   		    		  $('#rePassw').val("");
		   		    		  $('#rePassws').val("");
		   		    		  $('#reEmail').val("");
		   		    		  $('#reAddress').val("");
		   		    		  $("#erroDiv>font").remove();
		   		    	  }else{
		   		    		  alert('注册失败');
		   		    	  }
		   		       });
	   	    	   }
              }
	    	
          }
      }