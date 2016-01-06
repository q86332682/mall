package com.myshop.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品评论实体类
 * @author Administrator
 *
 */
public class Goodscomment
{
	private Integer id;
	private Integer userId;
	private Integer goodsId;
	private String username;
	private String userlevel;
	private Integer score;
	private String content;
	private Date createtime;
	
	public Goodscomment()
	{
		
	}
	
	public Goodscomment(Map<String, String> map)
	{
		this.id = Integer.parseInt(map.get("id"));
		this.username = map.get("username");
		this.userlevel = map.get("userlevel");
		this.score = Integer.parseInt(map.get("score"));
		this.content = map.get("content");
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
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUserlevel()
	{
		return userlevel;
	}
	public void setUserlevel(String userlevel)
	{
		this.userlevel = userlevel;
	}
	public Integer getScore()
	{
		return score;
	}
	public void setScore(Integer score)
	{
		this.score = score;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
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
		map.put("username", this.username);
		map.put("userlevel", this.userlevel + "");
		map.put("score", this.score + "");
		map.put("content", this.content);
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("createtime", sdf.format(new Date()));
		return map;
	}
}
