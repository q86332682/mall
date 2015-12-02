package com.myshop.dao;

import java.util.List;

import com.myshop.model.Hotsearch;

/**
 * 热搜商品搜索表操作Mapper
 * @author Administrator
 *
 */
public interface HotsearchMapper
{
	/**
	 * 查询热销商品搜索列表
	 * @return
	 */
	public List<Hotsearch> queryHotsearchList();
	
	/**
	 * 插入或更新热销商品搜索
	 * @param name 搜索名
	 */
	public void insertAndUpdateHotSearch(String name);
}
