package com.myshop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import com.myshop.dao.GoodsCollectMapper;
import com.myshop.dao.GoodsMapper;
import com.myshop.dao.GoodsTagMapper;
import com.myshop.dao.GoodscommentMapper;
import com.myshop.dao.OrderMapper;
import com.myshop.dao.OrdergoodsMapper;
import com.myshop.dao.ScorelogMapper;
import com.myshop.dao.UserMapper;
import com.myshop.dao.UserlevelMapper;
import com.myshop.model.Goods;
import com.myshop.model.GoodsCollect;
import com.myshop.model.GoodsTag;
import com.myshop.model.Goodscomment;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
import com.myshop.model.SQLAdapter;
import com.myshop.model.Scorelog;
import com.myshop.model.User;
import com.myshop.model.Userlevel;
import com.myshop.service.GoodsService;
import com.myshop.service.UserService;
import com.myshop.util.ConfigUtil;
import com.myshop.util.RedisUtil;

@Service
/**
 * 用户业务实现类
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrdergoodsMapper ordergoodsMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodscommentMapper goodscommentMapper;
	
	@Autowired
	private GoodsTagMapper goodsTagMapper;
	
	@Autowired
	private GoodsCollectMapper goodsCollectMapper;
	
	@Autowired
	private ScorelogMapper scorelogMapper;
	
	@Autowired
	private UserlevelMapper userlevelMapper;
	
	@Autowired
	private GoodsService goodsService;
	
	@Transactional
	@Override
	public void register(User user)
	{
		/*if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			
			//key = "User:username"
			if(!jedis.exists("User:" + user.getUsername()))
			{
				user.setLevel("普通会员");
				user.setPercent(100);
				jedis.hmset("User:" + user.getUsername(), user.toMap());
			}
			else
			{
				LOG.info("用户名: " + user.getUsername() + ", 已经存在!!!");
			}
			
			RedisUtil.returnResource(jedis);
		}
		else
		{*/
			User isExist = userMapper.queryUser(user);
			if(isExist == null)
			{
				userMapper.insertUser(user);
				Scorelog scorelog = new Scorelog();
				scorelog.setUserId(user.getId());
				scorelog.setInfo("注册加50积分!");
				scorelog.setScore(50);
				AddScore(scorelog, user);
			}
			else
			{
				LOG.info("用户名: " + user.getUsername() + ", 已经存在!!!");
			}
		//}
	}

	
	@Override
	public User login(User user)
	{
		User loginUser = null;
		
		/*if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			Map<String, String> map = jedis.hgetAll("User:" + user.getUsername());
			if(map != null && !map.isEmpty())
			{
				loginUser  = new User(map);
				if(!user.getPassword().equals(loginUser.getPassword()))
					loginUser = null;
			}
			else
			{
				loginUser = userMapper.queryUser(user);
			}
			
			RedisUtil.returnResource(jedis);
		}
		else
		{*/
			loginUser = userMapper.queryUser(user);
		//}
		
		if(loginUser != null)
		{
			Scorelog scorelog = new Scorelog();
			scorelog.setUserId(loginUser.getId());
			scorelog.setInfo("登录加10积分!");
			scorelog.setScore(10);
			AddScore(scorelog, loginUser);
		}
		
		return loginUser;
	}

	@Override
	public PageModel<Order> getMyOrderList(PageModel<Order> pageModel)
	{
		if(ConfigUtil.USE_CACHE && pageModel.getPageNow() <= ConfigUtil.CACHE_PAGESIZE)
		{
			Jedis jedis = RedisUtil.getJedis();
			if(jedis.exists("MyOrderCount:" + pageModel.getPageQuery().getUserId()))
			{
				pageModel.setTotalCount(Integer.parseInt(jedis.get("MyOrderCount:" + pageModel.getPageQuery().getUserId())));
			}
			else
			{
				int orderCount = orderMapper.queryOrderCount(pageModel);
				pageModel.setTotalCount(orderCount);
				jedis.set("MyOrderCount:" + pageModel.getPageQuery().getUserId(), orderCount + "");
			}
			int start = pageModel.getPageNow() * pageModel.getPageSize() - pageModel.getPageSize();
			int stop = start + pageModel.getPageSize() - 1;
			List<String> ids = jedis.lrange("MyOrderIds:" + pageModel.getPageQuery().getUserId(), start, stop);
			List<Order> orderList = new ArrayList<Order>();
			for(String id : ids)
			{
				Map<String, String> map = jedis.hgetAll("Order:" + id);
				orderList.add(new Order(map));
			}
			pageModel.setList(orderList);
			RedisUtil.returnResource(jedis);
		}
		else
		{
			pageModel.setTotalCount(orderMapper.queryOrderCount(pageModel));
			if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
			{
				//未分表写法
				pageModel.setList(orderMapper.queryOrderPage(pageModel));
			}
			else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
			{
			
				//分表写法
				Integer userId = pageModel.getPageQuery().getUserId();
				String sql = "select o.* from `order" + (userId % ConfigUtil.SPLIT_NUM) + "` o where o.userId = " + userId +
							 " ORDER BY createtime LIMIT " + pageModel.getStartIndex() + "," + pageModel.getPageSize();
				pageModel.setList(orderMapper.queryOrderPageSplit(new SQLAdapter(sql)));
			}
		}
		
		return pageModel;
	}

	@Transactional
	@Override
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList, User user)
	{
		Integer orderId = 0;
		if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
		{
			//未分表写法
			orderMapper.insertOrder(order);
			orderId = order.getId();
		}
		else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
		{
			//分表写法
			StringBuffer sql = new StringBuffer(128);
			sql.append(" insert into `order").append(order.getUserId() % ConfigUtil.SPLIT_NUM).append("`(name, addr, mobile, totalprice, userId, createtime) ");
			sql.append(" VALUES('").append(order.getName()).append("','").append(order.getAddr()).append("','").append(order.getMobile()).append("',");
			sql.append(order.getTotalprice()).append(",").append(order.getUserId()).append(", SYSDATE())");
			SQLAdapter sqlAdapter = new SQLAdapter(sql.toString());
			orderMapper.insertOrderSplit(sqlAdapter);
			orderId = sqlAdapter.getId();
		}
		
		List<Integer> ids = new ArrayList<Integer>();
		for(Ordergoods o : OrdergoodsList)
		{
			o.setOrderId(orderId);
			ids.add(o.getGoodsId());
		}
		ordergoodsMapper.insertOrdergoods(OrdergoodsList);
		
//		goodsMapper.updateSellCount(OrdergoodsList);
//		goodsMapper.updateStokc(OrdergoodsList);
		//TODO 此处会造成锁竞争
//		synchronized (this)
//		{
//			goodsMapper.updateSellCountAndStock(OrdergoodsList);
//		}
		userMapper.updateOrderCount(order.getUserId());
		user.setOrderCount(user.getOrderCount() + 1);
		
		Scorelog scorelog = new Scorelog();
		scorelog.setUserId(order.getUserId());
		int socre = (int) order.getTotalprice();
		scorelog.setInfo("购买商品加" + socre  + "积分!");
		scorelog.setScore(socre);
		AddScore(scorelog, user);
		
		socre = (int)(socre * (user.getPercent() / 100.0));
		int gold = user.getGold() - socre <= 0 ? user.getGold() : socre; 
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", order.getUserId());
		map.put("gold", gold);
		userMapper.updateGold(map);
		user.setGold(user.getGold() - gold);
		
		
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			
			if(jedis.exists("MyOrderCount:" + user.getId()))
			{
				jedis.incr("MyOrderCount:" + user.getId());
			}
			
			//key = MyOrderIds:userId
			if(jedis.llen("MyOrderIds:" + user.getId()) >= ConfigUtil.CACHE_PAGESIZE * 5)
			{
				jedis.lpush("MyOrderIds:" + user.getId(), orderId + "");
				int delOrderId = Integer.parseInt(jedis.rpop("MyOrderIds:" + user.getId()));
				
				//key = Order:orderId
				jedis.hmset("Order:" + orderId, order.toMap());
				jedis.del("Order:" + delOrderId);
			}
			else
			{
				jedis.lpush("MyOrderIds:" + user.getId(), orderId + "");
				
				//key = Order:orderId
				jedis.hmset("Order:" + orderId, order.toMap());
			}
			
			RedisUtil.returnResource(jedis);
		}
	}
	
	@Transactional
	@Override
	/**
	 * 发布评论
	 * @param goodscomment
	 */
	public void publishComment(Goodscomment goodscomment, User user)
	{
		int goodscommentId = 0;
		
		if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
		{
			//未分表写法
			goodscommentMapper.insertComment(goodscomment);
			goodscommentId = goodscomment.getId();
		}
		else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
		{
			//分表写法
			StringBuffer sql = new StringBuffer(128);
			sql.append(" INSERT INTO goodscomment").append(goodscomment.getGoodsId() % ConfigUtil.SPLIT_NUM).append("(userId, goodsId, username, userlevel, score, content, createtime) ");
			sql.append(" VALUES(").append(goodscomment.getUserId()).append(",").append(goodscomment.getGoodsId()).append(",'").append(goodscomment.getUsername()).append("','");
			sql.append(goodscomment.getUserlevel()).append("',").append(goodscomment.getScore()).append(",'").append(goodscomment.getContent()).append("', SYSDATE()) ");
			SQLAdapter sqlAdapter = new SQLAdapter(sql.toString());
			goodscommentMapper.insertCommentSplit(sqlAdapter);
			goodscommentId = sqlAdapter.getId();
		}
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			jedis.incr("GoodsCommentCount:" + goodscomment.getGoodsId());
			
			//key = GoodsCommentIds:goodsId
			if(jedis.llen("GoodsCommentIds:" + goodscomment.getGoodsId()) >= ConfigUtil.CACHE_PAGESIZE * 5)
			{
				jedis.lpush("GoodsCommentIds:" + goodscomment.getGoodsId(), goodscommentId + "");
				int delGoodscommentId = Integer.parseInt(jedis.rpop("GoodsCommentIds:" + goodscomment.getGoodsId()));
				
				//key = GoodsComment:goodscommentId
				jedis.hmset("GoodsComment:" + goodscommentId, goodscomment.toMap());
				jedis.del("GoodsComment:" + delGoodscommentId);
			}
			else
			{
				jedis.lpush("GoodsCommentIds:" + goodscomment.getGoodsId(), goodscommentId + "");
				jedis.hmset("GoodsComment:" + goodscommentId, goodscomment.toMap());
			}
			
			RedisUtil.returnResource(jedis);
		}
		
		 
		//goodsMapper.updateCommentCount(goodscomment.getGoodsId());		//TODO 会对同一个商品进行竞争, 造成锁定
		userMapper.updateCommentCount(goodscomment.getUserId());
		user.setCommentCount(user.getCommentCount() + 1);
		
		
		Goods goods = goodsService.getGoodsById(goodscomment.getGoodsId(), null);
		int commentCount = goods.getCommentCount();
		Scorelog scorelog = new Scorelog();
		scorelog.setUserId(goodscomment.getUserId());
		if(commentCount == 1)
		{
			scorelog.setInfo("首次评论加50积分!");
			scorelog.setScore(50);
		}
		else
		{
			scorelog.setInfo("评论加10积分!");
			scorelog.setScore(10);
		}
		
		AddScore(scorelog, user);
		
	}
	
	@Override
	/**
	 * 修改评论
	 * @param goodscomment
	 */
	public void updateComment(Goodscomment goodscomment)
	{
		boolean updateInCache = false;
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			Map<String, String> map = jedis.hgetAll("GoodsComment:" + goodscomment.getId());
			if(map != null && !map.isEmpty())
			{
				updateInCache = true;
				Goodscomment temp = new Goodscomment(map);
				temp.setScore(goodscomment.getScore());
				temp.setContent(goodscomment.getContent());
				jedis.hmset("GoodsComment:" + goodscomment.getId(), temp.toMap());
			}
			RedisUtil.returnResource(jedis);
		}
		
		
		
		if(!updateInCache)
		{
			if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
			{
				//未分表写法
				goodscommentMapper.updateComment(goodscomment);
			}
			else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
			{
				//分表写法
				StringBuffer sql = new StringBuffer(64);
				sql.append(" UPDATE goodscomment").append(goodscomment.getId() % ConfigUtil.SPLIT_NUM);
				sql.append(" SET score = ").append(goodscomment.getScore()).append(", content = '").append(goodscomment.getContent());
				sql.append("' where id = ").append(goodscomment.getId());
				goodscommentMapper.updateCommentSplit(new SQLAdapter(sql.toString()));
			}
		}
	}
	
	@Override
	/**
	 * 添加商品标签
	 * @param tag
	 */
	public void addGoodsTag(GoodsTag tag)
	{
		if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
		{
			//未分表写法
			goodsTagMapper.insertTag(tag);
		}
		else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
		{
			//分表写法
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO goodstag").append(tag.getGoodsId() % ConfigUtil.SPLIT_NUM);
			sql.append("(name, count, userId, goodsId) ");
			sql.append(" VALUES('").append(tag.getName()).append("',").append(tag.getCount()).
			append(",").append(tag.getUserId()).append(",").append(tag.getGoodsId()).append(") ");
			goodsTagMapper.insertTagSplit(new SQLAdapter(sql.toString()));
		}
	}
	
	@Override
	/**
	 * 更新标签点击数
	 * @param id
	 */
	public void updateGoodsTag(Integer id)
	{
		if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
		{
			//未分表写法
			goodsTagMapper.updateTagCount(id);
		}
		else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
		{
			//分表写法
			Random rand = new Random();
			String sql = " update goodstag" + rand.nextInt(ConfigUtil.SPLIT_NUM) + " set count = count + 1 where id = " + id;
			goodsTagMapper.updateTagCountSplit(new SQLAdapter(sql));
		}
	}
	
	@Transactional
	@Override
	/**
	 * 收藏商品
	 * @param goodsCollect
	 */
	public void collectGoods(GoodsCollect goodsCollect, User user)
	{
		int goodsCollectId = 0;
		if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
		{
			//未分表写法
			goodsCollectMapper.insertGoodsCollect(goodsCollect);
			goodsCollectId = goodsCollect.getId();
		}
		else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
		{
			//分表写法
			StringBuffer sql = new StringBuffer(128);
			sql.append("INSERT INTO goodscollect").append((goodsCollect.getUserId() % ConfigUtil.SPLIT_NUM));
			sql.append("(userId, goodsId, goodsname, goodsimg, goodsMarketprice, goodsSellprice, createtime) ");
			sql.append(" VALUES(").append(goodsCollect.getUserId()).append(",").append(goodsCollect.getGoodsId()).append(",'").
			append(goodsCollect.getGoodsname());
			sql.append("','").append(goodsCollect.getGoodsimg()).append("',").append(goodsCollect.getGoodsMarketprice()).append(",");
			sql.append(goodsCollect.getGoodsSellprice()).append(",SYSDATE()) ");
			SQLAdapter sqlAdapter = new SQLAdapter(sql.toString());
			goodsCollectMapper.insertGoodsCollectSplit(sqlAdapter);
			goodsCollectId = sqlAdapter.getId();
		}
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			
			//key = GoodsCollectIds:userId
			if(jedis.llen("GoodsCollectIds:" + goodsCollect.getUserId()) >= 10)
			{
				jedis.lpush("GoodsCollectIds:" + goodsCollect.getUserId(), goodsCollectId + "");
				int delGoodsCollectId = Integer.parseInt(jedis.rpop("GoodsCollectIds:" + goodsCollect.getUserId()));
				
				//key = GoodsCollect:GoodsCollectId
				jedis.hmset("GoodsCollect:" + goodsCollectId, goodsCollect.toMap());
				jedis.del("GoodsCollect:" + delGoodsCollectId);
			}
			else
			{
				jedis.lpush("GoodsCollectIds:" + goodsCollect.getUserId(), goodsCollectId + "");
				jedis.hmset("GoodsCollect:" + goodsCollectId, goodsCollect.toMap());
			}
			
			RedisUtil.returnResource(jedis);
		}
		
		userMapper.updateCollectCount(goodsCollect.getUserId());
		user.setCollectCount(user.getCollectCount() + 1);
	}
	
	@Transactional
	/**
	 * 增加积分
	 */
	public void AddScore(Scorelog scorelog, User user)
	{
		userMapper.updateScore(scorelog);

		if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
		{
			//未分表写法
			scorelogMapper.insertScorelog(scorelog);
		}
		else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
		{
			//分表写法
			StringBuffer sql = new StringBuffer(128);
			sql.append(" INSERT INTO scorelog").append(user.getId() % ConfigUtil.SPLIT_NUM).append("(info, score, userId, createtime)");
			sql.append(" values('").append(scorelog.getInfo()).append("',").append(scorelog.getScore()).append(",");
			sql.append(scorelog.getUserId()).append(", SYSDATE())");
			System.out.println(sql.toString());
			scorelogMapper.insertScorelogSplit(new SQLAdapter(sql.toString()));
		}
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			
			if(jedis.llen("UserScorelogIds:" + user.getId()) >= 10)
			{
				//key = UserScorelogIds:userId
				jedis.lpush("UserScorelogIds:" + user.getId(), scorelog.getId() + "");
				int delScorelogId = Integer.parseInt(jedis.rpop("UserScorelogIds:" + user.getId()));
				
				//key = Scorelog:scorelogId
				jedis.del("Scorelog:" + delScorelogId);
				jedis.hmset("Scorelog:" + scorelog.getId(), scorelog.toMap());
			}
			else
			{
				//key = UserScorelogIds:userId
				jedis.lpush("UserScorelogIds:" + user.getId(), scorelog.getId() + "");
				
				//key = Scorelog:scorelogId
				jedis.hmset("Scorelog:" + scorelog.getId(), scorelog.toMap());
			}
			
			RedisUtil.returnResource(jedis);
		}
		
		user = userMapper.queryUser(user);
	}

	@Override
	public List<Userlevel> getUserlevelList()
	{
		return userlevelMapper.queryUserlevelList();
	}

	@Override
	public List<Scorelog> getScorelog(Integer userId)
	{
		List<Scorelog> scorelogs = null;
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			
			if(jedis.exists("UserScorelogIds:" + userId))
			{
				int start = 0;
				int stop = 9;
				List<String> ids = jedis.lrange("UserScorelogIds:" + userId, start, stop);
				scorelogs = new ArrayList<Scorelog>();
				for(String id : ids)
				{
					Map<String, String> map = jedis.hgetAll("Scorelog:" + id);
					scorelogs.add(new Scorelog(map));
				}
			}
			RedisUtil.returnResource(jedis);
		}
		
		if(scorelogs == null)
		{
			if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
			{
				//未分表写法
				scorelogs = scorelogMapper.queryScorelog(userId);
			}
			else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
			{
				//分表写法
				String sql = " SELECT * FROM `scorelog" + (userId % ConfigUtil.SPLIT_NUM) + "` where userId = " + userId + " LIMIT 10 ";
				scorelogs = scorelogMapper.queryScorelogSplit(new SQLAdapter(sql));
			}
		}
		return scorelogs;
	}

	@Override
	public List<GoodsCollect> getGoodsCollect(Integer userId)
	{
		List<GoodsCollect> goodsCollects = null;
		
		if(ConfigUtil.USE_CACHE)
		{
			Jedis jedis = RedisUtil.getJedis();
			
			if(jedis.exists("GoodsCollectIds:" + userId))
			{
				int start = 0;
				int stop = 9;
				List<String> ids = jedis.lrange("GoodsCollectIds:" + userId, start, stop);
				goodsCollects = new ArrayList<GoodsCollect>();
				for(String id : ids)
				{
					Map<String, String> map = jedis.hgetAll("GoodsCollect:" + id);
					goodsCollects.add(new GoodsCollect(map));
				}
			}
			RedisUtil.returnResource(jedis);
		}
		
		if(goodsCollects == null)
		{
			if(ConfigUtil.TABLE_MODEL == ConfigUtil.TABLE)
			{
				//未分表写法
				goodsCollects = goodsCollectMapper.queryGoodsCollect(userId);
			}
			else if(ConfigUtil.TABLE_MODEL == ConfigUtil.SPLIT_TABLE)
			{
				//分表写法
				String sql = "SELECT * FROM `goodscollect" + (userId % ConfigUtil.SPLIT_NUM) + "` where userId = " + userId + " LIMIT 10";
				goodsCollects = goodsCollectMapper.queryGoodsCollectSplit(new SQLAdapter(sql));
			}
		}
		return goodsCollects;
	}
}
