package com.myshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.myshop.model.SQLAdapter;
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
		if(req != null)
		{
			String submit = (String) req.getAttribute("submit");
			if("true".equals(submit))
				goodsMapper.updateClickCount(id);
		}
		
		Goods goods = null;
		//未分表写法
		//goods = goodsMapper.queryGoodsById(id);
		
		//分表写法
		String sql = " select g.id, g.img, g.`name`, g.marketprice, g.sellprice, g.stock, g.clickcount, g.sellcount, g.commentCount, " +
					 " gd.desc, gd.id gdid, gd.`name` gdname, gd.val gdval, t.id tid, t.`name` tname, t.count tcount " +
					 " from goods g " +
					 " LEFT JOIN goodsdesc" + (id % 10) + " gd ON g.id = gd.goodsId " +
					 " LEFT JOIN goodstag" + (id % 10) + " t ON g.id = t.goodsId " +
					 " where g.id = " + id;
		goods = goodsMapper.queryGoodsByIdSplit(new SQLAdapter(sql));
		return goods;
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
		//未分表写法
//		pageModel.setTotalCount(goodscommentMapper.queryCommentCount(pageModel.getPageQuery()));
//		pageModel.setList(goodscommentMapper.queryCommentList(pageModel));
		
		//分表写法
		Integer goodsId = pageModel.getPageQuery().getGoodsId();
		pageModel.setTotalCount(goodscommentMapper.queryCommentCount(pageModel.getPageQuery()));
		String sql = " SELECT c.id, c.username, c.userlevel, c.score, c.content, c.createtime FROM `goodscomment" + (goodsId % 10) + "` c " +
				" where c.goodsId = " + goodsId + " AND c.createtime <= (SELECT createtime from `goodscomment` where goodsId = " + goodsId + " ORDER BY createtime DESC LIMIT " + pageModel.getStartIndex() + ", 1) " +
				" ORDER BY c.createtime DESC LIMIT " + pageModel.getPageSize();
		pageModel.setList(goodscommentMapper.queryCommentListSplit(new SQLAdapter(sql)));
		
		return pageModel;
	}
	
	/**
	 * 精确查找
	 * @param pageModel
	 * @return
	 */
	public PageModel<Goods> preciseSearch(PageModel<Goods> pageModel)
	{
		pageModel.setTotalCount(goodsMapper.preciseSearchCount(pageModel.getPageQuery()));
		pageModel.setList(goodsMapper.preciseSearch(pageModel));
		return pageModel;
	}
}
