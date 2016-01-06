package com.myshop.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 分数日志实体类
 * @author Administrator
 *
 */
public class Scorelog
{
	private Integer id;
	private String info;
	private Integer score;
	private Integer userId;
	private Date createtime;
	
	public Scorelog()
	{
		
	}
	
	public Scorelog(Map<String, String> map)
	{
		this.info = map.get("info");
		this.score = Integer.parseInt(map.get("score"));
		this.userId = Integer.parseInt(map.get("userId"));
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	public Integer getScore()
	{
		return score;
	}
	public void setScore(Integer score)
	{
		this.score = score;
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
		map.put("info", this.info);
		map.put("score", this.score + "");
		map.put("userId", this.userId + "");
//		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		map.put("createtime", sdf.format(this.createtime));
		return map;
	}
}
