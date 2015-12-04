package com.myshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.CategoryMapper;
import com.myshop.dao.GoodsMapper;
import com.myshop.dao.HotsearchMapper;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Hotsearch;
import com.myshop.model.Order;
import com.myshop.model.PageModel;
import com.myshop.service.GoodsService;

@Service
/**
 * 商品业务实现类
 * @author Administrator
 *
 */
public class GoodsServiceImpl implements GoodsService
{
	@Autowired
	private HotsearchMapper hotsearchMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	

	@Override
	public List<Hotsearch> getHotsearchList()
	{
		return hotsearchMapper.queryHotsearchList();
	}

	@Override
	public List<Goods> searchGoodsList(String name)
	{
		List<Goods> goodsList = goodsMapper.queryGoodsByName(name);
		hotsearchMapper.insertAndUpdateHotSearch(name);
		return goodsList;
	}
	
	@Override
	public PageModel<Goods> getNewGoods(PageModel<Order> pageModel)
	{
		return goodsMapper.queryGoodsTime(pageModel);
	}

	@Override
	public List<Goods> getGoodsByPopRank()
	{
		return goodsMapper.queryGoodsClickcount();
	}

	@Override
	public List<Goods> getGoodsByRecommend()
	{
		return goodsMapper.queryGoodsClickcount();
	}

	@Override
	public List<Goods> getGoodsBySellhot()
	{
		return goodsMapper.queryGoodsSellcount();
	}

	@Override
	public List<Goods> getGoodsByCategory(String categoryName)
	{
		return goodsMapper.queryGoodsByCategory(categoryName);
	}

	@Override
	public List<Category> getCategorylist()
	{
		return categoryMapper.queryCategoryList();
	}
}
