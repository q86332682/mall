<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GO购网络商城by科帮网</title>
<STYLE type="text/css">
</STYLE>
<SCRIPT type="text/javascript">
	if (self != top) {
		top.location = self.location;
	}
</SCRIPT>
</head>
<body>
<%@include file="head.jsp"%>
<div id="box">
<div id="left">
<div id="left_s01"><a href = '${pageContext.request.contextPath}/home/goLoginPage.action'><img
	src="${pageContext.request.contextPath}/img/index_23.gif" class="imgx5" /></a>
	<a href = '${pageContext.request.contextPath}/home/goRegisterPage.action'><img
	src="${pageContext.request.contextPath}/img/index_26.gif" class="imgx5" /></a><img
	src="${pageContext.request.contextPath}/img/index_27.gif" /></div>
<div id="left_s02"><img
	src="${pageContext.request.contextPath}/img/index_25.gif" width="489" height="245"
	class="imgz5" /></div>
<!-- 类别 -->
<c:forEach items="${categorylist}" var="category">
<div id="left_x">
<div id="left122">
<table style="float: left;height: auto;width: 678px; vertical-align: middle; ">
    <tr>   
       <td class="word14" style="width: 22px; padding-left: 10px;">
           ${category.name}
       </td>
        <td style="padding-bottom: 3px;"> 
			<div id="left122_y">
				<!-- 二级 -->
				<c:if test="${category.childCategoryList != null}">
					<c:forEach items="${category.childCategoryList}" var="category2">
						<div style="white-space:nowrap; width: 28%;float: left; margin-top: 5px; margin-bottom: 5px; margin-left: 26px;">
							<b style="color: #990000;">${category2.name}</b>　
							<!-- 三级 -->
							<c:if test="${category2.childCategoryList != null}">
								<span>
								<c:forEach items="${category2.childCategoryList}" var="category3">
									<a href = '${pageContext.request.contextPath}/home/goGoodsListPage.action?menuName=分类商品&categoryId=${category3.id}'>
										${category3.name}
									</a>
								</c:forEach>
								</span>
							</c:if>
						</div>
					</c:forEach>
				</c:if>
			</div>
       </td>
    </tr>
</table>
</div>
</div>
</c:forEach>
</div>
<div id="right">
<!-- 商品排行 -->
<div id="rqpgb">
<table width="195" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="195" height="31"><img
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
						<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}&token=${token}'>
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
					<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}&token=${token}'>
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
<table width="195" border="0" cellpadding="0"
	cellspacing="0">
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
					<a href = '${pageContext.request.contextPath}/home/goGoodsDetailPage.action?id=${goods.id}&token=${token}'>
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
<div id="sckf"></div>
</div>
<div id="foot"></div>
</div>
<!-- 不出现在页面上的数据 -->
<div style="display: none;">
	<!-- 分类下的商品 -->
	<c:forEach items="${recommendCategory}" var="category">
		<span>${category.name}</span>
		<c:forEach items="${category.goods}" var="goods">
			<span>${goods.name}, ${goods.img}, ${goods.marketprice}, ${goods.sellprice}</span>
		</c:forEach>
	</c:forEach>
	
	<!-- 品牌下的商品 -->
	<c:forEach items="${recommendBrand}" var="brand">
		<span>${brand.name}</span>
		<c:forEach items="${brand.goods}" var="goods">
			<span>${goods.name}, ${goods.img}, ${goods.marketprice}, ${goods.sellprice}</span>
		</c:forEach>
	</c:forEach>
</div>
</body>
</html>