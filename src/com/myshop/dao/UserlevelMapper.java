package com.myshop.dao;

import java.util.List;

import com.myshop.model.Userlevel;

/**
 * 用户等级表操作Mapper
 * @author Administrator
 *
 */
public interface UserlevelMapper
{
	/**
	 * 查询用户等级列表
	 * @return
	 */
	public List<Userlevel> queryUserlevelList();
}
