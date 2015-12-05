<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加订单</title>
</head>
<body>
<%@ include file="head.jsp"%>
<div id="box">
	<div id="wdzh_left">
      <div id="left_list"><a href = '${pageContext.request.contextPath}/home/goCartPage.action'>我的购物车</a></div>
	  <div id="left_list"><a href = '${pageContext.request.contextPath}/home/goMyOrderPage.action?userId=${user.id}&pageNow=1'>我的订单</a></div>
    </div>
     <form action="${pageContext.request.contextPath}/buyGoods/submitOrder.action" method="post">
     <input type="hidden" name="userId" value="${user.id}">
    <div id="wdzh_right">
      <div id="right_tiao">　&gt;　我的订单</div>
      <div id="biaodan01">
     
        <table width="688" height="35" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" align="right" valign="middle">收货人姓名：</td>
            <td width="572" align="left" valign="middle">
            <input type="text" name="name" size="25">
            </td>
          </tr>
        </table>
      </div>
	  <div id="biaodan02">
	    <table width="688" height="70" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" align="right" valign="middle">收货人地址：</td>
            <td width="572" align="left" valign="middle">
            <textarea name="addr" cols="30" rows="3"></textarea>
            </td>
          </tr>
        </table>
	  </div>
	  <div id="biaodan03">
	    <table width="688" height="35" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" align="right" valign="middle">收货人电话：</td>
            <td width="572" align="left" valign="middle">
            <input type="text" name="mobile" cssClass="bian" size="25">
            </td>
          </tr>
        </table>
	  </div>
	  <div id="biaodan04">
	    <table width="688" height="140" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" height="35" align="right" valign="middle">支付方式：</td>
            <td width="572" align="left" valign="middle">
            <p>
				<input type="radio" name="payWay" value="网上银行">网上银行
			</p>
			<p>
				<input type="radio" name="payWay" value="支付宝">支付宝
			</p>
			<p>
				<input type="radio" name="payWay" value="货到付款">货到付款
			</p>
			<p>
				<input type="radio" name="payWay" value="邮局汇款" checked="checked">邮局汇款
				收款人地址：吉林省长春市xxx大厦xxx室  收款人姓名：xxx  收款人邮编：xxxx
			</p>
			</td>
          </tr>
        </table>
	  </div>
	  <div id="biaodan05">
	    <table width="688" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="688" align="center">
            <input type="image" src="${pageContext.request.contextPath}/img/zh01_03.gif">
           </td>
          </tr>
        </table>
        
	  </div>
    </div>
 </form>
  <div id="foot"></div>
</div>
</body>
</html>