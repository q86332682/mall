package com.mall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mall.generator.model.Advert;
import com.mall.generator.model.Goods;
import com.mall.generator.model.Hotkeyword;
import com.mall.model.MyCategory;
import com.mall.model.MyNavigate;
import com.mall.service.HomeService;

@Service
/**
 * 主页业务处理
 * @author Administrator
 *
 */
public class HomeServiceImpl implements HomeService
{
	@Override
	/**
	 * 获得导航列表
	 */
	public List<MyNavigate> getNavigate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 获得热门关键词
	 */
	public List<Hotkeyword> getHotkeyword()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 获得分类列表
	 */
	public List<MyCategory> getCategory()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 获得首页广告(主广告, 主广告下小广告, 热卖右侧广告查询)
	 */
	public List<Advert> getHomeAdvert()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 获得热卖和广告下面商品查询
	 */
	public List<Goods> getHotSellAndAdvertDownGoods()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 获得推荐商品
	 */
	public void getRecommendGoods()
	{
		// TODO Auto-generated method stub
	}
}