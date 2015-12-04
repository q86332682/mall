<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<%@ include file="head.jsp"%>
<div id="box">
<div id="mid">
    <div id="denglu">
      <form action="${pageContext.request.contextPath}/registerOrLogin/login.action" method="post">
      <table width="100%" height="94" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="70" height="35" align="right">会员名：</td>
          <td width="121" align="left">
          <input type="text" name="username" cssClass="bian" size="18">
          </td>
        </tr>
        <tr>
          <td height="35" align="right">密　码：</td>
          <td align="left">
          <input type="password" name="password" cssClass="bian" size="18">
          </td>
        </tr>
        <tr>
          <td height="24" colspan="2" align="center">
          <input type="image" src="${pageContext.request.contextPath}/img/dl_06.gif" value="登　录">
          <a href = '${pageContext.request.contextPath}/home/goRegisterPage.action'>
          <img src="${pageContext.request.contextPath}/img/dl_08.gif" width="68" height="24" />
          </a>
          </td>
        </tr>
      </table>
      </form>
    </div>
  </div>
  <div id="foot"></div>
</div>
</body>
</html>