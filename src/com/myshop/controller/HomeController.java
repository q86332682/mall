package com.myshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Hotsearch;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
import com.myshop.model.User;
import com.myshop.service.GoodsService;
import com.myshop.service.UserService;

@Controller
@RequestMapping("/home")
/**
 * 商城主页控制器
 * @author Administrator
 *
 */
public class HomeController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final String NEW_GOODS = "新品上市";
	private final String SELL_HOT_GOODS = "热销商品";
	private final String RECOMMEND_GOODS = "推荐商品";
	private final String POP_GOODS = "人气商品";
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/searchGoods")
	public String searchGoods(String goodsName, Model model)
	{
		LOG.info("搜索商品");		
		List<Goods> goodslist = goodsService.searchGoodsList(goodsName);
		model.addAttribute("goodslist", goodslist);
		return "Test";
	}
	
	@RequestMapping("/loadHotSearchlist")
	@ResponseBody
	/**
	 * 载入热门搜索商品列表
	 * @return
	 */
	public List<Hotsearch> loadHotSearchlist()
	{
		List<Hotsearch> hotSearchlist = goodsService.getHotsearchList();
		return hotSearchlist;
	}
	
	@RequestMapping("/goHomePage")
	public String goHomePage(Model model)
	{
		LOG.info("去主页");
		
		//List<Hotsearch> hotSearchlist = goodsService.getHotsearchList();
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		List<Category> categorylist = goodsService.getCategorylist();
		
		//model.addAttribute("hotSearchlist", hotSearchlist);
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		model.addAttribute("categorylist", categorylist);
		
		return "home";
	}
	
	@RequestMapping("/goRegisterPage")
	public String goRegisterPage()
	{
		return "register";
	}
	
	@RequestMapping("/goLoginPage")
	public String goLoginPage()
	{
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session)
	{
		session.invalidate();
		return goHomePage(model);
	}
	
	@RequestMapping("/goCartPage")
	public String goCartPage(HttpSession session)
	{
		List<Ordergoods> list = (List<Ordergoods>) session.getAttribute("catrlist");
		float cartTotalprice = 0;
		if(list != null)
			for(Ordergoods g : list)
				cartTotalprice += g.getSellprice();
		session.setAttribute("cartTotalprice", cartTotalprice);
				
		return "cart_list";
	}
	
	@RequestMapping("/goMyOrderPage")
	public String goMyOrderPage(Model model, Order order, PageModel<Order> pageModel)
	{
		pageModel.setPageQuery(order);
		PageModel<Order> resultPageModel = userService.getMyOrderList(pageModel);
		model.addAttribute("resultPageModel", resultPageModel);
		return "order_list";
	}
	
	@RequestMapping("/goGoodsListPage")
	public String goGoodsListPage(Model model, String menuName, PageModel<Order> pageModel)
	{
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		
		
		
		String menuImg = null;
		PageModel<Goods> resultPageModel = null;
		
		if(NEW_GOODS.equals(menuName))
		{
			menuImg = "01.gif";
			resultPageModel = goodsService.getNewGoods(pageModel);
		}
		else if(SELL_HOT_GOODS.equals(menuName))
		{
			menuImg = "03.gif";
			resultPageModel = goodsService.getNewGoods(pageModel);
		}
		else if(RECOMMEND_GOODS.equals(menuName))
		{
			menuImg = "06.gif";
			resultPageModel = goodsService.getNewGoods(pageModel);
		}
		else if(POP_GOODS.equals(menuName))
		{
			menuImg = "07.gif";
			resultPageModel = goodsService.getNewGoods(pageModel);
		}

		model.addAttribute("menuImg", menuImg);
		model.addAttribute("menuName", menuName);
		model.addAttribute("resultPageModel", resultPageModel);
		LOG.info("goNewGoodsPage");
		return "goods_list";
	}
	
	@RequestMapping("/testPageModel")
	public void testPageModel()
	{
		Order order = new Order();
		order.setUserId(1);
		PageModel<Order> pageModel = new PageModel<Order>();
		pageModel.setPageNow(1);
		pageModel.setPageSize(5);
		pageModel.setPageQuery(order);
		PageModel<Order> resultPageModel = userService.getMyOrderList(pageModel);
		
		LOG.info("testPageModel");
	}
}
