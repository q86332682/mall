package com.myshop.model;

import java.util.List;

/**
 * 品牌实体类
 * @author Administrator
 *
 */
public class Brand
{
	private Integer id;
	private String name;
	private String logo;
	private String url;
	private Integer isRecommend;
	private List<Goods> goods;
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
	public String getLogo()
	{
		return logo;
	}
	public void setLogo(String logo)
	{
		this.logo = logo;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public Integer getIsRecommend()
	{
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend)
	{
		this.isRecommend = isRecommend;
	}
	public List<Goods> getGoods()
	{
		return goods;
	}
	public void setGoods(List<Goods> goods)
	{
		this.goods = goods;
	}
}
