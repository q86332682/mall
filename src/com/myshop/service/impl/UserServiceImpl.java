package com.myshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.OrderMapper;
import com.myshop.dao.OrdergoodsMapper;
import com.myshop.dao.UserMapper;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.User;
import com.myshop.service.UserService;

@Service
/**
 * 用户业务实现类
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	@Override
	public void register(User user)
	{
		userMapper.insertUser(user);
	}

	@Override
	public User login(User user)
	{
		return userMapper.queryUser(user);
	}

	@Override
	public List<Order> getMyOrderList(String userId)
	{
		List<Order> orderList = orderMapper.queryOrderList(userId);
		return orderList;
	}

	@Override
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList)
	{
		orderMapper.insertOrder(order);
		ordergoodsMapper.insertOrdergoods(OrdergoodsList);
	}
}
