package com.myshop.model;

import java.util.Date;

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
	private Integer commentCount;
	private Integer collectCount;
	private Integer orderCount;
	private Date createtime;
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
}
