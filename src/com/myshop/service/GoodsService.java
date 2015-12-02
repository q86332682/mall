package com.myshop.service;

import java.util.List;

import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Hotsearch;

/**
 * 商品业务操作接口
 * @author Administrator
 *
 */
public interface GoodsService
{
	/**
	 * 获得热门搜索商品列表
	 * @return
	 */
	public List<Hotsearch> getHotsearchList();
	
	/**
	 * 搜索商品
	 * @param name 商品
	 */
	public List<Goods> searchGoodsList(String name);
	
	/**
	 * 获得人气排行榜
	 * @return
	 */
	public List<Goods> getGoodsByPopRank();
	
	/**
	 * 获得推荐商品
	 * @return
	 */
	public List<Goods> getGoodsByRecommend();
	
	/**
	 * 获得热销商品
	 * @return
	 */
	public List<Goods> getGoodsBySellhot();
	
	/**
	 * 获得分类列表
	 * @return
	 */
	public List<Category> getCategorylist();
	
	/**
	 * 通过分类获得商品
	 * @param categoryName 分类名称
	 * @return
	 */
	public List<Goods> getGoodsByCategory(String categoryName);
}
