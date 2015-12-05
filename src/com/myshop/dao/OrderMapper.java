package com.myshop.dao;

import java.util.List;

import com.myshop.model.Order;
import com.myshop.model.PageModel;

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
	public List<Order> queryOrderList(Integer userId);
	
	/**
	 * 查询订单数量
	 * @param pageModel
	 * @return
	 */
	public int queryOrderCount(PageModel<Order> pageModel);
	
	/**
	 * 查询订单分页
	 * @param pageModel 分页模型
	 * @return
	 */
	public List<Order> queryOrderPage(PageModel<Order> pageModel);
	
	/**
	 * 插入订单
	 * @param order
	 */
	public void insertOrder(Order order);
}
