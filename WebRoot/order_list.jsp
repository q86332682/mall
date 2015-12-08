<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
</head>
<body>
<%@ include file="head.jsp"%>
<div id="box">
<div id="wdzh_left">
      <div id="left_list"><a href = '${pageContext.request.contextPath}/home/goCartPage.action'>我的购物车</a></div>
	  <div id="left_list"><a href = '${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=1'>我的订单</a></div>
    </div>
    <div id="wdzh_right">
      <div id="right_tiao">　&gt;　我的订单</div>
      <div id="biaodan">
        <table width="688" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30" bgcolor="#fff8ad"><table width="688" height="30" border="0" cellpadding="0" cellspacing="0" class="yellow">
              <tr>
                <td width="158" height="30" align="center">订单号码</td>
                <td width="90" align="center">订单总金额</td>
                <td width="66" align="center">收货人</td>
                <td width="119" align="center">收货地址</td>
                <td width="74" align="center">支付方式</td>
				 <td width="112" align="center">创建时间</td>
				  <td width="69" align="center">订单状态</td>
              </tr>
            </table></td>
          </tr>
          <c:forEach items="${resultPageModel.list}" var="order">
          <tr>
            <td><div id="xiaotiao">
              <table width="688" height="60" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="158" height="30" align="center">${order.id}</td>
                  <td width="90" align="center">${order.totalprice}</td>
                  <td width="66" align="center">${order.name}</td>
                  <td width="119" align="center">${order.addr}</td>
                  <td width="74" align="center">${order.payWay}</td>
                  <td width="112" align="center"><p>${order.createtime}</p>
                  </td>
                  <td width="69" align="center">${order.state}</td>
                </tr>
              </table>
            </div></td>
          </tr>
		</c:forEach>
        </table>
      </div>
    </div>
<div style="text-align: right; margin-top: 20px;margin-right: 20px;">
	<a href="${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=1">首页</a>
	<c:if test="${resultPageModel.pageNow > 1}">
		<a href="${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=${resultPageModel.pageNow - 1}">上一页</a>
	</c:if>
	<SPAN style="color: red;">
		　[${resultPageModel.pageNow}]　
	</SPAN>
	<c:if test="${resultPageModel.pageNow < resultPageModel.totalPage}">
		<a href="${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=${resultPageModel.pageNow + 1}">下一页</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=${resultPageModel.totalPage}">尾页</a>
</div>
  <div id="foot"></div>
</div>
<!-- 以下内容不显示 -->
<div style="display: none;">
	<!-- 用户资料 -->
	<span>${user.username}, ${user.email}, ${user.addr}, ${user.mobile}</span>
	<span>${user.gold}, ${user.score}, ${user.commentCount}, ${user.collectCount}, ${user.orderCount}</span>
	
	<!-- 会员等级列表 -->
	<c:forEach items="${userlevelList}" var="item">
		<span>${item.name}, ${item.score}, ${item.percent}, ${item.desc}</span>
	</c:forEach>
	
	<!-- 积分日志列表 -->
	<c:forEach items="${scorelog}" var="item">
		<span>${item.info}, ${item.score}, ${item.createtime}</span>
	</c:forEach>
	
	<!-- 用户收藏列表 -->
	<c:forEach items="${goodsCollect}" var="item">
		<span>${item.goodsname}, ${item.goodsimg}, ${item.goodsMarketprice}, ${item.goodsSellprice}, ${item.createtime}</span>
	</c:forEach>
</div>
</body>
</html>