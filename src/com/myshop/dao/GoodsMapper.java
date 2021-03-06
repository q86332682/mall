package com.myshop.dao;

import java.util.List;

import com.myshop.model.Goods;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
import com.myshop.model.SQLAdapter;

public interface GoodsMapper
{	
	/**
	 * 查询商品总记录数
	 * @return
	 */
	public int queryGoodsCount();
	
	/**
	 * 查询搜索商品记录数
	 * @param goods
	 * @return
	 */
	public int querySearchGoodsCount(Goods goods);
	
	/**
	 * 查询商品按时间排序
	 * @return
	 */
	public List<Goods> queryGoodsTime(PageModel<Goods> pageModel);
	
	/**
	 * 查询商品按点击数排序
	 * @return
	 */
	public List<Goods> queryGoodsClickcount();
	
	/**
	 * 查询商品按点击数排序带分页
	 * @return
	 */
	public List<Goods> queryGoodsClickcountPage(PageModel<Goods> pageModel);
	
	/**
	 * 查询商品按卖出数量排序
	 * @return
	 */
	public List<Goods> queryGoodsSellcount();
	
	/**
	 * 查询商品按卖出数量排序带分页
	 * @return
	 */
	public List<Goods> queryGoodsSellcountPage(PageModel<Goods> pageModel);
	
	/**
	 * 按商品名称模糊查询
	 * @return
	 */
	public List<Goods> queryGoodsByName(PageModel<Goods> pageModel);
	
	/**
	 * 查询分类商品数量
	 * @param goods
	 * @return
	 */
	public int queryGoodsCategoryCount(Goods goods);
	
	/**
	 * 按分类名称查询商品
	 * @return
	 */
	public List<Goods> queryGoodsByCategory(PageModel<Goods> pageModel);
	
	/**
	 * 预加载分类商品
	 * @param categoryId
	 * @return
	 */
	public List<Goods> preloadCategoryGoods(Integer categoryId);
	
	/**
	 * 按id查询商品
	 * @return
	 */
	public Goods queryGoodsById(Integer id);
	
	/**
	 * 按id查询商品分表
	 * @return
	 */
	public Goods queryGoodsByIdSplit(SQLAdapter sqlAdapter);
	
	/**
	 * 更新点击数量
	 * @param id
	 */
	public void updateClickCount(Integer id);
	
	/**
	 * 更新卖出数量
	 * @param id
	 */
	public void updateSellCount(List<Ordergoods> OrdergoodsList);
	
	/**
	 * 更新评论数
	 * @param id
	 */
	public void updateCommentCount(Integer id);
	
	/**
	 * 更新库存
	 * @param OrdergoodsList
	 */
	public void updateStokc(List<Ordergoods> OrdergoodsList);
	
	/**
	 * 更新卖出数和库存
	 * @param OrdergoodsList
	 */
	public void updateSellCountAndStock(List<Ordergoods> OrdergoodsList);
	
	/**
	 * 精确查找
	 * @param pageModel
	 * @return
	 */
	public List<Goods> preciseSearch(PageModel<Goods> pageModel);
	
	/**
	 * 精确查找记录数
	 * @param goods
	 * @return
	 */
	public int preciseSearchCount(Goods goods);
	
	/**
	 * 初始化载入数据
	 * @return
	 */
	public List<Goods> loadData();
}
