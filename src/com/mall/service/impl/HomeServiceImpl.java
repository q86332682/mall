package com.mall.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.MyAdvertMapper;
import com.mall.dao.MyCategoryMapper;
import com.mall.dao.MyGoodsMapper;
import com.mall.dao.MyNavigateMapper;
import com.mall.generator.dao.HotkeywordMapper;
import com.mall.generator.model.Advert;
import com.mall.generator.model.Goods;
import com.mall.generator.model.Hotkeyword;
import com.mall.generator.model.HotkeywordExample;
import com.mall.model.MyCategory;
import com.mall.model.MyNavigate;
import com.mall.model.MyRecommendGoods;
import com.mall.service.HomeService;

@Service
/**
 * 主页业务处理
 * @author Administrator
 *
 */
public class HomeServiceImpl implements HomeService
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MyNavigateMapper myNavigateMapper;		 	//导航表操作mapper
	
	@Autowired
	private HotkeywordMapper hotkeywordMapper;			//热门关键词表操作mapper
	
	@Autowired
	private MyCategoryMapper myCategoryMapper;			//分类表操作mapper
	
	@Autowired
	private MyAdvertMapper myAdvertMapper;				//广告表操作mapper
	
	@Autowired
	private MyGoodsMapper myGoodsMapper;				//商品表操作mapper
	
	@Override
	/**
	 * 获得导航列表
	 */
	public List<MyNavigate> getNavigate()
	{
		List<MyNavigate> list = myNavigateMapper.queryNavigates();
		return list;
	}

	@Override
	/**
	 * 获得热门关键词
	 */
	public List<Hotkeyword> getHotkeyword()
	{
		List<Hotkeyword> list = hotkeywordMapper.selectByExample(new HotkeywordExample());
		return list;
	}

	@Override
	/**
	 * 获得分类列表
	 */
	public List<MyCategory> getCategory()
	{
		List<MyCategory> list = myCategoryMapper.queryCategory();
		return list;
	}

	@Override
	/**
	 * 获得首页广告(主广告, 主广告下小广告, 热卖右侧广告查询)
	 */
	public List<Advert> getHomeAdvert()
	{
		List<Advert> list = myAdvertMapper.queryHomeAdverts();
		return list;
	}

	@Override
	/**
	 * 获得热卖和广告下面商品查询
	 */
	public List<Goods> getHotSellAndAdvertDownGoods()
	{
		List<Goods> list =  myGoodsMapper.queryHotSellAndAdvertDownGoods();
		return list;
	}

	@Override
	/**
	 * 获得推荐商品
	 */
	public List<MyRecommendGoods> getRecommendGoods()
	{
		List<MyRecommendGoods> list = myGoodsMapper.queryHomeRecommendGoods();
		return list;
	}
}