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
}
