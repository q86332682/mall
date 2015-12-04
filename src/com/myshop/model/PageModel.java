package com.myshop.model;

import java.util.List;

/**
 * 分页列表模型
 * @author Administrator
 *
 * @param <E>
 */
public class PageModel<E>
{
	private int pageNow;
	private int startIndex;
	private int pageSize;
	private int totalPage;
	private int totalCount;
	private E pageQuery;
	private List<E> list;
	
	public PageModel()
	{
		pageNow = 1;
		startIndex = 0;
		pageSize = 10;
		totalPage = 1;
		totalCount = 1;
		pageQuery = null;
		list = null;
	}
	
	public int getPageNow()
	{
		return pageNow;
	}
	public void setPageNow(int pageNow)
	{
		this.pageNow = pageNow;
		setStartIndex(this.pageNow, this.pageSize);
	}
	public int getStartIndex()
	{
		return startIndex;
	}
	private void setStartIndex(int pageNow, int pageSize)
	{
		if(pageNow > 0 && pageSize > 0)
			this.startIndex = (pageNow -1) * pageSize;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
		setStartIndex(this.pageNow, this.pageSize);
	}
	public int getTotalPage()
	{
		return totalPage;
	}
	private void setTotalPage(int totalCount, int pageSize)
	{
		if(totalCount > 0 && pageSize > 1)
		{
			this.totalPage = (totalCount + pageSize - 1) / pageSize;
		}
	}
	public int getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
		setTotalPage(totalCount, getPageSize());
	}
	public E getPageQuery()
	{
		return pageQuery;
	}
	public void setPageQuery(E pageQuery)
	{
		this.pageQuery = pageQuery;
	}
	public List<E> getList()
	{
		return list;
	}
	public void setList(List<E> list)
	{
		this.list = list;
	}
}
