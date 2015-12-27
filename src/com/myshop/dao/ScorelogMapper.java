package com.myshop.dao;

import java.util.List;

import com.myshop.model.SQLAdapter;
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
	 * 插入积分日志分表
	 * @param Scorelog
	 */
	public void insertScorelogSplit(SQLAdapter sqlAdapter);
	
	/**
	 * 查询积分日志
	 * @return
	 */
	public List<Scorelog> queryScorelog(Integer id);
	
	/**
	 * 查询积分日志分表
	 * @return
	 */
	public List<Scorelog> queryScorelogSplit(SQLAdapter sqlAdapter);
}
