package com.myshop.model;

import java.util.Date;

/**
 * 商品实体类
 * @author Administrator
 *
 */
public class Goods
{
	private Integer id;
	private String name;
	private String img;
	private String desc;
	private float marketprice;
	private float sellprice;
	private Integer clickcount;
	private Integer sellcount;
	private Integer categoryId;
	private Date createtime;
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
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public float getMarketprice()
	{
		return marketprice;
	}
	public void setMarketprice(float marketprice)
	{
		this.marketprice = marketprice;
	}
	public float getSellprice()
	{
		return sellprice;
	}
	public void setSellprice(float sellprice)
	{
		this.sellprice = sellprice;
	}
	public Integer getClickcount()
	{
		return clickcount;
	}
	public void setClickcount(Integer clickcount)
	{
		this.clickcount = clickcount;
	}
	public Integer getSellcount()
	{
		return sellcount;
	}
	public void setSellcount(Integer sellcount)
	{
		this.sellcount = sellcount;
	}
	public Integer getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
}
