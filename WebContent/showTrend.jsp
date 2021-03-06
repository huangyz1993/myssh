<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>nowayfly</title>
<link rel="shortcur icon" type="image/x-icon" href="/wuzhi/big/57458.1487598279574.jpg">
<script type="text/javascript" src="js/echarts.min.js"></script>
</head>
<body style="height: 100%; margin: 0">
	<div id="container" style="height: 560px;"></div>
	<div>备案号：浙ICP备17008428号<div>
	<script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		option = {
			title : {
				text : '折线图堆叠'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '关键字' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : [ <s:iterator value='numberCopy' id='item' status='xx'><s:if test='#xx.index<30'>'${item[1]}',</s:if></s:iterator> ]
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '关键字',
				type : 'line',
				stack : '总量',
				data : [ <s:iterator value='number' id='item' status='xx'><s:if test='#xx.index<30'>'${item[0]}',</s:if></s:iterator> ]
			} ]
		};
		;
		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}
	</script>
</body>
</html>