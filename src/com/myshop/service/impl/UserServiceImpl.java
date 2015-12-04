package com.myshop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.OrderMapper;
import com.myshop.dao.OrdergoodsMapper;
import com.myshop.dao.UserMapper;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
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
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	@Override
	public void register(User user)
	{
		User isExist = userMapper.queryUser(user);
		if(isExist == null)
		{
			userMapper.insertUser(user);
		}
		else
		{
			LOG.info("用户名: " + user.getUsername() + ", 已经存在!!!");
		}
	}

	@Override
	public User login(User user)
	{
		return userMapper.queryUser(user);
	}

	@Override
	public PageModel<Order> getMyOrderList(PageModel<Order> pageModel)
	{
		PageModel<Order> resultPageModel = orderMapper.queryOrderPage(pageModel);
		return resultPageModel;
	}

	@Override
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList)
	{
		orderMapper.insertOrder(order);
		ordergoodsMapper.insertOrdergoods(OrdergoodsList);
	}
}
