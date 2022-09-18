<%@page contentType="text/html;charset=utf-8" %>

<html>
<head>
  <title>注册成功</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script language="JAVASCRIPT" src="js/cmnScript.js"></script>
  <link rel="stylesheet" type="text/css" href="css\cmnStyle.css" TITLE="common"></link>

  <script language="JAVASCRIPT">
    window.onload = startCount;
    var timeLeft = 5;
    var timeLeftSpan;
    var timeId;
    function startCount()
    {
        timeLeftSpan = document.getElementById("timeDisp");
        timeCount();
        timeId = setInterval("timeCount();",1000);
    }
    function clearCount()
    {
        clearInterval( timeId );
    }
    function timeCount()
    {
        timeLeftSpan.innerHTML = "&nbsp;<font color=blue>" + timeLeft + "</font>&nbsp;";
        if ( timeLeft == 0 )
        {
            clearInterval( timeId );
            window.location.href = "controller/setting";
        }
        timeLeft--;
    }
  </script>
</head>

<body>
<table border=0 cellpadding=0 cellspacing=0 height=100>
  <tr>
    <td>
      &nbsp;
    </td>
  </tr>
</table>
<table border=0 cellpadding=0 cellspacing=0 bgcolor="#000000" align=center>
  <tr>
    <td>
      <table border=0 cellpadding=0 cellspacing=0 bgcolor="#ffffff">
        <tr>
          <td width=400>
            <h1>注册成功！</h1>
          </td>
        </tr>
        <tr>
          <td height=5></td>
        </tr>
        <tr>
          <td align=center>
            系统<span id="timeDisp"></span>秒钟后将自动跳转到
            <a href="controller/setting" onclick="clearCount()"><img border=0 src="img/mailsetting.gif"/></a>
            页面。
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>