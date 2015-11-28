package com.mall.dao;

import java.util.List;

import com.mall.generator.dao.GoodsMapper;
import com.mall.generator.model.Goods;
import com.mall.model.MyRecommendGoods;

/**
 * 自定义商品表操作Mapper
 * @author Administrator
 *
 */
public interface MyGoodsMapper
{
	/**
	 * 查询热卖和广告下面商品查询
	 * @return
	 */
	public List<Goods> queryHotSellAndAdvertDownGoods();
	
	/**
	 * 查询首页推荐商品
	 */
	public List<MyRecommendGoods> queryHomeRecommendGoods();
}
