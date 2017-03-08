<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>nowayfly</title>
<link rel="shortcur icon" type="image/x-icon" href="/wuzhi/big/57458.1487598279574.jpg">
</head>
<body>

</body>
<s:iterator value="articleWithNoUser">
	<p><s:date name="time" format="yyyy-MM-dd HH:mm:ss" nice="false" /> ${userid }</p>
	<p>${article }</p>

	<br>
</s:iterator>
<div>备案号：浙ICP备17008428号<div>
</html>