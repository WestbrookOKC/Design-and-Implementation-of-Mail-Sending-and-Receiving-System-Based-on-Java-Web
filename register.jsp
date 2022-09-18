<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head><title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="js/cmnScript.js"></script>
<script language="javascript" type="text/javascript" src="js/register.js"></script>
<script language="javascript" type="text/javascript" src="js/service.js"></script>
<link rel="stylesheet" type="text/css" href="css\about.css" TITLE="common"></link>
<style type="text/css">
<!--
body {
font-family: Arial,  Helvetica, sans-serif;
font-size:12px;
color:#666666;
background:url("images/registerbg.jpg");
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
p {
color:#1E7ACE;
margin:4px;
}
input, select,textarea {
padding:3px;
margin:2px;
font-size:11px;
}
.buttom{
cursor:pointer;
padding:1px  10px;
font-size:12px;
border:1px #1E7ACE solid;
background:#D0F0FF;
}
#formwrapper {
width:490px;
margin-left:750px;
margin-top:60px;
padding:20px;
text-align:left;
border:1px #1E7ACE solid;
}
fieldset {
padding:10px;
margin-top:5px;
border:1px solid #1E7ACE;
}
fieldset legend  {
color:#1E7ACE;
font-weight:bold;
padding:3px  20px 3px 20px;
border:1px solid #1E7ACE;  
background:#fff;
}
fieldset label {
float:left;
width:120px;
text-align:right;
padding:4px;
margin:1px;
}
fieldset div {
clear:left;
margin-bottom:2px;
}
.enter{  margin-left:130px;padding-left:10px;}
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
<form name="form_main" action="controller/register" method="post" onsubmit="return checkRegisterInput();">
<fieldset>
   <legend>用户注册</legend>
   <p><strong>您的信息不会被公布出去,但是必须填写.</strong> 在您注册之前请先认真阅读服务条款.</p>
<div>
<!--<select name="realm"><option>@feng.com</option><option>@chenup.com</option></select>       -->
   <label for="Email">用户账号</label>
   <input type="text" name="userName"  id="Email" value=""  size="31" maxlength="15" /><font color=red>*</font><br />  
</div>  
<div>
   <label for="Name">真实姓名</label>
   <input type="text" name="realName" id="Name" value="" size="31"  maxlength="30" /><font color=red>*</font><br/>  
</div>
<div>
   <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
   <input type="password" name="password" id="password1" size="31" maxlength="15" /><font color=red>*</font><br />
</div>
   <div>
   <label for="confirm_password">重复密码</label>
   <input type="password" name="confirm_password" id="password2" size="31" maxlength="15" /><font color=red>*</font><br />
</div>
<div>
<label for="question">密码保护</label>
<select style="width: 184px;" name ="question" id="question"><option>你最喜欢的颜色是什么?</option><option>你最喜欢的人的名字?</option><option>你最喜欢的数字?</option></select>
</div>
<div>
<label for="answer">密保答案</label>
<input type="text" name="answer" id="answer" size=31 maxlength=30></input>
</div>
<div>
	<label for="mobile">手机号码</label>
	<input type="text" name="mobile" id="mobile" value="" size="31"  maxlength="11" /> 
</div>
<div>
	<label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
	<input type="radio" id="man" value="男" name="xb_one"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="woman" value="女" name="xb_one" />女
	 &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="secret" value="保密" name="xb_one" />保密
</div>
<div>
	<label for="birthday">出生日期</label>
	<input name="brithday" type="text" size="31" class="Wdate" value="" onClick="WdatePicker()"/> 
</div>
   <div>
   <label for="AgreeToTerms">服务条款</label>
   <input type="checkbox" name="AgreeToTerms" id="AgreeToTerms" value="1" /> 
   <a href="#"  title="<%@ include file="inc/service1.inc"%>">同意条款？</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="login()">已有账号？</a></div>    
   <div class="enter">
   <input name="create791"  type="submit" class="buttom" id="Submit1" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input name="Submit" type="reset" class="buttom" value="重置" />
</div>



   <p><strong> 
   * 这些条款可能在未经您同意的时候进行修改.</strong></p>  
</fieldset>
</form>
</div>
  <%@ include file="inc/about.inc" %>
</body>
</html>