package com.mall.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView goHomePage()
	{
		LOG.info("去主页!!!!!");
		List<MyRecommendGoods> list = homeService.getRecommendGoods();
		//List<Goods> list = homeService.getHotSellAndAdvertDownGoods();
		//List<MyCategory> list = homeService.getCategory();
		//List<Hotkeyword> list = homeService.getHotkeyword();
		//List<MyNavigate> list = homeService.getNavigate();
		//List<Advert> list = homeService.getHomeAdvert();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Test");
		return modelAndView;
	}
}
