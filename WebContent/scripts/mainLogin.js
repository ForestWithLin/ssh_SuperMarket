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
	
	/*------------导航条--------------*/
	$('#nav li').hover(function(){
		$(this).find('.jnNav').show();
	},function(){
		$(this).find('.jnNav').hide();
	});
	
	/*-------------图片自动切换----------------*/
	var $imgrolls=$('#jnIamg div a');
	var len=$imgrolls.length;
	var index=0;
	var adTimer=null;
	$imgrolls.mouseover(function(){
		index = $imgrolls.index(this);
		showImg(index);
	}).eq(0).mouseover();
	$('#jnIamg').hover(function(){
		if(adTimer){
			clearInterval(adTimer);
		}
	},function(){
		adTimer=setInterval(function(){
			showImg(index);
			index++;
			if(index==len){index=0;}
		},5000);
	}).trigger('mouseleave');
	
	/*------------层转换----------------*/
	$('#jnBrandTab li a').click(function(){
		$(this).parent().addClass("chos")
		       .siblings().removeClass("chos");
		var idx=$('#jnBrandTab li a').index(this);
		showBrandList(idx);
		return false;
	}).eq(0).click();
});

/*--------显示不同的图片-----*/
function showImg(index){
	var $rollobj=$('#jnIamg');
	var $rolllist=$rollobj.find('div a');
	var newhref=$rolllist.eq(index).attr('href');
	$('#JS_imgWrap').attr('href',newhref)
	.find('img').eq(index).stop(true,true).fadeIn()
	.siblings().fadeOut();
}

/*----------显示不同的模板------------*/
function showBrandList(index){
	var $rollobj=$('#jnBrandList');
	var rollWidth=$rollobj.find("li").outerWidth();
	rollWidth=rollWidth*5;//版面宽度
	$rollobj.stop(true,false).animate({ left : -rollWidth*index},1000);
}
