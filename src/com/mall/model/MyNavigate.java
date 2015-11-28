package com.mall.model;

import java.util.List;

import com.mall.generator.model.Navigate;

/**
 * 自定义导航列表实体类
 * @author Administrator
 *
 */
public class MyNavigate extends Navigate
{
	private List<MyNavigate> myChildNavigates;		//当前导航下的子集导航

	public List<MyNavigate> getMyChildNavigates()
	{
		return myChildNavigates;
	}

	public void setMyChildNavigates(List<MyNavigate> myChildNavigates)
	{
		this.myChildNavigates = myChildNavigates;
	}
}
