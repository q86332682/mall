package com.myshop.dao;

import com.myshop.model.GoodsCollect;

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
}
