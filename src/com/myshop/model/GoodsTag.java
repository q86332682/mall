package com.myshop.model;

/**
 * 商品标签实体类
 * @author Administrator
 *
 */
public class GoodsTag
{
	private Integer id;
	private String name;
	private Integer count;
	private Integer userId;
	private Integer goodsId;
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
	public Integer getCount()
	{
		return count;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public Integer getGoodsId()
	{
		return goodsId;
	}
	public void setGoodsId(Integer goodsId)
	{
		this.goodsId = goodsId;
	}
}
