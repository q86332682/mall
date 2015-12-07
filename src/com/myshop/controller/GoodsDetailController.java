package com.myshop.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.model.GoodsCollect;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.model.User;
import com.myshop.service.GoodsService;
import com.myshop.service.UserService;

@Controller
@RequestMapping("/goodsDetail")
/**
 * 商品详情控制器
 * @author Administrator
 *
 */
public class GoodsDetailController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private GoodsService goodsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/publishComment")
	public String publishComment(HttpSession session, Goodscomment goodscomment)
	{
		LOG.info("发布评论!");
		User user = (User) session.getAttribute("user");
		goodscomment.setUserId(user.getId());
		goodscomment.setGoodsId(1);
		goodscomment.setUsername(user.getUsername());
		goodscomment.setUserlevel(user.getLevel());
		goodscomment.setScore(5);
		goodscomment.setContent("好");
		userService.publishComment(goodscomment, user);
		return null;
	}
	
	@RequestMapping("/updateComment")
	public String updateComment(Goodscomment goodscomment)
	{
		LOG.info("修改评论!");
		goodscomment.setId(1);
		goodscomment.setScore(3);
		goodscomment.setContent("差");
		userService.updateComment(goodscomment);
		return null;
	}
	
	@RequestMapping("/addGoodsTag")
	public String addGoodsTag(GoodsTag tag)
	{
		LOG.info("为商品添加标签!");
		tag.setName("测试标签");
		tag.setCount(1);
		tag.setUserId(3);
		tag.setGoodsId(1);
		userService.addGoodsTag(tag);
		return null;
	}
	
	@RequestMapping("/updateGoodsTag")
	public String updateGoodsTag(Integer id)
	{
		LOG.info("更新指定标签点击数!");
		userService.updateGoodsTag(id);
		return null;
	}
	
	@RequestMapping("/collectGoods")
	public String collectGoods(HttpSession session, GoodsCollect goodsCollect)
	{
		LOG.info("收藏商品!");
		User user = (User) session.getAttribute("user");
		goodsCollect.setUserId(3);
		goodsCollect.setGoodsId(1);
		goodsCollect.setGoodsname("name");
		goodsCollect.setGoodsimg("img");
		goodsCollect.setGoodsMarketprice(150);
		goodsCollect.setGoodsSellprice(120);
		userService.collectGoods(goodsCollect, user);
		return null;
	}
}
