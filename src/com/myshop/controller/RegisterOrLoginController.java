package com.myshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.model.Ordergoods;
import com.myshop.model.User;
import com.myshop.service.UserService;

@Controller
@RequestMapping("/registerOrLogin")
/**
 * 用户注册或登录控制器
 * @author Administrator
 *
 */
public class RegisterOrLoginController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public String register(User user)
	{
		userService.register(user);
		return "login";
	}
	
	@RequestMapping("/login")
	/**
	 * 用户登录
	 * @param user
	 * @param session
	 * @return
	 */
	public String login(User user, HttpServletRequest request, HttpSession session)
	{
		//加入测试购物车数据
//		List<Ordergoods> list = new ArrayList<Ordergoods>();
//		Ordergoods catrGoods1 = new Ordergoods();
//		catrGoods1.setName("Java 编程词典");
//		catrGoods1.setMarketprice(150);
//		catrGoods1.setSellprice(120);
//		catrGoods1.setNum(1);
//		catrGoods1.setGoodsId(1);
//		list.add(catrGoods1);
//		session.setAttribute("catrlist", list);
		
		
		User loginUser = userService.login(user);
		
		if(loginUser != null)
		{
			session.setAttribute("user", loginUser);
			return "redirect:/home/goHomePage.action";
		}
		else
		{
			LOG.info(user.getUsername() + "输入的用户名或密码错误, 返回登录页面!");
			return "login";
		}
	}
}
