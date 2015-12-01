package com.mall.model;

import java.util.List;

import com.mall.generator.model.Goods;
import com.mall.generator.model.Goodsdesc;

/**
 * 商品位置
 * @author Administrator
 *
 */
public class MyGoods extends Goods
{
	private String pos;							//商品当前位置
	private List<Goodsdesc> goodsdescs;			//商品介绍

	public String getPos()
	{
		return pos;
	}

	public void setPos(String pos)
	{
		this.pos = pos;
	}

	public List<Goodsdesc> getGoodsdescs()
	{
		return goodsdescs;
	}

	public void setGoodsdescs(List<Goodsdesc> goodsdescs)
	{
		this.goodsdescs = goodsdescs;
	}
}
