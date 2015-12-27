package com.myshop.dao;

import java.util.List;

import com.myshop.model.Goodscomment;
import com.myshop.model.PageModel;
import com.myshop.model.SQLAdapter;

/**
 * 商品评论表操作Mapper
 * @author Administrator
 *
 */
public interface GoodscommentMapper
{
	/**
	 * 查询评论数量
	 * @param comment
	 * @return
	 */
	public int queryCommentCount(Goodscomment comment);
	
	/**
	 * 查询评论列表
	 * @param pageModel
	 * @return
	 */
	public List<Goodscomment> queryCommentList(PageModel<Goodscomment> pageModel);
	
	/**
	 * 查询评论列表分表
	 * @param pageModel
	 * @return
	 */
	public List<Goodscomment> queryCommentListSplit(SQLAdapter sqlAdapter);
	
	/**
	 * 插入评论
	 * @param comment
	 */
	public void insertComment(Goodscomment comment);
	
	/**
	 * 插入评论分表
	 * @param comment
	 */
	public void insertCommentSplit(SQLAdapter sqlAdapter);
	
	/**
	 * 修改评论
	 * @param comment
	 */
	public void updateComment(Goodscomment comment);
	
	/**
	 * 修改评论分表
	 * @param comment
	 */
	public void updateCommentSplit(SQLAdapter sqlAdapter);
}
