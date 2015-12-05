package com.myshop.model;

/**
 * 订单商品实体类
 * @author Administrator
 *
 */
public class Ordergoods
{
	private Integer id;
	private String name;
	private float marketprice;
	private float sellprice;
	private Integer num;
	private Integer orderId;
	private Integer goodsId;
	
	public Ordergoods()
	{
		
	}
	
	public Ordergoods(Goods goods)
	{
		this.name = goods.getName();
		this.marketprice = goods.getMarketprice();
		this.sellprice = goods.getSellprice();
		this.num = 1;
		this.goodsId = goods.getId();
	}
	
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
	public Integer getNum()
	{
		return num;
	}
	public void setNum(Integer num)
	{
		this.num = num;
	}
	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
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
