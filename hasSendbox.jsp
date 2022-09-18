<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="com.mail.common.CommonInfo" %>
<%@ page language="java" import="com.mail.bean.MailContent" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Vector vMails = null;
Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_HASSENDLIST);
if ( myValues != null )
{
    vMails = (Vector)myValues.get("mail");
    if ( vMails == null )
    {
        vMails = new Vector();
    }
}
%>

<html>
<head>
  <title>已发送</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <script language="JAVASCRIPT" src="js/sendbox.js"></script>
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
    <td height=30>发件箱中共有 <font color=blue><%=vMails.size()%></font> 封邮件。</td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=750 align=center>
        <tr>
          <td width=130>
            <b>收件人</b>
          </td>
          <td width=400>
            <b>主题</b>
          </td>
          <td width=120>
            <b>发送时间</b>
          </td>
        </tr>
<%
    if ( vMails.size() > 0 )
    {
        for ( int i=0; i<vMails.size(); i++ )
        {
            MailContent mailContent = (MailContent)vMails.get(i);
            String sReceiver = mailContent.getReseiver();
            String sSubject = mailContent.getSubject();
            String sSaveTime = mailContent.getSendTime();
%>
        <tr>
          <td>
            <%=sReceiver%>
          </td>
          <td>
            <a href="javascript:gotoMail('<%=i%>')"><%=sSubject%></a>
          </td>
          <td>
            <%=sSaveTime%>
          </td>
        </tr>
<%            
        }
    }
    else
    {
%>
        <tr>
          <td colspan=3>
            发件箱中没有任何邮件！
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
<form name="form_main" action="controller/hassendbox" method="post" >
  <input type="hidden" name="mailIndex" value="">
</form>

<%@ include file="inc/cmnPagePart2.jsp" %>