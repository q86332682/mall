package com.myshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myshop.model.Goods;
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
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/publishComment")
	@ResponseBody
	public String publishComment(HttpSession session, Goodscomment goodscomment)
	{
		LOG.info("发布评论!");
		User user = (User) session.getAttribute("user");
		goodscomment.setUserId(user.getId());
//		goodscomment.setGoodsId(1);
		goodscomment.setUsername(user.getUsername());
		goodscomment.setUserlevel(user.getLevel());
//		goodscomment.setScore(5);
//		goodscomment.setContent("好");
		userService.publishComment(goodscomment, user);
		return null;
	}
	
	@RequestMapping("/updateComment")
	@ResponseBody
	public String updateComment(Goodscomment goodscomment)
	{
		LOG.info("修改评论!");
		//goodscomment.setId(1);
		//goodscomment.setScore(3);
		//goodscomment.setContent("差");
		userService.updateComment(goodscomment);
		return null;
	}
	
	@RequestMapping("/addGoodsTag")
	@ResponseBody
	public String addGoodsTag(HttpSession session, GoodsTag tag)
	{
		LOG.info("为商品添加标签!");
		User user = (User) session.getAttribute("user");
//		tag.setName("测试标签");
//		tag.setCount(1);
		tag.setUserId(user.getId());
//		tag.setGoodsId(1);
		userService.addGoodsTag(tag);
		return null;
	}
	
	@RequestMapping("/updateGoodsTag")
	@ResponseBody
	public String updateGoodsTag(Integer id)
	{
		LOG.info("更新指定标签点击数!");
		userService.updateGoodsTag(id);
		return null;
	}
	
	@RequestMapping("/collectGoods")
	@ResponseBody
	public String collectGoods(HttpServletRequest req, GoodsCollect goodsCollect)
	{
		LOG.info("收藏商品!");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Goods goods = goodsService.getGoodsById(goodsCollect.getGoodsId(), req);
		goodsCollect.setUserId(user.getId());
//		goodsCollect.setGoodsId(1);
		goodsCollect.setGoodsname(goods.getName());
		goodsCollect.setGoodsimg(goods.getImg());
		goodsCollect.setGoodsMarketprice(goods.getMarketprice());
		goodsCollect.setGoodsSellprice(goods.getSellprice());
		userService.collectGoods(goodsCollect, user);
		return null;
	}
}
