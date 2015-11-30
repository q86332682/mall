package com.mall.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.generator.model.Advert;
import com.mall.generator.model.Goods;
import com.mall.generator.model.Hotkeyword;
import com.mall.model.MyCategory;
import com.mall.model.MyNavigate;
import com.mall.model.MyRecommendGoods;
import com.mall.service.HomeService;

@Controller
public class HomeController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping("/home/goHomePage")
	/**
	 * 去主页
	 * @return
	 */
	public String goHomePage(Model model)
	{
		LOG.info("去主页!!!!!");
		
		//载入主页数据
		List<MyNavigate> navigatelist = homeService.getNavigate();
		List<Hotkeyword> hotkeywordlist = homeService.getHotkeyword();
		List<MyCategory> categorylist = homeService.getCategory();
		List<Advert> advertlist = homeService.getHomeAdvert();
		List<Goods> goodslist1 = homeService.getHotSellAndAdvertDownGoods();
		List<MyRecommendGoods> goodslist2 = homeService.getRecommendGoods();
		
		//加入到模型
		model.addAttribute("navigatelist", navigatelist);
		model.addAttribute("hotkeywordlist", hotkeywordlist);
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("advertlist", advertlist);
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		
		return "Test";
	}
	
	
	/*********************以下是测试代码*********************/
	
	@RequestMapping("/home/goHomePage2")
	public String goHomePage2(Model model)
	{
		LOG.info("去Test2");
		return "Test2";
	}
	
	@RequestMapping("/home/loadNavigatelist")
	public @ResponseBody List<MyNavigate> loadNavigatelist()
	{
		LOG.info("加载导航列表");
		List<MyNavigate> navigatelist = homeService.getNavigate();
		return navigatelist;
	}
	
	@RequestMapping("/home/loadHotkeywordlist")
	public @ResponseBody List<Hotkeyword> loadHotkeywordlist()
	{
		LOG.info("加载热门关键词");
		List<Hotkeyword> hotkeywordlist = homeService.getHotkeyword();
		return hotkeywordlist;
	}
	
	@RequestMapping("/home/loadCategorylist")
	public @ResponseBody List<MyCategory> loadCategorylist()
	{
		List<MyCategory> categorylist = homeService.getCategory();
		return categorylist;
	}
	
	@RequestMapping("/home/loadAdvertlist")
	public @ResponseBody List<Advert> loadAdvertlist()
	{
		List<Advert> advertlist = homeService.getHomeAdvert();
		return advertlist;
	}
	
	@RequestMapping("/home/loadGoodslist1")
	public @ResponseBody List<Goods> loadGoodslist1()
	{
		List<Goods> goodslist1 = homeService.getHotSellAndAdvertDownGoods();
		return goodslist1;
	}
	
	@RequestMapping("/home/loadGoodslist2")
	public @ResponseBody List<MyRecommendGoods> loadGoodslist2()
	{
		List<MyRecommendGoods> goodslist2 = homeService.getRecommendGoods();
		return goodslist2;
	}
}
