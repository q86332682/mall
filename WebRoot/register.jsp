<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
<%@ include file="head.jsp"%>
<div id="box">
<div id="mid">
    <div id="zhuce">
	<form action="${pageContext.request.contextPath}/registerOrLogin/register.action" method="post">
	<table width="80%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2">
<%--			<s:fielderror></s:fielderror>--%>
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">用 户 名：</td>
			<td>
				<input type="text" name="username" cssClass="bian">*
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">密　码：</td>
			<td>
				<input type="password" name="password" cssClass="bian">*
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">确认密码：</td>
			<td>
				<input type="password" name="repassword" cssClass="bian">*
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">邮箱地址：</td>
			<td>
				<input type="text" name="email" cssClass="bian">*
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">住　址：</td>
			<td>
				<input type="text" name="addr" cssClass="bian">*
			</td>
		</tr>
		<tr>
			<td width="70" height="35" align="right">手　机：</td>
			<td>
				<input type="text" name="mobile" cssClass="bian">*
			</td>
		</tr>
		<tr>
			<td colspan="2" height="80" valign="middle" align="center">
			<input type="image" value="登　录" src="${pageContext.request.contextPath}/img/dl_08.gif">
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