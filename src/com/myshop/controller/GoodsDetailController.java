package com.myshop.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.model.Goodscomment;
import com.myshop.model.User;
import com.myshop.service.GoodsService;

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
		goodsService.publishComment(goodscomment);
		return null;
	}
	
	@RequestMapping("/updateComment")
	public String updateComment(Goodscomment goodscomment)
	{
		LOG.info("修改评论!");
		goodscomment.setId(6);
		goodscomment.setScore(3);
		goodscomment.setContent("差");
		goodsService.updateComment(goodscomment);
		return null;
	}
}
