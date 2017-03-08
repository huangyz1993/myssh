<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Love</title>
<style type="text/css">
img {
	width: 80px;
	margin-left: 15px;
	border-radius: 50%;
}

.hideImg {
	display: none;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$(function() {
		$("#img1").animate({
			marginTop : '157.5px'
		});
		$("#img1").animate({
			marginLeft : '157.5px'
		});
		$("#img1").animate({
			marginTop : '0px'
		});
		$("#img2").animate({
			marginTop : '2005px'
		});
		$("#img2").animate({
			marginLeft : '205px'
		});
		$("#img2").animate({
			marginTop : '0px'
		});
		$("#img3").animate({
			marginLeft : '110px'
		});
		$("#img14").animate({
			marginLeft : '110px'
		});
		$("#img19").animate({
			marginLeft : '205px'
		});
		$("#img22").animate({
			marginLeft : '300px'
		});
	});
</script>
</head>
<body>
	<div style='width: 100%; height: auto'>
		<img id="img1" src="http://localhost:8090${userList[0].relPic}" /><img id="img2"
			src="http://localhost:8090${userList[1].relPic}"
			style="margin-left: 205px !important;" /><br> <br> <img
			id="img3" src="http://localhost:8090${userList[2].relPic}" /><img
			src="http://localhost:8090${userList[3].relPic}" id="img4"/><img
			src="http://localhost:8090${userList[4].relPic}" id="img5"
			style="margin-left: 110px !important;" /><img id="img6"
			src="http://localhost:8090${userList[5].relPic}" /><br> <br>
		<img src="http://localhost:8090${userList[6].relPic}" id="img7" /><img id="img8" 
			src="http://localhost:8090${userList[7].relPic}" /><img id="img9" 
			src="http://localhost:8090${userList[8].relPic}" /><img id="img10" 
			src="http://localhost:8090${userList[9].relPic}" /><img id="img11" 
			src="http://localhost:8090${userList[10].relPic}" /><img id="img12" 
			src="http://localhost:8090${userList[11].relPic}" /><img id="img13" 
			src="http://localhost:8090${userList[12].relPic}" /><br> <br>
		<img src="http://localhost:8090${userList[13].relPic}" id="img14" /><img id="img135" 
			src="http://localhost:8090${userList[14].relPic}" /><img id="img16" 
			src="http://localhost:8090${userList[15].relPic}" /><img id="img17" 
			src="http://localhost:8090${userList[16].relPic}" /><img id="img18" 
			src="http://localhost:8090${userList[17].relPic}" /><br> <br>
		<img src="http://localhost:8090${userList[18].relPic}" id="img19" /><img id="img20" 
			src="http://localhost:8090${userList[19].relPic}" /><img id="img21" 
			src="http://localhost:8090${userList[20].relPic}" /><br> <br>
		<img src="http://localhost:8090${userList[21].relPic}" id="img22" /><br>
		<br>
	</div>
</body>
</html>