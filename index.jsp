<%@page import="com.mail.common.CommonInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8" errorPage="error.jsp"%>
<%@page language="java" import="java.util.*" %>
<%
String sUsername = "";
Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_LOGIN);
if ( myValues != null )
{
    sUsername = (String)myValues.get("username");
    if ( sUsername == null )
    {
        sUsername = "";
    }
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JAVASCRIPT" src="js/cmnScript.js"></script>
<script language="JAVASCRIPT" src="js/login.js"></script>
<link rel="stylesheet" type="text/css" href="css\about.css" TITLE="common"></link>
<title>登陆</title>
<style type="text/css">
<!--
body {
font-family: Arial,  Helvetica, sans-serif;
font-size:12px;
color:#666666;
text-align:center;
}
* {
margin:0;
padding:0;
}
a {
color:#1E7ACE;
text-decoration:none;  
}
a:hover  {
color:#000;
text-decoration:underline;
}
h3 {
font-size:14px;
font-weight:bold;
}
pre,p {
color:#1E7ACE;
margin:4px;
}
input, select,textarea {
padding:2px;
margin:6px;
font-size:11px;
text-align:left;
}

.buttom{
cursor:pointer;
padding:1px  10px;
font-size:12px;
border:1px #1E7ACE solid;
background:#D0F0FF;
}
#formwrapper {
width:450px;
margin-left:780px;
margin-top:80px;
padding:20px;
text-align:left;
border:1px #1E7ACE solid;
}
fieldset {
padding:10px;
margin-top:5px;
border:1px solid #1E7ACE;
}

#login{
cursor:pointer;
color:#1E7ACE;
font-weight:bold;
padding:3px  20px 3px 20px;
border:1px solid #1E7ACE;  
background:#fff;
}

#mobileLogin{
display:inline;
cursor:pointer;
color:#1E7ACE;
font-weight:bold;
padding:3px  20px 3px 20px;
border:1px solid #1E7ACE;  
background:#fff;
}

fieldset legend a:hover{
color:#1E7ACE;
font-weight:bold;
padding:2px  3px 0px 3px;
border:1px solid #1E7ACE;  
background:#3cf;
}

fieldset span {
float:left;
text-align:left;
font-size:15px;
margin-left:100px;
}

fieldset div {
clear:left;
padding:5px;
margin-bottom:2px;
}
#botton{float:left; text-align:left; margin-left: 60px;}
.enter{  text-align:center;}
.clear {
clear:both;
}
-->
</style>
 <script language="JAVASCRIPT">
<%
    if ( session.getAttribute("errMsg") != null )
    {
%>
        var sErrMsg = "<%=session.getAttribute("errMsg")%>";
<%
    }
    else
    {
%>
        var sErrMsg = "";
<%
    }
%>
  </script>
</head>
<body>
<div id="formwrapper">
<form name="form_main" action="controller/Login" method="post" onreset="resetForm()">
<fieldset>
   	<legend id="login"><a>用户登录</a></legend>
   <div>
     <span>	用户名
     <input type="text" name="username" id="Name" size="32" maxlength="30" value="<%=sUsername %>"/></span><span id="usernameNotNull"></span><br/>
   </div><br/>
   <div>
     <span>密&nbsp;&nbsp;&nbsp;&nbsp;码
     <input type="password" name="password" id="password" size="32" maxlength="15" /></span><span id="passwordNotNull"></span><br/>
   </div>
   <div class="cookiechk">
     <span><input type="checkbox" name="CookieYN" id="CookieYN" onclick="writeCookie();"></input> <a id="isSelete" href="#" title="选择是否记录您的信息" onclick="isSelect();">记住我</a><select id="cookielive" onchange="writeCookie();"><option>一天</option><option>一周</option><option>一月</option></select>
     </span><span id="codevalue" style="cursor: pointer;background-color: #ffffff;margin-left: 2px;margin-top: 8px;" onclick="add();"></span><input type="text" size=3 name="code"/><span id="codeNotNull"></span>
   </div>  
   <div id="botton"><span><input name="login" type="button" class="buttom"  value="登录" onclick="return checkInput();"></input></span><input name="reset" type="reset" class="buttom" value="重置"/></div> 
   <div class="forgotpass"><span><a href="../webmails/findpassword.jsp">忘记密码</a></span><span><a href="#" onclick="registerNewUser()">注册账户</a></span></div>  
</fieldset>
</form><br />
</div>
<%@ include file="inc/about.inc" %>
</body>
</html>