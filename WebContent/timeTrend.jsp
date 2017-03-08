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
				data : [ '时间' ]
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
				data : ['0点','1点','2点','3点','4点','5点','6点','7点','8点','9点','10点','11点','12点','13点','14点','15点','16点','17点','18点','19点','20点','21点','22点','23点']
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '时间',
				type : 'line',
				stack : '总量',
				data : [ <s:iterator value='timeNumber' id='item' status='xx'>'${item}',</s:iterator>  ]
			} ]
		};
		;
		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}
	</script>
</body>
</html>