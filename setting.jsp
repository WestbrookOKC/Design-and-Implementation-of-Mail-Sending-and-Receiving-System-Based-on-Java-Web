<%@page import="com.mail.common.CommonInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page language="java" import="java.util.*" %>

<%
String sImapIp = "";
String sImapUser = "";
String sImapPass = "";
String sSmtpIp =  "";
String sSmtpUser = "";
String sSmtpPass = "";
String sAddress = "";
Hashtable myValues=(Hashtable)session.getAttribute(CommonInfo.VIEWID_SETTING);
if(myValues != null){
	sImapIp = (String)myValues.get("imapIp");
	if(sImapIp==null){
		sImapIp="";
	}
	sImapUser = (String)myValues.get("imapUser");
	if(sImapUser == null){
		sImapUser="";
	}
	sImapPass = (String)myValues.get("imapPass");
	if(sImapPass==null){
		sImapPass="";
	}
	sSmtpIp = (String)myValues.get("smtpIp");
	if(sSmtpIp==null){
		sSmtpIp="";
	}
	sSmtpUser = (String)myValues.get("smtpUser");
	if(sSmtpUser==null){
		sSmtpUser = "";
	}
	sSmtpPass = (String)myValues.get("smtpPass");
	if(sSmtpPass==null){
		sSmtpPass = "";
	}
	sAddress = (String)myValues.get("address");
	if(sAddress==null){
		sAddress="";
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <script language="JAVASCRIPT" src="js/setting.js"></script>
  <link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>
	<script language="javascript">
		<% 
			if(session.getAttribute("errMsg") != null){
		%>
				var sErrMsg = <%=session.getAttribute("errMsg")%>
				alsert(sErrMsg);
		<%
			}else {
		%>
				var sErrMsg = "";
		<%
			}
		%>
	</script>
<title>邮箱设置</title>
</head>
<%@ include file="inc/cmnPagePart1.jsp" %>
<form name="form_main" action="controller/setting" method="post" 
      onsubmit="return checkInput();" onreset="resetForm()">

<table border=0 cellpadding=0 cellspacing=0 width=750 align=center>
  <tr>
    <td height=15>&nbsp;</td>
  </tr>
  <tr>
    <td height=30><h3>请输入邮箱设置参数</h3></td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=750 align=center>
        <tr>
          <td width=150 align=right>POP邮件服务器地址：</td>
          <td align=left>
            <input type="text" name="imapIp" size=40 maxlength=40 value="<%=sImapIp%>"></input>
          </td>
        </tr>
        <tr>
          <td width=150 align=right>POP用户名：</td>
          <td align=left>
            <input type="text" name="imapUser" size=40 maxlength=40 value="<%=sImapUser%>"></input>
          </td>
        </tr>
        <tr>
          <td width=150 align=right>POP密码：</td>
          <td align=left>
            <input type="password" name="imapPass" size=40 maxlength=40 value="<%=sImapPass%>"></input>
          </td>
        </tr>
        <tr>
          <td width=150 align=right>SMTP邮件服务器地址：</td>
          <td align=left>
            <input type="text" name="smtpIp" size=40 maxlength=40 value="<%=sSmtpIp%>"></input>
          </td>
        </tr>
        <tr>
          <td width=150 align=right>SMTP用户名：</td>
          <td align=left>
            <input type="text" name="smtpUser" size=40 maxlength=40 value="<%=sSmtpUser%>"></input>
          </td>
        </tr>
        <tr>
          <td width=150 align=right>SMTP密码：</td>
          <td align=left>
            <input type="password" name="smtpPass" size=40 maxlength=40 value="<%=sSmtpPass%>"></input>
          </td>
        </tr>
        <tr>
          <td width=150 align=right>邮件地址：</td>
          <td align=left>
            <input type="text" name="address" size=40 maxlength=40 value="<%=sAddress%>"></input>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height=20>&nbsp;</td>
  </tr>
  <tr>
    <td align=center>
      <input type="submit" value="提交"></input>
      &nbsp;
      <input type="reset" value="重置"></input>
    </td>
  </tr>
</table>

</form>

<%@ include file="inc/cmnPagePart2.jsp" %>