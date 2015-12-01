package com.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.model.MyComment;
import com.mall.model.MyConsultation;
import com.mall.model.MyGoods;
import com.mall.service.GoodsDetailsService;

@Controller
/**
 * 商品详情控制器
 * @author Administrator
 *
 */
public class GoodsDetailsController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsDetailsService goodsDetailsService;
	
	@RequestMapping("/goodsDetails/goGoodsDetailsPage")
	/**
	 * 去商品详情页面
	 */
	public String goGoodsDetailsPage(String goodsId, Model model)
	{
		LOG.info("去商品详情页面");
		MyGoods goodsDetails = goodsDetailsService.getGoodsDetails(goodsId);
		List<MyComment> comments = goodsDetailsService.getComments(goodsId);
		List<MyConsultation> consultations = goodsDetailsService.getConsultations(goodsId);
		model.addAttribute("goodsDetails", goodsDetails);
		model.addAttribute("comments", comments);
		model.addAttribute("consultations", consultations);
		return "Test";
	}
}
