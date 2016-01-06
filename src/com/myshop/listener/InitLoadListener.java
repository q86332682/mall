package com.myshop.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.myshop.dao.BrandMapper;
import com.myshop.dao.CategoryMapper;
import com.myshop.dao.GoodsMapper;
import com.myshop.dao.GoodscommentMapper;
import com.myshop.model.Brand;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.GoodsDesc;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.thread.PreloadThread;
import com.myshop.util.RedisUtil;

/**
 * 初始化载入监听器
 * @author Administrator
 *
 */
public class InitLoadListener implements ServletContextListener
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public InitLoadListener()
	{
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		LOG.info("初始化载入数据！");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		Jedis jedis = RedisUtil.getJedis();
		GoodsMapper goodsMapper = (GoodsMapper) ctx.getBean("goodsMapper");
		GoodscommentMapper goodscommentMapper = (GoodscommentMapper) ctx.getBean("goodscommentMapper");
		
		if(!jedis.exists("NewGoodsIds"))
		{
			LOG.info("初始化载入商品数据！");
			Pipeline pipeline = jedis.pipelined();
			List<Goods> goodslist = goodsMapper.loadData();
			
			//goodscount数载入
			pipeline.set("GoodsCount", goodsMapper.queryGoodsCount() + "");
			
			//新品上架(key = NewGoodsIds)
			for(int i = 0;i < 250;i++)
			{
				Goods goods = goodslist.get(i);
				pipeline.lpush("NewGoodsIds", goods.getId() + "");
			}
			
			//推荐商品(key = RecommendGoodsIds)
			for(int i = 250;i < 500;i++)
			{
				Goods goods = goodslist.get(i);
				pipeline.lpush("RecommendGoodsIds", goods.getId() + "");
			}
			
			//热卖商品(key = SellhotIds)
			for(int i = 500;i < 750;i++)
			{
				Goods goods = goodslist.get(i);
				pipeline.lpush("SellhotIds", goods.getId() + "");
			}
			
			//推荐分类商品
			/*
			CategoryMapper categoryMapper = (CategoryMapper) ctx.getBean("categoryMapper");
			List<Category> categorylist = categoryMapper.loadData();
			for(int i = 0;i < categorylist.size();i++)
			{
				Category c = categorylist.get(i);
				pipeline.hmset("HomeCategory:" + c.getId(), c.toMap());
				
				List<Goods> categoryGoods = c.getGoods();
				for(Goods g : categoryGoods)
					goodslist.add(g);
			}
			
			
			//推荐品牌商品
			BrandMapper brandMapper = (BrandMapper) ctx.getBean("brandMapper");
			List<Brand> brandlist = brandMapper.loadData();
			for(int i = 0;i < brandlist.size();i++)
			{
				Brand b = brandlist.get(i);
				pipeline.hmset("HomeBrand:" + b.getId(), b.toMap());
				
				List<Goods> brandGoods = b.getGoods();
				for(Goods g : brandGoods)
					goodslist.add(g);
			}*/
			
			//预加载分类商品
			LOG.info("处理预加载分类商品!");
			Goods tempGoods = new Goods();
			for(int categoryId = 1;categoryId <= 349;categoryId++)
			{
				//处理分类数缓存
				tempGoods.setCategoryId(categoryId);
				pipeline.set("CategoryGoodsCount:" + categoryId, goodsMapper.queryGoodsCategoryCount(tempGoods) + "");
				
				List<Goods> categoryGoods = goodsMapper.preloadCategoryGoods(categoryId);
				for(Goods g : categoryGoods)
				{
					//key = CategoryGoodsIds:categoryId
					pipeline.lpush("CategoryGoodsIds:" + categoryId, g.getId() + "");
					goodslist.add(g);
				}
			}
			
			
			/*---------加入不重复的商品详情---------*/
			LOG.info("处理加载商品和评论!");
			Goodscomment tempComment = new Goodscomment();
			for(int i = 0;i < goodslist.size();i++)
			{
				Goods goods = goodslist.get(i);
				//key = GoodsDetail:id
				pipeline.hmset("GoodsDetail:" + goods.getId(), goods.toMap());
				
				//处理评论数加载
				tempComment.setGoodsId(goods.getId());
				pipeline.set("GoodsCommentCount:" + goods.getId(), goodscommentMapper.queryCommentCount(tempComment) + "");
				
				//预加载商品评论
				List<Goodscomment> goodscommentList = goodscommentMapper.preloadGoodsComment(goods.getId());
				for(Goodscomment gc : goodscommentList)
				{
					//key = GoodsCommentIds:goodsId
					pipeline.lpush("GoodsCommentIds:" + goods.getId(), gc.getId() + "");
					pipeline.hmset("GoodsComment:" + gc.getId(), gc.toMap());
				}
				
				
				//key = GoodsDesc:goodsId
				/*
				GoodsDesc goodsDesc = goods.getGoodsDesc().get(0);
				pipeline.hmset("GoodsDesc:" + goods.getId(), goodsDesc.toMap());
				
				//key = GoodsTagIds:goodsId
				List<GoodsTag> goodsTagList = goods.getGoodsTag();
				for(GoodsTag gt : goodsTagList)
					pipeline.lpush("GoodsTagIds:" + goods.getId(), gt.getId() + "");
				
				
				//key = GoodsTag:id
				for(GoodsTag gt : goodsTagList)
					pipeline.hmset("GoodsTag:" + gt.getId(), gt.toMap());*/
			}
			
			pipeline.sync();
		}
		RedisUtil.returnResource(jedis);
		LOG.info("初始化载入数据完成！");
		
//		LOG.info("启动预加载线程!");
//		new PreloadThread(goodsMapper, goodscommentMapper).start();
	}
}
