package com.myshop.model;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", this.id + "");
		map.put("name", this.name);
		map.put("count", this.count + "");
		map.put("userId", this.userId + "");
		map.put("goodsId", this.goodsId + "");
		return map;
	}
}
