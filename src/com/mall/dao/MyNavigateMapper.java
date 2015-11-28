package com.mall.dao;

import java.util.List;

import com.mall.generator.dao.NavigateMapper;
import com.mall.model.MyNavigate;

/**
 * 自定义导航表操作Mapper
 * @author Administrator
 *
 */
public interface MyNavigateMapper
{
	/**
	 * 查询导航列表
	 * @return
	 */
	public List<MyNavigate> queryNavigates();
}
