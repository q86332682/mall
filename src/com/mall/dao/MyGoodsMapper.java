package com.mall.dao;

import java.util.List;

import com.mall.generator.model.Goods;
import com.mall.model.MyComment;
import com.mall.model.MyConsultation;
import com.mall.model.MyGoods;
import com.mall.model.MyRecommendGoods;

/**
 * 自定义商品表操作Mapper
 * @author Administrator
 *
 */
public interface MyGoodsMapper
{
	/**
	 * 查询热卖和广告下面商品查询
	 * @return
	 */
	public List<Goods> queryHotSellAndAdvertDownGoods();
	
	/**
	 * 查询首页推荐商品
	 */
	public List<MyRecommendGoods> queryHomeRecommendGoods();
	
	/**
	 * 查询商品详情
	 * @param goodsId 商品id
	 * @return
	 */
	public MyGoods queryGoodsDetails(String goodsId);
	
	/**
	 * 查询商品评论列表
	 * @param goodsId 商品id
	 */
	public List<MyComment> queryComments(String goodsId);
	
	/**
	 * 查询商品咨询列表
	 * @param goodsId 商品id
	 */
	public List<MyConsultation> queryConsultations(String goodsId);
}
