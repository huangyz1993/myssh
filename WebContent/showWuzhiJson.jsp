<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>nowayfly</title>
<link rel="shortcur icon" type="image/x-icon"
	href="http://localhost:8090/wuzhi/big/57458.1487598279574.jpg">
<link rel="stylesheet" href="css/global.css" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/page.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
</head>
<body>
	<div class="width100">
		<s:iterator value="userList" status="index">
			<s:if test="(#index.index)%8==0">
				<div class="floatLeft width11 marginLeft3 marginTop1">
					<a href="wuzhi!showUserArticle?userId=${userid}"><img
						src="http://localhost:8090${relPic}" width="100%" /></a>
				</div>
			</s:if>
			<s:elseif test="(#index.index+1)%8!=0">
				<div class="floatLeft width11 marginLeft1 marginTop1">
					<a href="wuzhi!showUserArticle?userId=${userid}"><img
						src="http://localhost:8090${relPic}" width="100%" /></a>
				</div>
			</s:elseif>
			<s:else>
				<div class="floatLeft width11 marginLeft1 marginTop1">
					<a href="wuzhi!showUserArticle?userId=${userid}"><img
						src="http://localhost:8090${relPic}" width="100%" /></a>
				</div>
				<div class="floatClear"></div>
			</s:else>
			<s:if test="#index.isLast()">
				<div class="floatClear"></div>
			</s:if>
		</s:iterator>
	</div>
	<div class="floatRight marginRight2">
	    <!-- 获取id的第一种方法 -->
		<%-- <span id="firstPage" onclick="choosePage(this.id)">首页</span> --%> 
		<span id="firstPage">首页</span>
		<span id="previousPage">上一页</span>
		<%-- <span>
		<s:iterator value="new int[pageNum]" status="i">
     		<s:property value="#i.index+1"/>
   		</s:iterator>
   		</span> --%>
		<span id="nextPage">下一页</span>
		<span id="lastPage">末页</span>
	</div>
	<div class="marjorDiv"
		style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
		备案号：浙ICP备17008428号
		</div>
		
		<script type="text/javascript">
		allPage=${pageNum};
		</script>
</body>
</html>