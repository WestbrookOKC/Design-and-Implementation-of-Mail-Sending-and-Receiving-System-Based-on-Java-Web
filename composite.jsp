<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="com.mail.common.CommonInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String sReceiver = "";
String sSubject = "";
String sContent = "";
String sSaveTime = "";
String sAffix = "";
//String sAffixName = "";
Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);
if ( myValues != null )
{
    sReceiver = (String)myValues.get("receiver");
    if ( sReceiver == null )
    {
        sReceiver = "";
    }
    sSubject = (String)myValues.get("subject");
    if ( sSubject == null )
    {
        sSubject = "";
    }
    sContent = (String)myValues.get("content");
    if ( sContent == null )
    {
        sContent = "";
    }
    
   session.setAttribute("affix",sAffix);
 
   
    sSaveTime = (String)myValues.get("saveTime");
    if ( sSaveTime == null )
    {
        sSaveTime = "";
    }

}
%>

<html>
<head>
  <title>写邮件</title>
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <script language="JAVASCRIPT" src="js/composite.js"></script>
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

<form name="form_main" action="controller/composite" method="post" encType="multipart/form-data
      onsubmit="return checkInput();" onreset="resetForm()">

<table border=0 cellpadding=0 cellspacing=0 width=750 align=center>
  <tr>
    <td height=15>&nbsp;</td>
  </tr>
  <tr>
    <td height=30>请输入邮件内容：</td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=750 align=center>
        <tr>
          <td width=200 align=right>
            <b>收件人：</b>
          </td>
          <td width=450>
            <input type="text" name="receiver" size=80 maxlength=40 value="<%=sReceiver%>"></input>
          </td>
        </tr>
        <tr>
          <td width=200 align=right>
            <b>主题：</b>
          </td>
          <td width=450>
            <input type="text" name="subject" size=80 maxlength=40 value="<%=sSubject%>"></input>
          </td>
        </tr>
        <tr>
          <td width=200 align=right>
            <b>附件：</b>
          </td>
          <td width=450>
            <input type="file" name="affix" size=80 maxlength=40></input>
          </td>
        <tr>
          <td width=200 align=right valign=top>
            <b>内容：</b>
          </td>
          <td width=450 height=300 valign=top>
            <textarea name="content" cols="91" rows="24"><%=sContent%></textarea>
          </td>
        </tr>
        <tr>
          <td width=200 align=right valign=top>
            <b>时间：</b>
          </td>
          <td width=450>
            <%=sSaveTime%>&nbsp;
          </td>
        </tr>

      </table>
    </td>
  </tr>
  <tr>
    <td height=5>&nbsp;</td>
  </tr>
    <tr>
    <td align=center>
      <button onclick="sendMail()">发送</button>
      &nbsp;
      <button onclick="saveMail()">保存</button>
      &nbsp;
      <input type="reset" name="reset" value="重置"/> 
    </td>
  </tr>
</table>
  <input type="hidden" name="mailOption" value="">
  <input type="hidden" name="saveTime" value="<%=sSaveTime%>">
</form>

<%@ include file="inc/cmnPagePart2.jsp" %>