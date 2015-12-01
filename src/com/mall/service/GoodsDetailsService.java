package com.mall.service;

import java.util.List;

import com.mall.model.MyComment;
import com.mall.model.MyConsultation;
import com.mall.model.MyGoods;

/**
 * 商品详情业务处理
 * @author Administrator
 *
 */
public interface GoodsDetailsService
{
	public MyGoods getGoodsDetails(String goodsId);
	
	public List<MyComment> getComments(String goodsId);
	
	public List<MyConsultation> getConsultations(String goodsId);
}
