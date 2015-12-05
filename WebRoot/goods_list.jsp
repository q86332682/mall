<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息列表</title>
<style type="text/css">
li {
	list-style: none;
	border-bottom: dashed #99CC66 1px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
<%@ include file="head.jsp"%>
<div id="box">
<div id="right">
<!-- 商品排行 -->
<div id="rqpgb">
<table width="195" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="199" height="31"><img
			src="${pageContext.request.contextPath}/img/index_28.gif" width="195" height="29" /></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="193" height="23" border="0" cellpadding="0" cellspacing="0">
				<c:forEach items="${goodslist1}" var="goods">
				<tr>
					<td width="187" valign="middle">
						<img src="${pageContext.request.contextPath}/img/h_32.gif" width="20" height="17" />
						<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}'>
							${goods.name}（人气：<span class="red">${goods.clickcount}</span>）
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
</div>
<!-- 推荐商品 -->
<div id="xpss">
<table width="195" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="195" height="31"><img
			src="${pageContext.request.contextPath}/img/08.gif" width="195" height="29" /></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<div style="width: 195px;">
				<c:forEach items="${goodslist2}" var="goods">
				<div style="float: left; width:45%; text-align: center;">
					<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}'>
						<img width="90" height="90" border="1" src="${pageContext.request.contextPath}/img/${goods.img}">
						<p style="width: 80px;">${goods.name}</p>
					</a>
				</div>
				</c:forEach>
			</div>
		</td>
	</tr>
</table>
</div>
<!-- 热销商品 -->
<div id="rxsp">
<table width="195" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="195" height="31"><img
			src="${pageContext.request.contextPath}/img/index_47.gif" width="195" height="29" /></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<div style="width: 195px;">
				<c:forEach items="${goodslist3}" var="goods">
				<div style="float: left; width:45%; text-align: center;">
					<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}'>
						<img width="90" height="90" border="1" src="${pageContext.request.contextPath}/img/${goods.img}">
						<p style="width: 80px;">${goods.name}</p>
					</a>
				</div>
				</c:forEach>
			</div>
		</td>
	</tr>
</table>
</div>
</div>
<!-- 商品信息列表 -->
<div id="right_sp">
<div id="zitiao"><img src="${pageContext.request.contextPath}/img/${menuImg}" width="676" height="31" /></div>
<div id="xshangpin">
<c:if test="${resultPageModel != null}">
	<c:forEach items="${resultPageModel.list}" var="goods">
	<ul>
		<li>
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td rowspan="5" width="160">
					<a href = ''>
						<img width="150" height="150" src="${pageContext.request.contextPath}/img/${goods.img}">
					</a>
					</td>
				</tr>
				<tr bgcolor="#f2eec9">
					<td align="right" width="90">商品名称：</td>
					<td>
					<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}'>
						${goods.name}
					</a>
					</td>
				</tr>
				<tr>
					<td align="right" width="90">市场价格：</td>
					<td>
					<font style="text-decoration: line-through;">
					${goods.marketprice}
					</font>
					</td>
				</tr>
				<tr bgcolor="#f2eec9">
					<td align="right" width="90">天下淘价格：</td>
					<td>
						
						<c:if test="${goods.sellprice <= goods.marketprice}">
							<font color="red">节省${goods.marketprice - goods.sellprice}</font>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}'>
							<img src="${pageContext.request.contextPath}/img/gm_06.gif" width="136" height="32" />
						</a>
					</td>
				</tr>
			</table>
		</li>
	</ul>
	</c:forEach>
	<div style="text-align: right; margin-top: 20px;margin-right: 20px;">
		<a href="${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=${menuName}&name=${search.name}&categoryId=${search.categoryId}&pageNow=1">首页</a>
		<c:if test="${resultPageModel.pageNow > 1}">
			<a href="${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=${menuName}&name=${search.name}&categoryId=${search.categoryId}&pageNow=${resultPageModel.pageNow - 1}">上一页</a>
		</c:if>
		<SPAN style="color: red;">
			　[${resultPageModel.pageNow}]　
		</SPAN>
		<c:if test="${resultPageModel.pageNow < resultPageModel.totalPage}">
			<a href="${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=${menuName}&name=${search.name}&categoryId=${search.categoryId}&pageNow=${resultPageModel.pageNow + 1}">下一页</a>
		</c:if>
		<a href="${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=${menuName}&name=${search.name}&categoryId=${search.categoryId}&pageNow=${resultPageModel.totalPage}">尾页</a>
	</div>
	</c:if>
	<c:if test="${resultPageModel == null}">
		<p>对不起，还没有添加商品信息。</p>
		<a href = '${pageContext.request.contextPath}/home/goHomePage.action'>返回主页</a>
	</c:if>
</div>
</div>
</div>
</body>
</html>