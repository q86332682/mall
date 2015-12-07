package com.myshop.dao;

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
}
