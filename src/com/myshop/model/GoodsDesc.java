package com.myshop.model;

/**
 * 商品信息描述实体类
 * @author Administrator
 *
 */
public class GoodsDesc
{
	private Integer id;
	private String type;
	private String name;
	private String val;
	private Integer goodsId;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getVal()
	{
		return val;
	}
	public void setVal(String val)
	{
		this.val = val;
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
