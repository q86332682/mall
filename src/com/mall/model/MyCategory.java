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

	public List<MyCategory> getMyChildCategory()
	{
		return MyChildCategory;
	}

	public void setMyChildCategory(List<MyCategory> myChildCategory)
	{
		MyChildCategory = myChildCategory;
	}
}
