package com.myshop.service;

import java.util.List;

import com.myshop.model.GoodsCollect;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
import com.myshop.model.Scorelog;
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
	public PageModel<Order> getMyOrderList(PageModel<Order> pageModel);
	
	/**
	 * 购买商品
	 * @param order 订单
	 * @param OrdergoodsList 订单商品列表
	 */
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList, User user);
	
	
	/**
	 * 发布评论
	 * @param goodscomment
	 */
	public void publishComment(Goodscomment goodscomment, User user);
	
	/**
	 * 修改评论
	 * @param goodscomment
	 */
	public void updateComment(Goodscomment goodscomment);
	
	/**
	 * 添加商品标签
	 * @param tag
	 */
	public void addGoodsTag(GoodsTag tag);
	
	/**
	 * 更新标签点击数
	 * @param id
	 */
	public void updateGoodsTag(Integer id);
	
	/**
	 * 收藏商品
	 * @param goodsCollect
	 */
	public void collectGoods(GoodsCollect goodsCollect, User user);
	
	/**
	 * 增加积分
	 */
	public void AddScore(Scorelog scorelog);
}
