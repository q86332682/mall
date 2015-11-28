package com.mall.service;

import java.util.List;

import com.mall.generator.model.Advert;
import com.mall.generator.model.Goods;
import com.mall.generator.model.Hotkeyword;
import com.mall.model.MyCategory;
import com.mall.model.MyNavigate;
import com.mall.model.MyRecommendGoods;

/**
 * 主页业务处理
 * @author Administrator
 *
 */
public interface HomeService
{
	public List<MyNavigate> getNavigate();
	
	public List<Hotkeyword> getHotkeyword();
	
	public List<MyCategory> getCategory();
	
	public List<Advert> getHomeAdvert();
	
	public List<Goods> getHotSellAndAdvertDownGoods();
	
	public List<MyRecommendGoods> getRecommendGoods();
}
