package com.myshop.model;

/**
 * 热搜商品搜索实体类
 * @author Administrator
 *
 */
public class Hotsearch
{
	private Integer id;
	private String name;
	private Integer count;
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
}
