<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单确认</title>
</head>
<body>
<%@ include file="head.jsp"%>
<div id="box">
	<div id="wdzh_left">
      <div id="left_list"><a href = '${pageContext.request.contextPath}/home/goCartPage.action'>我的购物车</a></div>
	  <div id="left_list"><a href = '${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=1'>我的订单</a></div>
    </div>
    <div id="wdzh_right">
      <div id="right_tiao">　&gt;　订单确认</div>
      <div id="biaodan">
      <table width="688" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="30" bgcolor="#e7f3c3"><table width="688" height="30" border="0" cellpadding="0" cellspacing="0" class="green">
              <tr>
                <td width="213" height="30" align="center">商品名称</td>
                <td width="130" align="center">市场价</td>
                <td width="130" align="center">价格</td>
                <td width="104" align="center">数量</td>
              </tr>
            </table></td>
          </tr>
          
          <c:forEach items="${catrlist}" var="cartGoods">
          <tr>
            <td><div id="xiaotiao">
              <table width="688" height="30" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="213" height="30" align="center">${cartGoods.name}</td>
                  <td width="130" align="center"><span style="text-decoration: line-through;">
                  ￥${cartGoods.marketprice}元</span>
                  </td>
                  <td width="130" align="center">￥${cartGoods.sellprice}元<br>
			为您节省：￥${cartGoods.marketprice - cartGoods.sellprice}元</td>
                  <td width="104" align="center" class="red">${cartGoods.num}</td>
                </tr>
              </table>
            </div></td>
          </tr>
           </c:forEach>
           <tr>
            <td width="688" height="25" align="right">
            	总价：<span class="red">￥${cartTotalprice}</span>元　&nbsp;</td>
          </tr>
          <tr>
            <td width="688" height="25"><div id="bd_xt">我的信息</div></td>
          </tr>
          <tr>
            <td height="60" align="center" valign="middle">
            <table width="100%">
	<tr>
		<td align="right" width="90">收货人姓名：</td>
		<td align="left">${myOrder.name}</td>
	</tr>
	<tr>
		<td align="right" width="90">收货人地址：</td>
		<td align="left">${myOrder.addr}</td>
	</tr>
	<tr>
		<td align="right" width="90">收货人电话：</td>
		<td align="left">${myOrder.mobile}</td>
	</tr>
	<tr>
		<td align="right" width="90">货款支付方式：</td>
		<td align="left">${myOrder.payWay}</td>
	</tr>
</table>
            </td>
          </tr>
          <tr>
          	<td colspan="2" align="center">
          	<a href = '${pageContext.request.contextPath}/buyGoods/completeBuy.action'>
          		<img src="${pageContext.request.contextPath}/img/zh01_03.gif">
          	</a>
          	</td>
          </tr>
        </table>
    </div>
    </div>
  <div id="foot"></div>
</div>
</body>
</html>