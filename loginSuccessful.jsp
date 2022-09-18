<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String sUserName = (String)session.getAttribute("username");
if(sUserName==null){
	response.sendRedirect("/webmails/index.jsp");
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆成功</title>
<style type="text/css">
	#login{
		width:450px;
		margin-left:420px;
		margin-top:100px;
		padding:20px;
		text-align:left;
		border:0px #1E7ACE solid;
	}


</style>
</head>
<body>
<div id="login">
	<form name=loading>
  <p align=center> <font color="#0066ff" size="2" face="Arial">请悄候,邮件正在加载...</font><br>
    <input type=text name=chart size=46 style="font-family:Arial; font-weight:bolder; color:#0066ff; background-color:#fef4d9; padding:0px; border-style:none;">
    <br>
    <input type=text name=percent size=47 style="color:#0066ff; text-align:center; border-width:medium; border-style:none;">
 <script> 
	var bar=0;
	var line="||";
	var amount="||";
	count();
	function count(){ 
	bar=bar+2;
	amount =amount + line;
	document.loading.chart.value=amount; 
	document.loading.percent.value=bar+"%"; 
	if (bar<99) 
	{setTimeout("count()",100);} 
	else 
	{window.location = "/webmails/controller/inbox";} 
	}
</script>
</p>
</form>
</div>
</body>
</html>