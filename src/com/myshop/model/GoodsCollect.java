package com.myshop.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	
	public GoodsCollect()
	{
		
	}
	
	public GoodsCollect(Map<String, String> map)
	{
		this.id = Integer.parseInt(map.get("id"));
		this.userId = Integer.parseInt(map.get("userId"));
		this.goodsId = Integer.parseInt(map.get("goodsId"));
		this.goodsname = map.get("goodsname");
		this.goodsimg = map.get("goodsimg");
		this.goodsMarketprice = Float.parseFloat(map.get("goodsMarketprice"));
		this.goodsSellprice = Float.parseFloat(map.get("goodsSellprice"));
	}
	
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
	
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", this.id + "");
		map.put("userId", this.userId + "");
		map.put("goodsId", this.goodsId + "");
		map.put("goodsname", this.goodsname);
		map.put("goodsimg", this.goodsimg);
		map.put("goodsMarketprice", this.goodsMarketprice + "");
		map.put("goodsSellprice", this.goodsSellprice + "");
		return map;
	}
}
