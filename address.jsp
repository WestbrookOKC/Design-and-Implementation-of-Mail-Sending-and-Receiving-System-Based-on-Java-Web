<%--联系人管理页面 --%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="com.mail.common.CommonInfo" %>
<%@ page language="java" import="com.mail.bean.MailAddress" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--通过哈希表获取联系人的信息显示到页面上 --%>
<%
Vector<MailAddress> vAddresses = null;
String sFriendName = "";
String sFriendMail = "";
Hashtable myValues = (Hashtable)session.getAttribute(CommonInfo.VIEWID_ADDRESSLIST);
if ( myValues != null )
{
    vAddresses = (Vector<MailAddress>)myValues.get("addresses");
    if ( vAddresses == null )
    {
        vAddresses = new Vector<MailAddress>();
    }
    
    sFriendName = (String)myValues.get("friendName");
    if ( sFriendName == null )
    {
        sFriendName = "";
    }
    
    sFriendMail = (String)myValues.get("friendMail");
    if ( sFriendMail == null )
    {
        sFriendMail = "";
    }
}
%>

<html>
<head>
  <title>地址簿</title>
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <script language="JAVASCRIPT" src="js/address.js"></script>
  <link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>
<%--如果有错误信息，显示错误信息 --%>
  <script language="JAVASCRIPT">
<%
    if ( session.getAttribute("errMsg") != null ){
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
<form name="form_main" action="controller/address" method="post" >

<table border=0 cellpadding=0 cellspacing=0 width=750 align=center>
  <tr>
    <td height=15>&nbsp;</td>
  </tr>
  <tr>
    <td height=30>共有 <font color=blue><%=vAddresses.size()%></font> 位联系人。</td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=750 align=center>
        <tr>
          <td width=150>
            <b>姓名</b>
          </td>
          <td width=350>
            <b>邮件地址</b>
          </td>
        </tr>
<%
    if ( vAddresses.size() > 0 ){
        for ( int i=0; i<vAddresses.size(); i++ ){
            MailAddress mailAddress = (MailAddress)vAddresses.get(i);
            String sEachFriendName = mailAddress.getVfriendName();
            String sEachFriendMail = mailAddress.getVfriendMail();
%>
        <tr>
          <td>
            <%=sEachFriendName%>
          </td>
          <td>
            <a href="javascript:gotoMail('<%=i%>')"><%=sEachFriendMail%></a>
          </td>
        </tr>
<%            
        }
    }else{
%>
        <tr>
          <td colspan=2>
            没有任何联系人的信息！
          </td>
        </tr>
<%
    }
%>
      </table>
    </td>
  </tr>
  <tr>
    <td height=30>&nbsp;</td>
  </tr>
  <tr>
    <td>添加新联系人：</td>
  </tr>
  <tr>
    <td height=5>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table border=1 bordercolor="#13A7FD" cellpadding=2 cellspacing=0 width=500 align=center>
        <tr>
          <td width=100 align=right>
            <b>姓名：</b>
          </td>
          <td width=400>
            <input type="text" name="friendName" size=40 maxlength=40 value="<%=sFriendName%>"></input>
          </td>
        </tr>
        <tr>
          <td align=right>
            <b>邮件地址：</b>
          </td>
          <td>
            <input type="text" name="friendMail" size=40 maxlength=40 value="<%=sFriendMail%>"></input>
          </td>
        </tr>
        <tr>
          <td colspan="2" align=center>
            <button onclick="return addAddress()">添加</button>
            &nbsp;
            <input type="reset"  value="重写"></input>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
  <input type="hidden" name="addressIndex" value="">
</form>

<%@ include file="inc/cmnPagePart2.jsp" %>