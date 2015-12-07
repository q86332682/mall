package com.myshop.model;

import java.util.Date;

/**
 * 商品收藏实体类
 * @author Administrator
 *
 */
public class GoodsCollect
{
	private Integer id;
	private Integer userId;
	private Integer goodsId;
	private String goodsname;
	private String goodsimg;
	private float goodsMarketprice;
	private float goodsSellprice;
	private Date createtime;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
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
	public String getGoodsname()
	{
		return goodsname;
	}
	public void setGoodsname(String goodsname)
	{
		this.goodsname = goodsname;
	}
	public String getGoodsimg()
	{
		return goodsimg;
	}
	public void setGoodsimg(String goodsimg)
	{
		this.goodsimg = goodsimg;
	}
	public float getGoodsMarketprice()
	{
		return goodsMarketprice;
	}
	public void setGoodsMarketprice(float goodsMarketprice)
	{
		this.goodsMarketprice = goodsMarketprice;
	}
	public float getGoodsSellprice()
	{
		return goodsSellprice;
	}
	public void setGoodsSellprice(float goodsSellprice)
	{
		this.goodsSellprice = goodsSellprice;
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
