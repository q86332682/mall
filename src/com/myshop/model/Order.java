package com.myshop.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	
	public Order()
	{
		
	}
	
	public Order(Map<String, String> map)
	{
		this.id = Integer.parseInt(map.get("id"));
		this.name = map.get("name");
		this.addr = map.get("addr");
		this.mobile = map.get("mobile");
		this.totalprice = Float.parseFloat(map.get("totalprice"));
		this.payWay = map.get("payWay");
		this.state = map.get("state");
		this.userId = Integer.parseInt(map.get("userId"));
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			this.createtime = sdf.parse(map.get("createtime"));
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", this.id + "");
		map.put("name", this.name);
		map.put("addr", this.addr);
		map.put("mobile", this.mobile);
		map.put("totalprice", this.totalprice + "");
		map.put("payWay", "支付宝");
		map.put("state", "已完成");
		map.put("userId", this.userId + "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("createtime", sdf.format(new Date()));
		return map;
	}
}
