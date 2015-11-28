package com.mall.dao;

import java.util.List;

import com.mall.generator.dao.CategoryMapper;
import com.mall.model.MyCategory;

/**
 * 自定义分类表操作Mapper
 * @author Administrator
 *
 */
public interface MyCategoryMapper
{
	/**
	 * 查询分类列表
	 * @return
	 */
	public List<MyCategory> queryCategory();
}
