package com.myshop.model;

public class SQLAdapter
{
	private Integer id;
	private String sql;
	
	public SQLAdapter(String sql)
	{
		this.sql = sql;
	}
	

	public Integer getId()
	{
		return id;
	}



	public void setId(Integer id)
	{
		this.id = id;
	}



	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}
}
