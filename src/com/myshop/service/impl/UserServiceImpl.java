package com.myshop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myshop.dao.GoodsCollectMapper;
import com.myshop.dao.GoodsMapper;
import com.myshop.dao.GoodsTagMapper;
import com.myshop.dao.GoodscommentMapper;
import com.myshop.dao.OrderMapper;
import com.myshop.dao.OrdergoodsMapper;
import com.myshop.dao.ScorelogMapper;
import com.myshop.dao.UserMapper;
import com.myshop.model.Goods;
import com.myshop.model.GoodsCollect;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
import com.myshop.model.Scorelog;
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
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodscommentMapper goodscommentMapper;
	
	@Autowired
	private GoodsTagMapper goodsTagMapper;
	
	@Autowired
	private GoodsCollectMapper goodsCollectMapper;
	
	@Autowired
	private ScorelogMapper scorelogMapper;
	
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
		pageModel.setTotalCount(orderMapper.queryOrderCount(pageModel));
		pageModel.setList(orderMapper.queryOrderPage(pageModel));
		return pageModel;
	}

	@Transactional
	@Override
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList, User user)
	{
		orderMapper.insertOrder(order);
		List<Integer> ids = new ArrayList<Integer>();
		for(Ordergoods o : OrdergoodsList)
		{
			o.setOrderId(order.getId());
			ids.add(o.getGoodsId());
		}
		ordergoodsMapper.insertOrdergoods(OrdergoodsList);
		
		goodsMapper.updateSellCount(OrdergoodsList);
		goodsMapper.updateStokc(OrdergoodsList);
		userMapper.updateOrderCount(order.getUserId());
		user.setOrderCount(user.getOrderCount() + 1);
		
		Scorelog scorelog = new Scorelog();
		scorelog.setUserId(order.getUserId());
		int socre = (int) order.getTotalprice();
		scorelog.setInfo("购买商品加" + socre  + "积分!");
		scorelog.setScore(socre);
		AddScore(scorelog);
		user.setScore(user.getScore() + socre);
		
		int gold = user.getGold() - socre <= 0 ? user.getGold() : socre; 
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", order.getUserId());
		map.put("gold", gold);
		userMapper.updateGold(map);
		user.setGold(user.getGold() - gold);
	}
	
	@Transactional
	@Override
	/**
	 * 发布评论
	 * @param goodscomment
	 */
	public void publishComment(Goodscomment goodscomment, User user)
	{
		goodscommentMapper.insertComment(goodscomment);
		goodsMapper.updateCommentCount(goodscomment.getGoodsId());
		userMapper.updateCommentCount(goodscomment.getUserId());
		user.setCommentCount(user.getCommentCount() + 1);
		
		Goods goods = goodsMapper.queryGoodsById(goodscomment.getGoodsId());
		int commentCount = goods.getCommentCount();
		Scorelog scorelog = new Scorelog();
		scorelog.setUserId(goodscomment.getUserId());
		if(commentCount == 1)
		{
			scorelog.setInfo("首次评论加50积分!");
			scorelog.setScore(50);
		}
		else
		{
			scorelog.setInfo("评论加10积分!");
			scorelog.setScore(10);
		}
		
		AddScore(scorelog);
		user.setScore(user.getScore() + scorelog.getScore());
	}
	
	@Override
	/**
	 * 修改评论
	 * @param goodscomment
	 */
	public void updateComment(Goodscomment goodscomment)
	{
		goodscommentMapper.updateComment(goodscomment);
	}
	
	@Override
	/**
	 * 添加商品标签
	 * @param tag
	 */
	public void addGoodsTag(GoodsTag tag)
	{
		goodsTagMapper.insertTag(tag);
	}
	
	@Override
	/**
	 * 更新标签点击数
	 * @param id
	 */
	public void updateGoodsTag(Integer id)
	{
		goodsTagMapper.updateTagCount(id);
	}
	
	@Transactional
	@Override
	/**
	 * 收藏商品
	 * @param goodsCollect
	 */
	public void collectGoods(GoodsCollect goodsCollect, User user)
	{
		goodsCollectMapper.insertGoodsCollect(goodsCollect);
		userMapper.updateCollectCount(goodsCollect.getUserId());
		user.setCollectCount(user.getCollectCount() + 1);
	}
	
	@Transactional
	/**
	 * 增加积分
	 */
	public void AddScore(Scorelog scorelog)
	{
		userMapper.updateScore(scorelog);
		scorelogMapper.insertScorelog(scorelog);
	}
}
