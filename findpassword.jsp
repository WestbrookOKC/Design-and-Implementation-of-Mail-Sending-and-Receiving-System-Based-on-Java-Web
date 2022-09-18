<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.mail.common.CommonInfo" %>
<%
	String question = null;
	String password = null;
	Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_FINDPASSWORD);
    if(myValues != null){
		question = (String)myValues.get("question");
		password = (String)myValues.get("password");
	}else{
		question = "";
		password = "";
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JAVASCRIPT" src="js/cmnScript.js"></script>
<script language="javascript" src="js/findpassword.js"></script>
<title>找回密码</title>
<style type="text/css">
	#findpassword{
		margin-top:140px;
		margin-right:500px;
		text-align: right;
	}
</style>
<script language="JAVASCRIPT">
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
</head>
<body>
	<div style="margin-left:50px; font-style: oblique;color: #13A7FD;"><h1>欢迎使用找回密码:</h1></div>
	<div><HR style="FILTER: alpha(opacity=100,finishopacity=0,style=1)" width="92%" color=#987cb9 SIZE=3></div>
	<div id="findpassword">
	<form name="form_main" action="controller/findpassword" method="post">
	<div>
	<label for="inputUserName">您注册时用的手机号码:</label>
	<input type="text" size=30 id="userName_Mobile" name="userName_Mobile"></input>
	<button value="获取问题" onclick="findQuestion();">获取验证码</button>
	</div>
	<br/>
	<br/>
	<div>
	<label for="answer" >请输入您收到的验证码:</label>
	<input type="text" size=32 id="answer" name="answer"></input>
	<button value="提交答案" onclick="findAnswer();">找回密码</button>
	</div><br/>
	
	<br/>
	<div style="margin: 30px;padding: 30px;">
<!-- <span style="margin: 100px;"> <a href="#" onclick="">给管理员留言</a></span> -->	
	<span><a href="#" onclick="login();">返回登录</a></span>
	</div><br/>
</form>
</div>
</body>
</html>