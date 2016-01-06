package com.myshop.dao;

import java.util.List;

import com.myshop.model.Brand;

/**
 * 品牌表操作Mapper
 * @author Administrator
 *
 */
public interface BrandMapper
{
	/**
	 * 查询推荐品牌
	 * @return
	 */
	public List<Brand> queryRecommendBrand();
	
	/**
	 * 载入数据
	 * @return
	 */
	public List<Brand> loadData();
}
