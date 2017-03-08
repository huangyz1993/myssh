<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Love</title>
<link rel="shortcur icon" type="image/x-icon"
	href="/wuzhi/big/57458.1487598279574.jpg">
<style type="text/css">
img {
	width: 25px;
	border-radius: 50%;
	border: 0px;
	float: left;
	margin-left: 1px;
}

.hideImg {
	display: none;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
	<div style='width: 100%; height: auto; margin: 0px; padding: 0px;'>
		<s:iterator value="userList" status='userIndex'>
			<s:if test='#userIndex.index==0'>
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 105px;" />
			</s:if>
			<s:elseif test="#userIndex.index==2">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 209px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>3&&#userIndex.index<20">
			</s:elseif>

			<s:elseif test="#userIndex.index==20">
				<img src="http://localhost:8090${relPic}" style="margin-left: 79px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index==24">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 157px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>27&&#userIndex.index<40">
			</s:elseif>

			<s:elseif test="#userIndex.index==40">
				<img src="http://localhost:8090${relPic}" style="margin-left: 53px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index==46">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 105px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>51&&#userIndex.index<60">
			</s:elseif>

			<s:elseif test="#userIndex.index==60">
				<img src="http://localhost:8090${relPic}" style="margin-left: 27px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index==68">
				<img src="http://localhost:8090${relPic}" style="margin-left: 53px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>75&&#userIndex.index<80">
			</s:elseif>


			<s:elseif test="#userIndex.index>79&&#userIndex.index<100">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>

			<s:elseif test="#userIndex.index==100">
				<img src="http://localhost:8090${relPic}" style="margin-left: 27px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>100&&#userIndex.index<118">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>117&&#userIndex.index<120">
			</s:elseif>

			<s:elseif test="#userIndex.index==120">
				<img src="http://localhost:8090${relPic}" style="margin-left: 53px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>120&&#userIndex.index<136">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>135&&#userIndex.index<140">
			</s:elseif>

			<s:elseif test="#userIndex.index==140">
				<img src="http://localhost:8090${relPic}" style="margin-left: 79px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>140&&#userIndex.index<154">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>153&&#userIndex.index<160">
			</s:elseif>

			<s:elseif test="#userIndex.index==160">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 105px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>160&&#userIndex.index<172">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>171&&#userIndex.index<180">
			</s:elseif>

			<s:elseif test="#userIndex.index==180">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 131px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>180&&#userIndex.index<190">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>189&&#userIndex.index<200">
			</s:elseif>

			<s:elseif test="#userIndex.index==200">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 157px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>200&&#userIndex.index<208">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>207&&#userIndex.index<220">
			</s:elseif>

			<s:elseif test="#userIndex.index==220">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 183px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>220&&#userIndex.index<226">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>225&&#userIndex.index<240">
			</s:elseif>

			<s:elseif test="#userIndex.index==240">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 209px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>240&&#userIndex.index<244">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>243&&#userIndex.index<260">
			</s:elseif>

			<s:elseif test="#userIndex.index==260">
				<img src="http://localhost:8090${relPic}"
					style="margin-left: 235px;" />
			</s:elseif>
			<s:elseif test="#userIndex.index>260&&#userIndex.index<262">
				<img src="http://localhost:8090${relPic}" />
			</s:elseif>
			<s:elseif test="#userIndex.index>261&&#userIndex.index<280">
			</s:elseif>

			<s:else>
				<img src="http://localhost:8090${relPic}" />
			</s:else>
			<s:if test='(#userIndex.index+1)%20==0'>
				<br>
				<br>
			</s:if>
		</s:iterator>
	</div>
	<div>备案号：浙ICP备17008428号<div>
</body>
</html>