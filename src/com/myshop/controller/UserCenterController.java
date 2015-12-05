package com.myshop.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.model.Ordergoods;

@Controller
@RequestMapping("/userCenter")
/**
 * 用户中心控制器
 * @author Administrator
 *
 */
public class UserCenterController
{
	@RequestMapping("/delCartGoods")
	public String delCartGoods(HttpSession session, Integer goodsId)
	{
		List<Ordergoods> list = (List<Ordergoods>) session.getAttribute("catrlist");
		Iterator<Ordergoods> it = list.iterator();
		while(it.hasNext())
		{
			Ordergoods g = it.next();
			if(g.getGoodsId().equals(goodsId))
				it.remove();
		}
		session.setAttribute("catrlist", list);
		return "redirect:/home/goCartPage.action";
	}
	
	@RequestMapping("/clearCart")
	public String clearCart(HttpSession session)
	{
		session.setAttribute("catrlist", null);
		return "redirect:/home/goCartPage.action";
	}
	
	@RequestMapping("/goOrderPage")
	public String goOrderPage()
	{
		return "order_add";
	}
}
