package com.myshop.dao;

import com.myshop.model.GoodsTag;
import com.myshop.model.SQLAdapter;

/**
 * 商品标签表操作Mapper
 * @author Administrator
 *
 */
public interface GoodsTagMapper
{
	/**
	 * 插入标签
	 * @param goodsTag
	 */
	public void insertTag(GoodsTag goodsTag);
	
	/**
	 * 插入标签分表
	 * @param goodsTag
	 */
	public void insertTagSplit(SQLAdapter sqlAdapter);
	
	/**
	 * 更新标签点击数
	 * @param id
	 */
	public void updateTagCount(Integer id);
	
	/**
	 * 更新标签点击数分表
	 * @param id
	 */
	public void updateTagCountSplit(SQLAdapter sqlAdapter);
}
