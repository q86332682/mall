package com.myshop.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌实体类
 * @author Administrator
 *
 */
public class Brand implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", this.id + "");
		map.put("name", this.name);
		map.put("logo", this.logo);
		map.put("url", this.url);
		return map;
	}
}
