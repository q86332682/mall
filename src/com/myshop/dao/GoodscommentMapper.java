package com.myshop.dao;

import java.util.List;

import com.myshop.model.Goodscomment;
import com.myshop.model.PageModel;

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
	 * 插入评论
	 * @param comment
	 */
	public void insertComment(Goodscomment comment);
	
	/**
	 * 修改评论
	 * @param comment
	 */
	public void updateComment(Goodscomment comment);
}
