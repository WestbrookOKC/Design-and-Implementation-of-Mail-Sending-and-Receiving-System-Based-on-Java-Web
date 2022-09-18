<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page language="java" import="java.util.*" %>
<%@page import="com.mail.bean.MailContent"%>
<%@page import="com.mail.common.CommonInfo"%>
<%
Vector<MailContent> vMails = null;
Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_INBOXLIST);
if ( myValues != null )
{
    vMails = (Vector<MailContent>)myValues.get("mails");
    if ( vMails == null ){
        vMails = new Vector<MailContent>();
    }
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JAVASCRIPT" src="js/cmnScript.js"></script>
<script language="JAVASCRIPT" src="js/inbox.js"></script>
<link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>
<title>收件箱</title>
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
<%@ include file="inc/cmnPagePart1.jsp" %>
<table border=0 cellpadding=0 cellspacing=0 width=750 align=center>
  <tr>
    <td height=15>&nbsp;</td>
  </tr>
  <tr>
    <td height=30>你当前共有 <font color=blue><%=vMails.size()%></font> 封邮件。</td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=750 align=center>
        <tr>
          <td width=130>
            <img alt="未读邮件" src="images/unread.gif">&nbsp;&nbsp;
            <b>发件人</b>
          </td>
          <td width=400>
            <b>主题</b>
          </td>
          <td width=120>
            <b>发信时间</b>
          </td>
        </tr>
<%
    if(vMails.size()>0){
        for ( int i=0; i<vMails.size(); i++ )
        {
            MailContent mailContent = (MailContent)vMails.get(i);
            String sFrom = mailContent.getSender();
            String sSubject = mailContent.getSubject();
            String sSendTime = mailContent.getSendTime();
            int sReadFlg = mailContent.getReadFag();
            if ( sReadFlg==0 ){
%>
        <tr>
          <td>
          	<img alt="未读邮件" src="images/new.gif">&nbsp;&nbsp;
            <b><%=sFrom%></b>
          </td>
          <td>
            <b><a href="javascript:gotoMail('<%=i%>')"><%=sSubject%></a></b>
          </td>
          <td>
            <%=sSendTime%>
          </td>
        </tr>
<%            
            }else{
%>
        <tr>
          <td>
         	<img alt="已读邮件" src="images/read.gif">&nbsp;&nbsp;
            <%=sFrom%>
          </td>
          <td>
            <a href="javascript:gotoMail('<%=i%>')"><%=sSubject%></a>
          </td>
          <td>
            <%=sSendTime%>
          </td>
        </tr>
<%            

            }
        }
    }else{
%>
        <tr>
          <td colspan=3>
            没有任何邮件！
          </td>
        </tr>
<%
    }
%>
      </table>
    </td>
  </tr>
  <tr>
    <td height=20>&nbsp;</td>
  </tr>
</table>
<form name="form_main" action="controller/inbox" method="post" >
  <input type="hidden" name="mailIndex" value="">
</form>

<%@ include file="inc/cmnPagePart2.jsp" %>