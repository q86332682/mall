package com.myshop.dao;

import java.util.Map;

import com.myshop.model.Scorelog;
import com.myshop.model.User;

/**
 * 用户表操作Mapper
 * @author Administrator
 *
 */
public interface UserMapper
{
	/**
	 * 插入用户
	 * @param user
	 */
	public void insertUser(User user);
	
	/**
	 * 查询用户
	 * @param user
	 */
	public User queryUser(User user);
	
	/**
	 * 更新评论数
	 * @param id
	 */
	public void updateCommentCount(Integer id);
	
	/**
	 * 更新收藏数
	 * @param id
	 */
	public void updateCollectCount(Integer id);
	
	/**
	 * 更新积分
	 * @param score
	 */
	public void updateScore(Scorelog scorelog);
	
	/**
	 * 更新订单数
	 * @param id
	 */
	public void updateOrderCount(Integer id);
	
	/**
	 * 更新金钱
	 * @param map
	 */
	public void updateGold(Map<String, Integer> map);
}
