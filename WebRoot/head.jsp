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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	//加载热门关键词
	$.ajax({
		type : 'get',
		url : '${pageContext.request.contextPath}/home/loadHotSearchlist.action',
		success : function(data){
			var htmltext = "<b>热搜商品：</b>";
			for(var i = 0;i < data.length;i++)
			{
				var d = data[i];
				htmltext += "<a href = ''>" + d.name + "</a>&nbsp;";
			}
			$("#hotSearchlist").html(htmltext);
		}
	});
</script>
<div id="box">
<div id="dark"><a href = '${pageContext.request.contextPath}/home/goCartPage.action'>
	<img src="${pageContext.request.contextPath}/img/index_03.gif" width="28"
		height="14" /> 我的购物车</a> | <a href = '${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=1'>我的订单</a>
		
<c:if test="${user != null}">
	欢迎    ${user.username}
	<a href = '${pageContext.request.contextPath}/home/logout.action'>退出</a>
</c:if>

</div>

<div id="logo">

<form action="">
<div id="sou">
	<input type="text" name="name"><br>
	<div style="margin-top: 5px;" id="hotSearchlist">
<%--		<b>热搜商品：</b>--%>
<%--		<c:forEach items="${hotSearchlist}" var="hotSearch">--%>
<%--			<a href = ''>${hotSearch.name}</a>&nbsp;--%>
<%--		</c:forEach>--%>
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
	<a href = '${pageContext.request.contextPath}/home/goHomePage.action'>
	<img src="${pageContext.request.contextPath}/img/index_12.gif" width="92" height="33" /></a>
	<a href = '${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=新品上市&pageNow=1'>
	<img src="${pageContext.request.contextPath}/img/index_13.gif" width="100" height="33" /></a>
	<a href = '${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=热销商品&pageNow=1'>
	<img src="${pageContext.request.contextPath}/img/index_14.gif" width="99" height="33" /></a>
	<a href = '${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=推荐商品&pageNow=1'>
	<img src="${pageContext.request.contextPath}/img/index_15.gif" width="98" height="33" /></a>
	<a href = '${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=人气商品&pageNow=1'>
	<img src="${pageContext.request.contextPath}/img/index_16.gif" width="100" height="33" /></a><img
	src="${pageContext.request.contextPath}/img/index_19.gif" width="144" height="33"
	id="z300" /></div>
</div>

