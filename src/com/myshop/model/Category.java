package com.myshop.model;

import java.util.List;

/**
 * 商品分类实体类
 * @author Administrator
 *
 */
public class Category
{
	private Integer id;
	private String name;
	private Integer level;
	private Integer pid;
	private Integer isRecommend;
	private List<Category> childCategoryList;
	private List<Goods> goods;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getLevel()
	{
		return level;
	}
	public void setLevel(Integer level)
	{
		this.level = level;
	}
	public Integer getPid()
	{
		return pid;
	}
	public void setPid(Integer pid)
	{
		this.pid = pid;
	}
	public Integer getIsRecommend()
	{
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend)
	{
		this.isRecommend = isRecommend;
	}
	public List<Category> getChildCategoryList()
	{
		return childCategoryList;
	}
	public void setChildCategoryList(List<Category> childCategoryList)
	{
		this.childCategoryList = childCategoryList;
	}
	public List<Goods> getGoods()
	{
		return goods;
	}
	public void setGoods(List<Goods> goods)
	{
		this.goods = goods;
	}
}
