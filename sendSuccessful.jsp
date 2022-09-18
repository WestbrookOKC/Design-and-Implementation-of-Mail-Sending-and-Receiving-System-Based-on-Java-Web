<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>
<style type="text/css"></style>
<title>邮件发送成功</title>
<style type="text/css">
#sendSuccessful{
	text-align:center;
	margin-top: 140px;
}


</style>
</head>
<%@ include file="inc/cmnPagePart1.jsp" %>
	<div id="sendSuccessful">
		<h1>邮件发送成功！</h1>
		<span style="cursor: pointer;background-image: url('img/menuSelect.gif');font-size: 25px;width: 50px;" onclick="gotoPage('composite')">再写一封</span>
	</div>
	
<%@ include file="inc/cmnPagePart2.jsp" %>