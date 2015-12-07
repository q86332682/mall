package com.myshop.dao;

import com.myshop.model.GoodsTag;

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
	 * 更新标签点击数
	 * @param id
	 */
	public void updateTagCount(Integer id);
}
