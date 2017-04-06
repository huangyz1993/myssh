<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<title>Insert title here</title>
</head>
<body>
	<form action="upload" method="post"
		enctype="multipart/form-data">
		请选择需要上传的文件：<input type="file" id="dofile" name="file" multiple /><br /> <input
			type="submit" id="btnupload" name="btnupload" value="开始上传">
	</form>
</body>
</html>