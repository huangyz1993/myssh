nowPage=1;//当前页
allPage=1;//所有页数
choosePage=1;//选择页数
$(function(){
	str="";
	if(allPage<10){
		for(i=0;i<allPage;i++){
			str+="<span onclick='choosePageAction("+(i+1)+")'> "+(i+1)+" </span>";
		}
	}else{
		for(i=0;i<10;i++){
			str+="<span onclick='choosePageAction("+(i+1)+")'> "+(i+1)+" </span>";
		}
	}
	
	$("#previousPage").after(str);
	
	
	
	$("#previousPage,#firstPage,#nextPage,#lastPage").click(function(){
		chooseId=$(this).attr("id");
		if(chooseId=="firstPage"){
			choosePage=1;
		}else if(chooseId=="previousPage"){
			if(nowPage>1){
				choosePage=nowPage-1;
			}
		}else if(chooseId=="nextPage"){
			if(allPage>nowPage){
				choosePage=nowPage+1;
			}
		}else{
			choosePage=allPage;
		}
		nowPage=choosePage;
	})
})


function choosePageAction(text){
	choosePage=text;
	userStr="";
	$.post("/mySSH/wuzhiJson!showWuzhiJson", {
		page:choosePage
	},function(result){
		console.log();
		$.each(result, function(index, obj) {
			if(index%8==0){
				userStr+="<div class='floatLeft width11 marginLeft3 marginTop1'><a href='wuzhi!showUserArticle?userId="+obj.userid+"'><img src='http://localhost:8090"+obj.relPic+"' width='100%' /></a></div>";
			}else if((index+1)%8!=0){
				userStr+="<div class='floatLeft width11 marginLeft1 marginTop1'><a href='wuzhi!showUserArticle?userId="+obj.userid+"'><img src='http://localhost:8090"+obj.relPic+"' width='100%' /></a></div>";
			}else{
				userStr+="<div class='floatLeft width11 marginLeft1 marginTop1'><a href='wuzhi!showUserArticle?userId="+obj.userid+"'><img src='http://localhost:8090"+obj.relPic+"' width='100%' /></a></div><div class='floatClear'></div>";
			}
			if(index==result.length-1){
				userStr+="<div class='floatClear'></div>";
			}
		    //alert(obj.tagName);
		});
		$(".width100").html(userStr);
		 //$("html,body").animate({scrollTop:0},"slow");
	});
	
}

