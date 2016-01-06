package com.myshop.thread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.myshop.dao.GoodsMapper;
import com.myshop.dao.GoodscommentMapper;
import com.myshop.model.Goods;
import com.myshop.model.Goodscomment;
import com.myshop.util.RedisUtil;

/**
 * 预加载线程
 * @author Administrator
 *
 */
public class PreloadThread extends Thread
{

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private static BlockingQueue<Object[]> preloadList;
	private GoodsMapper goodsMapper;
	private GoodscommentMapper goodscommentMapper;
	
	public PreloadThread(GoodsMapper goodsMapper, GoodscommentMapper goodscommentMapper)
	{
		this.goodsMapper = goodsMapper;
		this.goodscommentMapper = goodscommentMapper;
		preloadList = new ArrayBlockingQueue<Object[]>(100);
	}
	
	@Override
	public void run()
	{
		try
		{
			while(true)
				processPreload();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void processPreload() throws Exception
	{
		Object[] command = preloadList.take();
		String req = (String)command[0];
		
		if("preloadCategoryGoods".equals(req))
		{
			LOG.info("处理预加载分类商品!");
			Integer categoryId = (Integer)command[1];
			List<Goods> categoryGoods = goodsMapper.preloadCategoryGoods(categoryId);
			Jedis jedis = RedisUtil.getJedis();
			Pipeline pipeline = jedis.pipelined();
			for(Goods g : categoryGoods)
			{
				//key = CategoryGoodsIds:categoryId
				pipeline.lpush("CategoryGoodsIds:" + categoryId, g.getId() + "");
				pipeline.hmset("GoodsDetail:" + g.getId(), g.toMap());
			}
			
			pipeline.sync();
			RedisUtil.returnResource(jedis);
		}
		else if("preloadGoodsComment".equals(req))
		{
			LOG.info("处理预加载商品评论!");
			Integer goodsId = (Integer)command[1];
			List<Goodscomment> goodscommentList = goodscommentMapper.preloadGoodsComment(goodsId);
			Jedis jedis = RedisUtil.getJedis();
			Pipeline pipeline = jedis.pipelined();
			for(Goodscomment gc : goodscommentList)
			{
				//key = GoodsCommentIds:goodsId
				pipeline.lpush("GoodsCommentIds:" + goodsId, gc.getId() + "");
				pipeline.hmset("GoodsComment:" + gc.getId(), gc.toMap());
			}
			
			pipeline.sync();
			RedisUtil.returnResource(jedis);
		}
	}
	
	
	/**
	 * 添加预加载请求
	 * @param command 第一个元素是需要加载的请求字符串, 之后都是参数
	 */
	public static void addPreload(Object[] command)
	{
		try
		{
			if(preloadList != null)
				preloadList.put(command);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
