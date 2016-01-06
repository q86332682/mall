package com.myshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myshop.model.Brand;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Hotsearch;
import com.myshop.service.GoodsService;

@Controller
@RequestMapping("/hometest")
/**
 * 商城主页测试控制器
 * @author Administrator
 *
 */
public class HomeTestController
{
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/goHometest1")
	public String goHometest1(Model model)
	{
		//List<Hotsearch> hotSearchlist = goodsService.getHotsearchList();
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		List<Category> categorylist = goodsService.getCategorylist();
		List<Category> recommendCategory = goodsService.getRecommendCategory();
		List<Brand> recommendBrand = goodsService.getRecommendBrand();
		
		//model.addAttribute("hotSearchlist", hotSearchlist);
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("recommendCategory", recommendCategory);
		model.addAttribute("recommendBrand", recommendBrand);
		
		return "HomeTest";
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
	
	@RequestMapping("/getGoodsByPopRank")
	@ResponseBody
	public List<Goods> getGoodsByPopRank()
	{
		return goodsService.getGoodsByPopRank();
	}
	
	@RequestMapping("/getGoodsByRecommend")
	@ResponseBody
	public List<Goods> getGoodsByRecommend()
	{
		return goodsService.getGoodsByRecommend();
	}
	
	@RequestMapping("/getGoodsBySellhot")
	@ResponseBody
	public List<Goods> getGoodsBySellhot()
	{
		return goodsService.getGoodsBySellhot();
	}
	
	@RequestMapping("/getCategorylist")
	@ResponseBody
	public List<Category> getCategorylist()
	{
		return goodsService.getCategorylist();
	}
	
	@RequestMapping("/getRecommendCategory")
	@ResponseBody
	public List<Category> getRecommendCategory()
	{
		return goodsService.getRecommendCategory();
	}
	
	@RequestMapping("/getRecommendBrand")
	@ResponseBody
	public List<Brand> getRecommendBrand()
	{
		return goodsService.getRecommendBrand();
	}
}
