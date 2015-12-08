package com.myshop.dao;

import java.util.List;

import com.myshop.model.Scorelog;

/**
 * 积分日志表操作Mapper
 * @author Administrator
 *
 */
public interface ScorelogMapper
{
	/**
	 * 插入积分日志
	 * @param Scorelog
	 */
	public void insertScorelog(Scorelog scorelog);
	
	/**
	 * 查询积分日志
	 * @return
	 */
	public List<Scorelog> queryScorelog(Integer id);
}
