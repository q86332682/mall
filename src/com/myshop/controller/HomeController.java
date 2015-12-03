package com.myshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Hotsearch;
import com.myshop.service.GoodsService;

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
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/searchGoods")
	public String searchGoods(String goodsName, Model model)
	{
		LOG.info("搜索商品");		
		List<Goods> goodslist = goodsService.searchGoodsList(goodsName);
		model.addAttribute("goodslist", goodslist);
		return "Test";
	}
	
	@RequestMapping("/goHomePage")
	public String goHomePage(Model model)
	{
		LOG.info("去主页");
		
		List<Hotsearch> hotSearchlist = goodsService.getHotsearchList();
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		List<Category> categorylist = goodsService.getCategorylist();
		
		model.addAttribute("hotSearchlist", hotSearchlist);
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		model.addAttribute("categorylist", categorylist);
		
		return "home";
	}
	
	@RequestMapping("/goRegisterPage")
	public String goRegisterPage()
	{
		return "Test";
	}
	
	@RequestMapping("/goLoginPage")
	public String goLoginPage()
	{
		return "Test";
	}
}
