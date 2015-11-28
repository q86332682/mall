package com.mall.model;

import java.util.List;

import com.mall.generator.model.Category;
import com.mall.generator.model.Goods;

/**
 * 自定义商品分类
 * @author Administrator
 *
 */
public class MyCategory extends Category
{
	private List<MyCategory> MyChildCategory;		//子级分类
	private List<Goods> categoryGoods;				//分类下的商品

	public List<MyCategory> getMyChildCategory()
	{
		return MyChildCategory;
	}

	public void setMyChildCategory(List<MyCategory> myChildCategory)
	{
		MyChildCategory = myChildCategory;
	}

	public List<Goods> getCategoryGoods()
	{
		return categoryGoods;
	}

	public void setCategoryGoods(List<Goods> categoryGoods)
	{
		this.categoryGoods = categoryGoods;
	}
}
