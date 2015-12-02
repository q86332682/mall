package com.myshop.model;

import java.util.Date;

/**
 * 订单实体类
 * @author Administrator
 *
 */
public class Order
{
	private Integer id;
	private String name;
	private String addr;
	private String mobile;
	private float totalprice;
	private String payWay;
	private String state;
	private Integer userId;
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
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public float getTotalprice()
	{
		return totalprice;
	}
	public void setTotalprice(float totalprice)
	{
		this.totalprice = totalprice;
	}
	public String getPayWay()
	{
		return payWay;
	}
	public void setPayWay(String payWay)
	{
		this.payWay = payWay;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
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
