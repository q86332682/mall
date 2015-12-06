package com.myshop.model;

import java.util.Date;

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
}
