<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="${pageContext.request.contextPath}/css/style_index.css" rel="stylesheet" type="text/css" />
<style>
<!--
td {
	font-size: 12px;
}
-->
</style>
<div id="box">
<div id="dark"><a href = ''>
	<img src="${pageContext.request.contextPath}/img/index_03.gif" width="28"
		height="14" /> 我的购物车</a> | <a href = ''>我的订单</a>
		
<c:if test="${user != null}">
	欢迎    '${user.username}'
	<a href = ''>退出</a>
</c:if>

</div>

<div id="logo">

<form action="">
<div id="sou">
	<input type="text" name="name"><br>
	<div style="margin-top: 5px;">
		<b>热搜商品：</b>
		<c:forEach items="${hotSearchlist}" var="hotSearch">
			<a href = ''>${hotSearch.name}</a>&nbsp;
		</c:forEach>
	</div>
</div>
<div id="sou_zi">
	<input type="image" src="${pageContext.request.contextPath}/img/index_09.gif" value="搜索">
</div>
</form>
<div id="sou_zi01">高级搜索<br />

使用帮助</div>
</div>
<div id="menu">
	<a href = ''><img src="${pageContext.request.contextPath}/img/index_12.gif" width="92" height="33" /></a>
	<a href = ''><img src="${pageContext.request.contextPath}/img/index_13.gif" width="100" height="33" /></a>
	<a href = ''><img src="${pageContext.request.contextPath}/img/index_14.gif" width="99" height="33" /></a>
	<a href = ''><img src="${pageContext.request.contextPath}/img/index_15.gif" width="98" height="33" /></a>
	<a href = ''><img src="${pageContext.request.contextPath}/img/index_16.gif" width="100" height="33" /></a><img
	src="${pageContext.request.contextPath}/img/index_19.gif" width="144" height="33"
	id="z300" /></div>
</div>

