<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>nowayfly</title>
<link href="http://localhost:8090/wuzhi/big/57458.1487598279574.jpg"
	rel="shortcut icon" type="image/x-icon">
<link href="css/global.css" rel="stylesheet" type="text/css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<script src="js/echarts.min.js">
	
</script>
</head>
<body>

	<div id="container" class="floatLeft marginLeft3 marginTop1"
		style="height: 300px; width: 45%;"></div>
	<div id="Monday" class="floatLeft marginLeft3 marginTop1" 
		style="height: 300px; width: 45%;"></div>
	<div id="Tuesday"class="floatLeft marginLeft3 marginTop1"
		style="height: 300px; width: 45%;"></div>
	<div id="Wednesday" class="floatLeft marginLeft3 marginTop1"
		style="height: 300px; width: 45%;"></div>
	<div id="Thursday" class="floatLeft marginLeft3 marginTop1"
		style="height: 300px; width: 45%;"></div>
	<div id="Friday" class="floatLeft marginLeft3 marginTop1"
		style="height: 300px; width: 45%;"></div>
	<div id="Saturday" class="floatLeft marginLeft3 marginTop1" 
		style="height: 300px; width: 45%;"></div>
	<div id="Sunday" class="floatLeft marginLeft3 marginTop1"
		style="height: 300px; width: 45%;"></div>
	<script>
		var dom = document.getElementById("container");
		var Mondaydom = document.getElementById("Monday");
		var Tuesdaydom = document.getElementById("Tuesday");
		var Wednesdaydom = document.getElementById("Wednesday");
		var Thursdaydom = document.getElementById("Thursday");
		var Fridaydom = document.getElementById("Friday");
		var Saturdaydom = document.getElementById("Saturday");
		var Sundaydom = document.getElementById("Sunday");
		var myChart = echarts.init(dom);
		var MondayChart = echarts.init(Mondaydom);
		var TuesdaydomChart = echarts.init(Tuesdaydom);
		var WednesChart = echarts.init(Wednesdaydom);
		var ThursdayChart = echarts.init(Thursdaydom);
		var FridayChart = echarts.init(Fridaydom);
		var SaturdayChart = echarts.init(Saturdaydom);
		var SundayChart = echarts.init(Sundaydom);
		var dataAxis = [ '星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日' ];
		var data = [<s:iterator value='array' id='item' >'${item}',</s:iterator>];
		var Mondaydata = [<s:iterator value='arrayMon' id='item' >'${item}',</s:iterator>];
		var Tuesdaydata = [<s:iterator value='arrayTue' id='item' >'${item}',</s:iterator> ];
		var Wednesdaydata = [<s:iterator value='arrayWed' id='item' >'${item}',</s:iterator>];
		var Thursdaydata = [<s:iterator value='arrayThu' id='item' >'${item}',</s:iterator>];
		var Fridaydata = [ <s:iterator value='arrayFri' id='item' >'${item}',</s:iterator>];
		var Saturdaydata = [<s:iterator value='arraySat' id='item' >'${item}',</s:iterator>];
		var Sundaydata = [<s:iterator value='arraySun' id='item' >'${item}',</s:iterator>];
		var yMax = 500;
		var dataShadow = [];

		for ( var i = 0; i < data.length; i++) {
			dataShadow.push(yMax);
		}

		//myChart.setOption(returnOption(data, dataShadow), true);
		myChart.setOption(returnAxisOptionWeak(data, "星期分布"), true);
		MondayChart.setOption(returnAxisOption(Mondaydata, "周一分布"), true);
		TuesdaydomChart.setOption(returnAxisOption(Tuesdaydata, "周二分布"), true);
		WednesChart.setOption(returnAxisOption(Wednesdaydata, "周三分布"), true);
		ThursdayChart.setOption(returnAxisOption(Thursdaydata, "周四分布"), true);
		FridayChart.setOption(returnAxisOption(Fridaydata, "周五分布"), true);
		SaturdayChart.setOption(returnAxisOption(Saturdaydata, "周六分布"), true);
		SundayChart.setOption(returnAxisOption(Sundaydata, "周日分布"), true);

		// Enable data zoom when user click bar.
		var zoomSize = 6;

		function returnOption(data, dataShadow) {
			option = {
				title : {
					text : '星期分布'
				},
				xAxis : {
					data : dataAxis,
					axisLabel : {
						inside : true,
						textStyle : {
							color : '#fff'
						}
					},
					axisTick : {
						show : false
					},
					axisLine : {
						show : false
					},
					z : 10
				},
				yAxis : {
					axisLine : {
						show : false
					},
					axisTick : {
						show : false
					},
					axisLabel : {
						textStyle : {
							color : '#999'
						}
					}
				},
				dataZoom : [ {
					type : 'inside'
				} ],
				series : [
						{ // For shadow
							type : 'bar',
							itemStyle : {
								normal : {
									color : 'rgba(0,0,0,0.05)'
								}
							},
							barGap : '-100%',
							barCategoryGap : '45%',
							data : dataShadow,
							animation : false
						},
						{
							type : 'bar',
							itemStyle : {
								normal : {
									color : new echarts.graphic.LinearGradient(
											0, 0, 0, 1, [ {
												offset : 0,
												color : '#83bff6'
											}, {
												offset : 0.5,
												color : '#188df0'
											}, {
												offset : 1,
												color : '#188df0'
											} ])
								},
								emphasis : {
									color : new echarts.graphic.LinearGradient(
											0, 0, 0, 1, [ {
												offset : 0,
												color : '#2378f7'
											}, {
												offset : 0.7,
												color : '#2378f7'
											}, {
												offset : 1,
												color : '#83bff6'
											} ])
								}
							},
							data : data
						} ]
			};
			return option;

		}

		function returnAxisOption(data, text) {
			option = {
				title : {
					text : text
				},
				tooltip : {
					trigger : 'axis'
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : [ '0点', '1点', '2点', '3点', '4点', '5点', '6点', '7点',
							'8点', '9点', '10点', '11点', '12点', '13点', '14点',
							'15点', '16点', '17点', '18点', '19点', '20点', '21点',
							'22点', '23点' ]
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					name : '数量',
					type : 'line',
					stack : '总量',
					data : data
				} ]
			}
			return option;
		}
		
		function returnAxisOptionWeak(data, text) {
			option = {
				title : {
					text : text
				},
				tooltip : {
					trigger : 'axis'
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : dataAxis
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					name : '数量',
					type : 'line',
					stack : '总量',
					data : data
				} ]
			}
			return option;
		}
	</script>
</body>
</html>
