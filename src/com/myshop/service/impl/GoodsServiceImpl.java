package com.myshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.dao.BrandMapper;
import com.myshop.dao.CategoryMapper;
import com.myshop.dao.GoodsCollectMapper;
import com.myshop.dao.GoodsMapper;
import com.myshop.dao.GoodsTagMapper;
import com.myshop.dao.GoodscommentMapper;
import com.myshop.dao.HotsearchMapper;
import com.myshop.dao.UserMapper;
import com.myshop.model.Brand;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.GoodsCollect;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.model.Hotsearch;
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
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Autowired
	private GoodscommentMapper goodscommentMapper;

	@Override
	public List<Hotsearch> getHotsearchList()
	{
		return hotsearchMapper.queryHotsearchList();
	}

	@Override
	public PageModel<Goods> searchGoodsList(PageModel<Goods> pageModel)
	{
		List<Goods> goodsList = goodsMapper.queryGoodsByName(pageModel);
		hotsearchMapper.insertAndUpdateHotSearch(pageModel.getPageQuery());
		pageModel.setTotalCount(goodsMapper.querySearchGoodsCount(pageModel.getPageQuery()));
		pageModel.setList(goodsList);
		return pageModel;
	}
	
	@Override
	public PageModel<Goods> getNewGoods(PageModel<Goods> pageModel)
	{
		pageModel.setTotalCount(goodsMapper.queryGoodsCount());
		pageModel.setList(goodsMapper.queryGoodsTime(pageModel));
		return pageModel;
	}

	@Override
	public List<Goods> getGoodsByPopRank()
	{
		return goodsMapper.queryGoodsClickcount();
	}
	
	@Override
	public PageModel<Goods> getGoodsByPopRank(PageModel<Goods> pageModel)
	{
		pageModel.setTotalCount(goodsMapper.queryGoodsCount());
		pageModel.setList(goodsMapper.queryGoodsClickcountPage(pageModel));
		return pageModel;
	}

	@Override
	public List<Goods> getGoodsByRecommend()
	{
		return goodsMapper.queryGoodsClickcount();
	}
	
	@Override
	public PageModel<Goods> getGoodsByRecommend(PageModel<Goods> pageModel)
	{
		pageModel.setTotalCount(goodsMapper.queryGoodsCount());
		pageModel.setList(goodsMapper.queryGoodsClickcountPage(pageModel));
		return pageModel;
	}

	@Override
	public List<Goods> getGoodsBySellhot()
	{
		return goodsMapper.queryGoodsSellcount();
	}
	
	@Override
	public PageModel<Goods> getGoodsBySellhot(PageModel<Goods> pageModel)
	{
		pageModel.setTotalCount(goodsMapper.queryGoodsCount());
		pageModel.setList(goodsMapper.queryGoodsSellcountPage(pageModel));
		return pageModel;
	}

	@Override
	public PageModel<Goods> getGoodsByCategory(PageModel<Goods> pageModel)
	{
		pageModel.setTotalCount(goodsMapper.queryGoodsCategoryCount(pageModel.getPageQuery()));
		pageModel.setList(goodsMapper.queryGoodsByCategory(pageModel));
		return pageModel;
	}

	@Override
	public List<Category> getCategorylist()
	{
		return categoryMapper.queryCategoryList();
	}
	
	/**
	 * 通过id获得商品
	 * @param id
	 * @return
	 */
	public Goods getGoodsById(Integer id, HttpServletRequest req)
	{
		String submit = (String) req.getAttribute("submit");
		if("true".equals(submit))
			goodsMapper.updateClickCount(id);
		
		return goodsMapper.queryGoodsById(id);
	}
	
	/**
	 * 获得推荐分类商品
	 * @return
	 */
	public List<Category> getRecommendCategory()
	{
		return categoryMapper.queryRecommendCategory();
	}
	
	/**
	 * 获得推荐品牌
	 * @return
	 */
	public List<Brand> getRecommendBrand()
	{
		return brandMapper.queryRecommendBrand();
	}
	
	/**
	 * 获得评论列表
	 * @param pageModel
	 * @return
	 */
	public PageModel<Goodscomment> getCommentList(PageModel<Goodscomment> pageModel)
	{
		pageModel.setTotalCount(goodscommentMapper.queryCommentCount(pageModel.getPageQuery()));
		pageModel.setList(goodscommentMapper.queryCommentList(pageModel));
		return pageModel;
	}
}
