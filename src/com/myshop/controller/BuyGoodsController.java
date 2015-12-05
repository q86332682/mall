package com.myshop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.service.GoodsService;
import com.myshop.service.UserService;

@Controller
@RequestMapping("/buyGoods")
/**
 * 购买商品控制器
 * @author Administrator
 *
 */
public class BuyGoodsController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/submitOrder")
	public String submitOrder(HttpSession session, Order order)
	{
		session.setAttribute("myOrder", order);
		return "order_confirm";
	}
	
	@RequestMapping("/completeBuy")
	public String completeBuy(HttpSession session)
	{
		LOG.info("完成购买!!!!");
		Order myOrder = (Order) session.getAttribute("myOrder");
		float cartTotalprice = (Float) session.getAttribute("cartTotalprice");
		myOrder.setTotalprice(cartTotalprice);
		List<Ordergoods> list = (List<Ordergoods>) session.getAttribute("catrlist");
		userService.buyGoods(myOrder, list);
		session.setAttribute("catrlist", null);
		return "redirect:/home/goMyOrderPage.action";
	}
}
