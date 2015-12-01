package com.mall.model;

import com.mall.generator.model.Consultation;
import com.mall.generator.model.User;

/**
 * 自定义咨询实体类
 * @author Administrator
 *
 */
public class MyConsultation extends Consultation
{
	private User user;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
