<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>nowayfly</title>
<link rel="shortcur icon" type="image/x-icon"
	href="http://localhost:8090/wuzhi/big/57458.1487598279574.jpg">
<link href="css/global.css" rel="stylesheet" type="text/css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
</head>
<body>
	<div class="topHead">
		<span class="floatLeft marginLeft20"><a
			href="http://localhost:8080/mySSH/wuzhi!showWuzhi">首页</a></span> <span
			class="floatRight marginRight22">hello nowayfly</span>
			<input id="writeDays" type="hidden"  value="${writeDays}"/>
			<input id="years" type="hidden"  value="${years}"/>
			<input id="months" type="hidden"  value="${months}"/>
	</div>
	<div class="marjorDiv ">
		<div class=" picCalendar">
			<div class="floatLeft" id="headPic">
				<img src="http://localhost:8090${relBigPic}" width="100%"
					height="100%" />
			</div>
			<div class="floatClear" id="noclear"></div>
			<div id="calendar" style="margin-left: 2%;">
				<div class="floatLeft" style="margin-left: 1%;">
					<span onclick="lastYear()" class="pointerStyle"><</span> <span
						id="nowYear">2017</span> <span onclick="nextYear()"
						class="pointerStyle">></span>
				</div>
				<div class="floatRight">
					<span onclick="lastMonth()" class="pointerStyle"><</span> <span
						id="nowMonth">03</span>月 <span onclick="nextMonth()"
						class="pointerStyle">></span>
				</div>
				<br>
				<table id="weak">
					<th>一</th>
					<th>二</th>
					<th>三</th>
					<th>四</th>
					<th>五</th>
					<th>六</th>
					<th>日</th>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			<div class="floatClear"></div>
		</div>
		<div id="articleContent">
			<s:iterator value="userArticles">
				<span class="colorGray"> <s:date name="time"
						format="yyyy-MM-dd HH:mm:ss" nice="false" /></span>
				<p>${article }</p>
				<br>
			</s:iterator>
			<span class="floatRight colorGray">--${name }</span>
		</div>
		<div class="floatClear"></div>
	</div>
	<div>备案号：浙ICP备17008428号<div>
	<script src="js/jquery.js"></script>
	<script src="js/calendar.js"></script>
</body>
</html>