<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误啦</title>
</head>
<body>
	<p>错误信息：</p>
	<%= exception.getStackTrace() %>
	<p><a href="/webmails/index.jsp">返回主页</a></p>
</body>
</html>