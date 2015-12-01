package com.mall.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.MyGoodsMapper;
import com.mall.model.MyComment;
import com.mall.model.MyConsultation;
import com.mall.model.MyGoods;
import com.mall.service.GoodsDetailsService;

@Service
/**
 * 商品详情业务处理实现类
 * @author Administrator
 *
 */
public class GoodsDetailsServiceImpl implements GoodsDetailsService
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MyGoodsMapper myGoodsMapper;
	
	@Override
	/**
	 * 获得商品详情
	 * @param goodsId 商品id
	 */
	public MyGoods getGoodsDetails(String goodsId)
	{
		LOG.info("获得商品详情");
		MyGoods goods = myGoodsMapper.queryGoodsDetails(goodsId);
		return goods;
	}
	
	/**
	 * 获得商品评论列表
	 * @param goodsId 商品id
	 */
	public List<MyComment> getComments(String goodsId)
	{
		LOG.info("获得商品评论列表");
		List<MyComment> list = myGoodsMapper.queryComments(goodsId);
		return list;
	}
	
	/**
	 * 获得商品咨询列表
	 * @param goodsId 商品id
	 */
	public List<MyConsultation> getConsultations(String goodsId)
	{
		LOG.info("获得商品咨询列表");
		List<MyConsultation> list = myGoodsMapper.queryConsultations(goodsId);
		return list;
	}
}
