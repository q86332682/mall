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
<script type="text/javascript">
	$(function(){
		$("#pubComment").click(function(){
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/goodsDetail/publishComment.action',
				data : {
					"goodsId" : 1,
					"score" : 5,
					"content" : "好"
				},
				success : function(data){
					alert("评论成功");
				}
			});
		});
		
		$("#updateComment").click(function(){
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/goodsDetail/updateComment.action',
				data : {
					"id" : 1,
					"score" : 10,
					"content" : "好"
				},
				success : function(data){
					alert("改评论成功");
				}
			});
		});
		
		$("#addTag").click(function(){
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/goodsDetail/addGoodsTag.action',
				data : {
					"name" : "标签",
					"goodsId" : 1,
					"count" : 1
				},
				success : function(data){
					alert("加标签成功");
				}
			});
		});
		
		$("#tagClick").click(function(){
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/goodsDetail/updateGoodsTag.action',
				data : {
					"id" : 7
				},
				success : function(data){
					alert("改标签成功");
				}
			});
		});
		
		$("#collect").click(function(){
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/goodsDetail/collectGoods.action',
				data : {
					"goodsId" : 1,
					"goodsname" : "Java 编程词典",
					"goodsimg" : "201004201340260341.jpg",
					"goodsMarketprice" : 150,
					"goodsSellprice" : 120
				},
				success : function(data){
					alert("收藏成功");
				}
			});
		});
	});
</script>

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
</div>
<!-- 商品信息 -->
<div id="right_sp">
<div id="zitiao"><img src="${pageContext.request.contextPath}/img/05.gif"
	width="676" height="31" /></div>
<div id="xshangpin">
	<ul>
		<li>
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td rowspan="5" width="160">
					<a href = ''>
						<img width="200" height="200" src="${pageContext.request.contextPath}/img/${goods.img}">
					</a>
					</td>
				</tr>
				<tr bgcolor="#f2eec9">
					<td align="right" width="90">商品名称：</td>
					<td>
					<a href = ''>
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
					<td colspan="2" align="center">
						<a href = '${pageContext.request.contextPath}/home/goCartPage.action?name=${goods.name}
						&marketprice=${goods.marketprice}&sellprice=${goods.sellprice}&id=${goods.id}'>
							<img src="${pageContext.request.contextPath}/img/gm_06.gif" width="136" height="32" />
						</a>
						<p style="margin-top: 10px;">
						<span style=" color: gray; text-decoration:underline; ">
						[  配送说明 ]</span>　　　
						<span style="color: gray; text-decoration:underline; ">
						[  付款方式 ] 
						</span>
						<br>
						<br>
						<span>
						<input id="pubComment" type="button" value="发布评论">
						<input id="updateComment" type="button" value="修改评论">
						<input id="addTag" type="button" value="增加标签">
						<input id="tagClick" type="button" value="标签点击">
						<input id="collect" type="button" value="收藏商品">
						</span>
						</p>
					</td>
				</tr>
			</table>
		</li>
	</ul>
	<ul>
		<li>
			<hr>
		</li>
	</ul>
	<ul>
		<li>
			<div style="background-color: #eedb97;">
				<img src="${pageContext.request.contextPath}/img/cp_11.gif" width="98" height="28" />
			</div>
			<p style="margin-top: 15px;">
				${goods.desc}
			</p>
		</li>
	</ul>
</div>
</div>
</div>

<!-- 以下内容不显示在页面 -->
<div style="display: none;">
	<!-- 商品属性,信息 -->
	<c:forEach items="${goods.goodsDesc}" var="gd">
		<span>${gd.name}, ${gd.val}</span>
	</c:forEach>
	
	<!-- 商品标签 -->
	<c:forEach items="${goods.goodsTag}" var="t">
		<span>${t.name}, ${t.count}</span>
	</c:forEach>
	
	<!-- 商品评论 -->
	<c:forEach items="${commentList.list}" var="c">
		<span>${c.username}, ${c.userlevel}, ${c.score}, ${c.content}</span>
	</c:forEach>
</div>
</body>
</html>