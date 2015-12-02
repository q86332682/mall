package com.myshop.service;

import java.util.List;

import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.User;

/**
 * 用户业务操作接口
 * @author Administrator
 *
 */
public interface UserService
{
	/**
	 * 用户注册
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * 用户登录
	 * @param user
	 */
	public User login(User user);
	
	/**
	 * 获得我的订单列表
	 * @param userId 用户id
	 */
	public List<Order> getMyOrderList(String userId);
	
	/**
	 * 购买商品
	 * @param order 订单
	 * @param OrdergoodsList 订单商品列表
	 */
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList);
}
