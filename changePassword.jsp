<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更改密码</title>
  <script language="JAVASCRIPT" src="js/changepassword.js"></script>
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>
<script language="javascript" type="text/javascript">
<%
    if ( session.getAttribute("errMsg") != null )
    {
%>
        var sErrMsg = "<%=session.getAttribute("errMsg")%>";
<%
    }else{
%>
        var sErrMsg = "";
<%
    }
%>
  </script>
<style type="text/css">
  #changepassword{
  	float:left;
  	width:400px;
  	border-color: red;
  	border-width: 2px;
  	padding-left: 420px;
  	padding-top: 100px;
  
  }
  
</style>
</head>
<%@ include file="inc/cmnPagePart1.jsp"%>
<div id="changepassword">
	<form name="form_main" action="controller/changepassword" method="post" onsubmit="return checkTheInput();">
		<div class="info">
		<p>请输入旧密码:<input type="password" size="30" name="oldpassword"></input></p>
		<p>请输入新密码:<input type="password" size="30" name="newpassword"></input></p>
		<p>再输入新密码:<input type="password" size="30" name="confirm_newpassword"></input></p>
		<p style="padding-left: 100px;padding-top: 5px;margin-left: 10px;">
		<input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置"/></p>
		</div>
	</form>
</div>
<%@ include file="inc/cmnPagePart2.jsp"%>