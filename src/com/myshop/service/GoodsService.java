package com.myshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myshop.model.Brand;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.GoodsCollect;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.model.Hotsearch;
import com.myshop.model.PageModel;

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
	public PageModel<Goods> searchGoodsList(PageModel<Goods> pageModel);
	
	/**
	 * 获得最新上架商品
	 * @return
	 */
	public PageModel<Goods> getNewGoods(PageModel<Goods> pageModel);
	
	/**
	 * 获得人气排行榜
	 * @return
	 */
	public List<Goods> getGoodsByPopRank();
	
	/**
	 * 获得人气排行榜带分页
	 * @return
	 */
	public PageModel<Goods> getGoodsByPopRank(PageModel<Goods> pageModel);
	
	/**
	 * 获得推荐商品
	 * @return
	 */
	public List<Goods> getGoodsByRecommend();
	
	/**
	 * 获得推荐商品带分页
	 * @return
	 */
	public PageModel<Goods> getGoodsByRecommend(PageModel<Goods> pageModel);
	
	/**
	 * 获得热销商品
	 * @return
	 */
	public List<Goods> getGoodsBySellhot();
	
	/**
	 * 获得热销商品带分页
	 * @return
	 */
	public PageModel<Goods> getGoodsBySellhot(PageModel<Goods> pageModel);
	
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
	public PageModel<Goods> getGoodsByCategory(PageModel<Goods> pageModel);
	
	/**
	 * 通过id获得商品
	 * @param id
	 * @return
	 */
	public Goods getGoodsById(Integer id, HttpServletRequest req);
	
	/**
	 * 获得推荐分类商品
	 * @return
	 */
	public List<Category> getRecommendCategory();
	
	/**
	 * 获得推荐品牌
	 * @return
	 */
	public List<Brand> getRecommendBrand();	
	
	/**
	 * 获得评论列表
	 * @param pageModel
	 * @return
	 */
	public PageModel<Goodscomment> getCommentList(PageModel<Goodscomment> pageModel);
}
