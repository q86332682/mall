package com.myshop.dao;

import java.util.List;

import com.myshop.model.Order;

/**
 * 订单表操作Mapper
 * @author Administrator
 *
 */
public interface OrderMapper
{
	/**
	 * 查询订单列表
	 * @param userId 用户id
	 * @return
	 */
	public List<Order> queryOrderList(String userId);
	
	/**
	 * 插入订单
	 * @param order
	 */
	public void insertOrder(Order order);
}
