package com.myshop.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户实体类
 * @author Administrator
 *
 */
public class User
{
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String addr;
	private String mobile;
	private Integer gold;
	private Integer score;
	private String level;
	private Integer percent;
	private Integer commentCount;
	private Integer collectCount;
	private Integer orderCount;
	private Date createtime;
	
	public User()
	{
		
	}
	
	public User(Map<String, String> map)
	{
		this.username = map.get("username");
		this.password = map.get("password");
		this.email = map.get("email");
		this.addr = map.get("addr");
		this.mobile = map.get("mobile");
		this.level = map.get("level");
		this.percent = Integer.parseInt(map.get("percent"));
	}
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
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
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public Integer getGold()
	{
		return gold;
	}
	public void setGold(Integer gold)
	{
		this.gold = gold;
	}

	public Integer getScore()
	{
		return score;
	}
	public void setScore(Integer score)
	{
		this.score = score;
	}
	public String getLevel()
	{
		return level;
	}
	public void setLevel(String level)
	{
		this.level = level;
	}
	public Integer getPercent()
	{
		return percent;
	}
	public void setPercent(Integer percent)
	{
		this.percent = percent;
	}
	public Integer getCommentCount()
	{
		return commentCount;
	}
	public void setCommentCount(Integer commentCount)
	{
		this.commentCount = commentCount;
	}
	public Integer getCollectCount()
	{
		return collectCount;
	}
	public void setCollectCount(Integer collectCount)
	{
		this.collectCount = collectCount;
	}
	public Integer getOrderCount()
	{
		return orderCount;
	}
	public void setOrderCount(Integer orderCount)
	{
		this.orderCount = orderCount;
	}
	
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", this.username);
		map.put("password", this.password);
		map.put("email", this.email);
		map.put("addr", this.addr);
		map.put("mobile", this.mobile);
		map.put("level", this.level);
		map.put("percent", this.percent + "");
		return map;
	}
}
