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
	position: absolute;
}

.hideImg {
	display: none;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	 $(function() {
		$("#img1").animate({
			marginTop : '450px'
		},"slow");
		/* $("#img1").animate({
			//marginLeft : '157.5px'
		}); */
		$("#img1").animate({
			marginTop : '0px'
		},"slow");
		$("#img2").animate({
			marginTop : '450px'
		},"slow");
	/* 	$("#img2").animate({
			//marginLeft : '442.5px'
		}); */
		$("#img2").animate({
			marginTop : '0px'
		},"slow");
		$("#img3,#img4,#img5,#img6").animate({
			marginTop : '360px'
		},"slow");
		/* $("#img3").animate({
			//marginLeft : '442.5px'
		}); */
		$("#img3,#img4,#img5,#img6").animate({
			marginTop : '90px'
		},"slow");
		$("#img7,#img8,#img9,#img10,#img11,#img12,#img13").animate({
			marginTop : '270px'
		},"slow");
		$("#img7,#img8,#img9,#img10,#img11,#img12,#img13").animate({
			marginTop : '180px'
		},"slow");
		$("#img14,#img15,#img16,#img17,#img18").animate({
			marginTop : '180px'
		},"slow");
		$("#img14,#img15,#img16,#img17,#img18").animate({
			marginTop : '270px'
		},"slow");
		$("#img19,#img20,#img21").animate({
			marginTop : '90px'
		},"slow");
		$("#img19,#img20,#img21").animate({
			marginTop : '360px'
		},"slow");
		$("#img22").animate({
			marginTop : '0px'
		},"slow");
		$("#img22").animate({
			marginTop : '450px'
		},"slow");
	});
</script>
</head>
<body>
	<div style='width: 100%; height: auto'>
		<img id="img1" src="http://localhost:8090${userList[0].relPic}"
			style="margin-left: 157.5px !important;" /><img id="img2"
			src="http://localhost:8090${userList[1].relPic}"
			style="margin-left: 442.5px !important;" /> 
		<img id="img3"
			src="http://localhost:8090${userList[2].relPic}"
			style="margin-left: 110px !important; margin-top: 90px;" /><img
			src="http://localhost:8090${userList[3].relPic}" id="img4" style="margin-left: 205px !important; margin-top: 90px;"/><img
			src="http://localhost:8090${userList[4].relPic}" id="img5"
			style="margin-left: 395px !important;margin-top: 90px;" /><img id="img6"
			src="http://localhost:8090${userList[5].relPic}" style="margin-left: 490px !important;margin-top: 90px;"/>
		<img
			src="http://localhost:8090${userList[6].relPic}" id="img7" style="margin-left: 15px !important;margin-top: 180px;"/><img
			id="img8" src="http://localhost:8090${userList[7].relPic}" style="margin-left: 110px !important;margin-top: 180px;"/><img
			id="img9" src="http://localhost:8090${userList[8].relPic}" style="margin-left: 205px !important;margin-top: 180px;"/><img
			id="img10" src="http://localhost:8090${userList[9].relPic}" style="margin-left: 300px !important;margin-top: 180px;"/><img
			id="img11" src="http://localhost:8090${userList[10].relPic}" style="margin-left: 395px !important;margin-top: 180px;"/><img
			id="img12" src="http://localhost:8090${userList[11].relPic}" style="margin-left: 490px !important;margin-top: 180px;"/><img
			id="img13" src="http://localhost:8090${userList[12].relPic}" style="margin-left: 585px !important;margin-top: 180px;"/>
		<img
			src="http://localhost:8090${userList[13].relPic}" id="img14"
			style="margin-left: 110px !important;margin-top: 270px;" /><img id="img15"
			src="http://localhost:8090${userList[14].relPic}" style="margin-left: 205px !important;margin-top: 270px;"/><img id="img16"
			src="http://localhost:8090${userList[15].relPic}" style="margin-left: 300px !important;margin-top: 270px;"/><img id="img17"
			src="http://localhost:8090${userList[16].relPic}" style="margin-left: 395px !important;margin-top: 270px;"/><img id="img18"
			src="http://localhost:8090${userList[17].relPic}" style="margin-left: 490px !important;margin-top: 270px;"/>
		<img
			src="http://localhost:8090${userList[18].relPic}" id="img19"
			style="margin-left: 205px !important;margin-top: 360px;" /><img id="img20"
			src="http://localhost:8090${userList[19].relPic}" style="margin-left: 300px !important;margin-top: 360px;"/><img id="img21"
			src="http://localhost:8090${userList[20].relPic}" style="margin-left: 395px !important;margin-top: 360px;"/>
		<img
			src="http://localhost:8090${userList[21].relPic}"
			style="margin-left: 300px !important;margin-top: 450px;" id="img22" />
	</div>
</body>
</html>