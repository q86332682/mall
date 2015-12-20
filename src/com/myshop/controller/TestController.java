package com.myshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.service.GoodsService;

@Controller
@RequestMapping("/test")
/**
 * 提供测试的控制器
 * @author Administrator
 *
 */
public class TestController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/testRecommendCategory")
	/**
	 * 测试推荐分类商品
	 */
	public void testRecommendCategory()
	{
		LOG.info("测试推荐分类商品!");
		goodsService.getRecommendCategory();
	}
}
