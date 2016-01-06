package com.myshop.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类实体类
 * @author Administrator
 *
 */
public class Category implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", this.id + "");
		map.put("name", this.name);
		return map;
	}
}
