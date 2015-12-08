package com.myshop.model;

/**
 * 用户会员等级实体类
 * @author Administrator
 *
 */
public class Userlevel
{
	private Integer id;
	private String name;
	private Integer score;
	private Integer percent;
	private String desc;
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
	public Integer getScore()
	{
		return score;
	}
	public void setScore(Integer score)
	{
		this.score = score;
	}
	public Integer getPercent()
	{
		return percent;
	}
	public void setPercent(Integer percent)
	{
		this.percent = percent;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
}
