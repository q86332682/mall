package com.myshop.dao;

import java.util.List;

import com.myshop.model.Goods;
import com.myshop.model.Order;
import com.myshop.model.PageModel;

public interface GoodsMapper
{
	/**
	 * 查询商品按时间排序
	 * @return
	 */
	public PageModel<Goods> queryGoodsTime(PageModel<Order> pageModel);
	
	/**
	 * 查询商品按点击数排序
	 * @return
	 */
	public List<Goods> queryGoodsClickcount();
	
	/**
	 * 查询商品按卖出数量排序
	 * @return
	 */
	public List<Goods> queryGoodsSellcount();
	
	/**
	 * 按商品名称模糊查询
	 * @return
	 */
	public List<Goods> queryGoodsByName(String name);
	
	/**
	 * 按分类名称查询商品
	 * @return
	 */
	public List<Goods> queryGoodsByCategory(String categoryName);
}
