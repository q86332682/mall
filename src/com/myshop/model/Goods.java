package com.myshop.model;

import java.util.Date;
import java.util.List;

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
	private Integer stock;
	private Integer clickcount;
	private Integer sellcount;
	private Integer commentCount;
	private Integer categoryId;
	private Integer brandId;
	private Date createtime;
	private List<GoodsDesc>  goodsDesc;
	private List<GoodsTag> goodsTag;
	private Integer tagId;
	private Integer startRange;
	private Integer endRange;
	
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
	public Integer getBrandId()
	{
		return brandId;
	}
	public void setBrandId(Integer brandId)
	{
		this.brandId = brandId;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public Integer getStock()
	{
		return stock;
	}
	public void setStock(Integer stock)
	{
		this.stock = stock;
	}
	public Integer getCommentCount()
	{
		return commentCount;
	}
	public void setCommentCount(Integer commentCount)
	{
		this.commentCount = commentCount;
	}
	public List<GoodsDesc> getGoodsDesc()
	{
		return goodsDesc;
	}
	public void setGoodsDesc(List<GoodsDesc> goodsDesc)
	{
		this.goodsDesc = goodsDesc;
	}
	public List<GoodsTag> getGoodsTag()
	{
		return goodsTag;
	}
	public void setGoodsTag(List<GoodsTag> goodsTag)
	{
		this.goodsTag = goodsTag;
	}
	public Integer getTagId()
	{
		return tagId;
	}
	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}
	public Integer getStartRange()
	{
		return startRange;
	}
	public void setStartRange(Integer startRange)
	{
		this.startRange = startRange;
	}
	public Integer getEndRange()
	{
		return endRange;
	}
	public void setEndRange(Integer endRange)
	{
		this.endRange = endRange;
	}
}
