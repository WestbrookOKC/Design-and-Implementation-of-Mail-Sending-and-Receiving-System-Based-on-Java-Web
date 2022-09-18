<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="com.mail.common.CommonInfo" %>
<%@ page language="java" import="com.mail.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
MailContent mailContent = null;
Hashtable myValues = null;
Object objInbox =session.getAttribute(CommonInfo.VIEWID_INBOXDETAIL);
Object objSendbox=session.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);;
if(objInbox!=null){
	myValues = (Hashtable)objInbox;
}else if(objSendbox!=null){
	myValues = (Hashtable)objSendbox;
}
if ( myValues != null )
{
    mailContent = (MailContent)myValues.get("mail");
    if ( mailContent == null )
    {
        mailContent = new MailContent();
    }
}
%>

<html>
<head>
  <title>查看邮件</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script language="javascript" src="js/cmnScript.js"></script>
  <script language="JAVASCRIPT" src="js/inboxDetail.js"></script>
  <link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>

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

<%@ include file="inc/cmnPagePart1.jsp" %>

<table border=0 cellpadding=0 cellspacing=0 width=750 align=center>
  <tr>
    <td height=15>&nbsp;</td>
  </tr>
  <tr>
    <td height=30> &nbsp;邮件详情内容：</td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=750 align=center>
        <tr>
          <td width=80 align=right>
            <b>发件人：</b>
          </td>
          <td width=670>
            <%=mailContent.getSender()%>
          </td>
        </tr>
        <tr>
          <td width=80 align=right>
            <b>收件人：</b>
          </td>
          <td width=670>
            <%=mailContent.getReseiver()%>
          </td>
        </tr>
        <tr>
          <td width=80 align=right>
            <b>发送时间：</b>
          </td>
          <td width=670>
            <%=mailContent.getSendTime()%>
          </td>
        </tr>
        <tr>
          <td width=80 align=right>
            <b>邮件标题：</b>
          </td>
          <td width=670>
            <%=mailContent.getSubject()%>
          </td>
        </tr>
        <tr>
          <td width=80 align=right valign=top>
            <b>邮件内容：</b>
          </td>
          <td width=670 height=300 valign=top>
            <pre><%=mailContent.getContent()%></pre>
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
      <button onclick="deleteMail()">删除</button>
      &nbsp;
      <button onclick="replyMail()">回复</button>
      &nbsp;
      <button onclick="returnInbox()">返回一览</button>
    </td>
  </tr>
</table>
<form name="form_main" action="controller/inbox" method="post" >
  <input type="hidden" name="mailOption" value="">
  <input type="hidden" name="sender" value="<%=mailContent.getSender()%>">
  <input type="hidden" name="sendTime" value="<%=mailContent.getSendTime()%>">
  <input type="hidden" name="subject" value="<%=mailContent.getSubject()%>">
  <input type="hidden" name="content" value="<%=mailContent.getContent()%>">
</form>

<%@ include file="inc/cmnPagePart2.jsp" %>