package com.myshop.dao;

import java.util.List;

import com.myshop.model.Category;

/**
 * 分类表操作Mapper
 * @author Administrator
 *
 */
public interface CategoryMapper
{
	/**
	 * 查询分类列表
	 * @return
	 */
	public List<Category> queryCategoryList();
	
	/**
	 * 查询推荐分类
	 */
	public List<Category> queryRecommendCategory();
	
	/**
	 * 载入数据
	 */
	public List<Category> loadData();
}
