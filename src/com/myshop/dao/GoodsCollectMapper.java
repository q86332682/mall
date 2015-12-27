package com.myshop.dao;

import java.util.List;

import com.myshop.model.GoodsCollect;
import com.myshop.model.SQLAdapter;

/**
 * 商品收藏表操作mapper
 * @author Administrator
 *
 */
public interface GoodsCollectMapper
{
	/**
	 * 插入收藏记录
	 * @param goodsCollect
	 */
	public void insertGoodsCollect(GoodsCollect goodsCollect);
	
	/**
	 * 插入收藏记录到分表
	 * @param goodsCollect
	 */
	public void insertGoodsCollectSplit(SQLAdapter sqlAdapter);
	
	/**
	 * 查询用户收藏
	 * @param id
	 * @return
	 */
	public List<GoodsCollect> queryGoodsCollect(Integer id);
	
	/**
	 * 从分表查询用户收藏
	 * @param id
	 * @return
	 */
	public List<GoodsCollect> queryGoodsCollectSplit(SQLAdapter sqlAdapter); 
}
