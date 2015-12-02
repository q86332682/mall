package com.myshop.dao;

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
}
