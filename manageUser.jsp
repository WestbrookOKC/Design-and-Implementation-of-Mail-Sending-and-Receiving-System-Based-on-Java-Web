<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.mail.bean.MailUser" %>
<%@ page import="com.mail.common.*" %>
<%
Vector<MailUser> vMails = null;
Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_USERLIST);
if ( myValues != null )
{
    vMails = (Vector<MailUser>)myValues.get("users");
    if ( vMails == null ){
        vMails = new Vector<MailUser>();
    }
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JAVASCRIPT" src="js/cmnScript.js"></script>
<script language="javascript" src="js/user.js"></script>
<link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>
<title>管理用户</title>
<style type="text/css">
	#info td{
		text-align: center;
	}

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
<%@ include file="inc/cmnPagePart1.jsp"%>
<form name="form_main" action="controller/address" method="post" >
	<p style="text-align: left;margin-top: 20px;margin-left: 55px;">邮箱系统中总共有<font color="blud"><%=vMails.size() %></font>个用户<span style="margin-left: 683px;">输入用户名&nbsp;<input type="text" name="search" id="search">&nbsp;<button onclick="searchUser();">查找</button></span></p>
	<div>
	<table width=90% border=1 align = center>
	<tr><th><input type="checkbox" name="selectAll" id="selectAll" onclick="seleteAll();"/></th><th>用户名</th><th>真实姓名</th><th>手机号码</th><th>密保问题</th><th>密保答案</th><th>账号状态</th><th>操作</th></tr>
<%
if(vMails.size()>0){
	String sIspaking = null;
	for(int i=0;i<vMails.size();i++){
	String userName = vMails.get(i).getVuserName();
	String realName = vMails.get(i).getVrealName();
	String mobile   = vMails.get(i).getNmobile();
	String question = vMails.get(i).getVquestion();
	String answer   = vMails.get(i).getVanswer();
	int ispaking = vMails.get(i).getIspacking();
	if(ispaking == CommonInfo.IS_HISTORY){
		sIspaking = "锁定";
	}else if(ispaking == CommonInfo.NOT_HISTORY){
		sIspaking = "未锁定";
	}


%>
	<tr id="info"><td align=center ><input type="checkbox" name="Check" id="<%=i %>" value=<%=i %>/></td><td ><a href="javascript:gotoMail('<%=i%>')"><%=userName %></a></td><td><%=realName %></td><td><%=mobile %></td><td><%=question %></td><td><%=answer %></td><td><%=sIspaking %></td><td align="center" width=100><button onclick="paking('<%=userName%>');">锁定</button>&nbsp;<button onclick="removePaking('<%=userName%>');">解锁</button></td></tr>
	<%
	}
	}else{
	
	%>
	<tr>
	<td>
	没有任何注册用户！
	</td>
	</tr>	
	<%
	}
	%>
	</table>
	<input type="hidden" name="theAddress" id="theAddress" value="">
	<input type="hidden" name="removepaking" id="removepaking" value="">
	<input type="hidden" name="addressIndex" id ="address" value="">
	</div>
	</form>

<%@ include file="inc/cmnPagePart2.jsp"%>