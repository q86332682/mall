package com.mall.model;

import java.util.List;

import com.mall.generator.model.Goods;

/**
 * 自定义推荐商品实体类
 * @author Administrator
 *
 */
public class MyRecommendGoods
{
	private Integer categoryId;
	private String categoryName;
	private String categoryImg;
	private List<MyCategory> childCategorys;
	private List<Goods> recommendGoods;
	public Integer getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}
	public String getCategoryName()
	{
		return categoryName;
	}
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	public String getCategoryImg()
	{
		return categoryImg;
	}
	public void setCategoryImg(String categoryImg)
	{
		this.categoryImg = categoryImg;
	}
	public List<MyCategory> getChildCategorys()
	{
		return childCategorys;
	}
	public void setChildCategorys(List<MyCategory> childCategorys)
	{
		this.childCategorys = childCategorys;
	}
	public List<Goods> getRecommendGoods()
	{
		return recommendGoods;
	}
	public void setRecommendGoods(List<Goods> recommendGoods)
	{
		this.recommendGoods = recommendGoods;
	}
}
