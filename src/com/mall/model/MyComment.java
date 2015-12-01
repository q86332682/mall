package com.mall.model;

import com.mall.generator.model.Comment;
import com.mall.generator.model.User;

/**
 * 自定义评论实体类
 * @author Administrator
 *
 */
public class MyComment extends Comment
{
	private User user;						//评论的用户

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
