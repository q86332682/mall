package com.myshop.dao;

import java.util.List;

import com.myshop.model.Ordergoods;

/**
 * 订单商品表操作Mapper
 * @author Administrator
 *
 */
public interface OrdergoodsMapper
{
	/**
	 * 批量插入订单商品列表
	 * @param OrdergoodsList
	 */
	public void insertOrdergoods(List<Ordergoods> OrdergoodsList);
}
