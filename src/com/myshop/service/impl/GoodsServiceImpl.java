package com.myshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.myshop.dao.BrandMapper;
import com.myshop.dao.CategoryMapper;
import com.myshop.dao.GoodsMapper;
import com.myshop.dao.GoodscommentMapper;
import com.myshop.dao.HotsearchMapper;
import com.myshop.model.Brand;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Goodscomment;
import com.myshop.model.Hotsearch;
import com.myshop.model.PageModel;
import com.myshop.model.SQLAdapter;
import com.myshop.service.GoodsService;
import com.myshop.thread.PreloadThread;
import com.myshop.util.ConfigUtil;
import com.myshop.util.RedisUtil;

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
	
	@SuppressWarnings("unused")
	@Override
	public PageModel<Goods> getNewGoods(PageModel<Goods> pageModel)
	{
		if(ConfigUtil.USE_CACHE && pageModel.getPageNow() <= ConfigUtil.CACHE_PAGESIZE)
		{
			Jedis jedis = RedisUtil.getJedis();
			pageModel.setTotalCount(Integer.parseInt(jedis.get("GoodsCount")));
			int start = pageModel.getPageNow() * pageModel.getPageSize() - pageModel.getPageSize();
			int stop = start + pageModel.getPageSize() - 1;
			List<String> ids = jedis.lrange("NewGoodsIds", start, stop);
			List<Goods> newGoodsList = new ArrayList<Goods>();
			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
				newGoodsList.add(new Goods(map));
			}
			pageModel.setList(newGoodsList);
			RedisUtil.returnResource(jedis);
		}
		else
		{
			pageModel.setTotalCount(goodsMapper.queryGoodsCount());
			pageModel.setList(goodsMapper.queryGoodsTime(pageModel));
		}
		
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
		List<Goods> goodslist = null;
		
		if(ConfigUtil.USE_CACHE)
		{
			goodslist = new ArrayList<Goods>();
			Jedis jedis = RedisUtil.getJedis();
			List<String> ids = jedis.lrange("RecommendGoodsIds", 0, 4);

			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
				goodslist.add(new Goods(map));
			}
			RedisUtil.returnResource(jedis);
		}
		else
		{
			goodslist = goodsMapper.queryGoodsClickcount();
		}
		
		return goodslist;
	}
	
	@SuppressWarnings("unused")
	@Override
	public PageModel<Goods> getGoodsByRecommend(PageModel<Goods> pageModel)
	{
		if(ConfigUtil.USE_CACHE && pageModel.getPageNow() <= ConfigUtil.CACHE_PAGESIZE)
		{
			Jedis jedis = RedisUtil.getJedis();
			pageModel.setTotalCount(Integer.parseInt(jedis.get("GoodsCount")));
			int start = pageModel.getPageNow() * pageModel.getPageSize() - pageModel.getPageSize();
			int stop = start + pageModel.getPageSize() - 1;
			List<String> ids = jedis.lrange("RecommendGoodsIds", start, stop);
			List<Goods> goodsList = new ArrayList<Goods>();
			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
				goodsList.add(new Goods(map));
			}
			pageModel.setList(goodsList);
			RedisUtil.returnResource(jedis);
		}
		else
		{
			pageModel.setTotalCount(goodsMapper.queryGoodsCount());
			pageModel.setList(goodsMapper.queryGoodsClickcountPage(pageModel));
		}
		
		
		return pageModel;
	}

	@Override
	public List<Goods> getGoodsBySellhot()
	{
		List<Goods> goodslist = null;
		
		if(ConfigUtil.USE_CACHE)
		{
			goodslist = new ArrayList<Goods>();
			Jedis jedis = RedisUtil.getJedis();
			List<String> ids = jedis.lrange("SellhotIds", 0, 4);

			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
				goodslist.add(new Goods(map));
			}
			RedisUtil.returnResource(jedis);
		}
		else
		{
			goodslist = goodsMapper.queryGoodsSellcount();
		}
		
		return goodslist;
	}
	
	@SuppressWarnings("unused")
	@Override
	public PageModel<Goods> getGoodsBySellhot(PageModel<Goods> pageModel)
	{		
		if(ConfigUtil.USE_CACHE && pageModel.getPageNow() <= ConfigUtil.CACHE_PAGESIZE)
		{
			Jedis jedis = RedisUtil.getJedis();
			pageModel.setTotalCount(Integer.parseInt(jedis.get("GoodsCount")));
			int start = pageModel.getPageNow() * pageModel.getPageSize() - pageModel.getPageSize();
			int stop = start + pageModel.getPageSize() - 1;
			List<String> ids = jedis.lrange("SellhotIds", start, stop);
			List<Goods> goodsList = new ArrayList<Goods>();
			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
				goodsList.add(new Goods(map));
			}
			pageModel.setList(goodsList);
			RedisUtil.returnResource(jedis);
		}
		else
		{
			pageModel.setTotalCount(goodsMapper.queryGoodsCount());
			pageModel.setList(goodsMapper.queryGoodsSellcountPage(pageModel));
		}
		
		return pageModel;
	}

	@Override
	public PageModel<Goods> getGoodsByCategory(PageModel<Goods> pageModel)
	{
		if(ConfigUtil.USE_CACHE && pageModel.getPageNow() <= ConfigUtil.CACHE_PAGESIZE)
		{
			Integer categoryId = pageModel.getPageQuery().getCategoryId();
			Jedis jedis = RedisUtil.getJedis();
			pageModel.setTotalCount(Integer.parseInt(jedis.get("CategoryGoodsCount:" + categoryId)));
			
			int start = pageModel.getPageNow() * pageModel.getPageSize() - pageModel.getPageSize();
			int stop = start + pageModel.getPageSize() - 1;
			List<String> ids = jedis.lrange("CategoryGoodsIds:" + categoryId, start, stop);
			List<Goods> goodsList = new ArrayList<Goods>();
			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
				goodsList.add(new Goods(map));
			}
			pageModel.setList(goodsList);
			RedisUtil.returnResource(jedis);
		}
		else
		{
			pageModel.setTotalCount(goodsMapper.queryGoodsCategoryCount(pageModel.getPageQuery()));
			pageModel.setList(goodsMapper.queryGoodsByCategory(pageModel));
		}
		
		return pageModel;
	}

	@Override
	public List<Category> getCategorylist()
	{
		List<Category> categorylist = categoryMapper.queryCategoryList();
		return categorylist;
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
		
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			Map<String, String> map = jedis.hgetAll("GoodsDetail:" + id);
			if(map != null && !map.isEmpty())
				goods = new Goods(map);
			RedisUtil.returnResource(jedis);
		}
		
		if(goods == null)
		{
			if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
			{
				//未分表写法
				goods = goodsMapper.queryGoodsById(id);
			}
			else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
			{
				//分表写法
				String sql = " select g.id, g.img, g.`name`, g.marketprice, g.sellprice, g.stock, g.clickcount, g.sellcount, g.commentCount, " +
							 " gd.desc, gd.id gdid, gd.`name` gdname, gd.val gdval, t.id tid, t.`name` tname, t.count tcount " +
							 " from goods g " +
							 " LEFT JOIN goodsdesc" + (id % ConfigUtil.SPLIT_NUM) + " gd ON g.id = gd.goodsId " +
							 " LEFT JOIN goodstag" + (id % ConfigUtil.SPLIT_NUM) + " t ON g.id = t.goodsId " +
							 " where g.id = " + id;
				goods = goodsMapper.queryGoodsByIdSplit(new SQLAdapter(sql));
			}
		}

		return goods;
	}
	
	/**
	 * 获得推荐分类商品
	 * @return
	 */
	public List<Category> getRecommendCategory()
	{
		List<Category> recommendCategory = categoryMapper.queryRecommendCategory();
		return recommendCategory;
	}
	
	/**
	 * 获得推荐品牌
	 * @return
	 */
	public List<Brand> getRecommendBrand()
	{
		List<Brand> recommendBrand = brandMapper.queryRecommendBrand();
		return recommendBrand;
	}
	
	/**
	 * 获得评论列表
	 * @param pageModel
	 * @return
	 */
	public PageModel<Goodscomment> getCommentList(PageModel<Goodscomment> pageModel)
	{
		
		
		if(ConfigUtil.USE_CACHE && pageModel.getPageNow() <= ConfigUtil.CACHE_PAGESIZE)
		{
			Integer goodsId = pageModel.getPageQuery().getGoodsId();
			Jedis jedis = RedisUtil.getJedis();
			pageModel.setTotalCount(Integer.parseInt(jedis.get("GoodsCommentCount:" + goodsId)));
			int start = pageModel.getPageNow() * pageModel.getPageSize() - pageModel.getPageSize();
			int stop = start + pageModel.getPageSize() - 1;
			List<String> ids = jedis.lrange("GoodsCommentIds:" + goodsId, start, stop);
			List<Goodscomment> goodscommentList = new ArrayList<Goodscomment>();
			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("GoodsComment:" + id);
				if(map != null && !map.isEmpty())
					goodscommentList.add(new Goodscomment(map));
			}
			pageModel.setList(goodscommentList);
			RedisUtil.returnResource(jedis);
		}
		else
		{
			pageModel.setTotalCount(goodscommentMapper.queryCommentCount(pageModel.getPageQuery()));
			if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
			{
				//未分表写法	
				pageModel.setList(goodscommentMapper.queryCommentList(pageModel));
			}
			else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
			{
				//分表写法
				Integer goodsId = pageModel.getPageQuery().getGoodsId();
				String sql = " SELECT c.id, c.username, c.userlevel, c.score, c.content, c.createtime FROM `goodscomment" + (goodsId % ConfigUtil.SPLIT_NUM) + "` c " +
						" where c.goodsId = " + goodsId + " AND c.createtime <= (SELECT createtime from `goodscomment` where goodsId = " + goodsId + " ORDER BY createtime DESC LIMIT " + pageModel.getStartIndex() + ", 1) " +
						" ORDER BY c.createtime DESC LIMIT " + pageModel.getPageSize();
				pageModel.setList(goodscommentMapper.queryCommentListSplit(new SQLAdapter(sql)));
			}
		}
		
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
