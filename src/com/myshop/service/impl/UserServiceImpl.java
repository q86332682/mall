package com.myshop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	}

	@Transactional
	@Override
	public User login(User user)
	{
		User loginUser = userMapper.queryUser(user);
		Scorelog scorelog = new Scorelog();
		scorelog.setUserId(loginUser.getId());
		scorelog.setInfo("登录加10积分!");
		scorelog.setScore(10);
		AddScore(scorelog, loginUser);
		return loginUser;
	}

	@Override
	public PageModel<Order> getMyOrderList(PageModel<Order> pageModel)
	{
		pageModel.setTotalCount(orderMapper.queryOrderCount(pageModel));
		//未分表写法
		//pageModel.setList(orderMapper.queryOrderPage(pageModel));
		
		//分表写法
		Integer userId = pageModel.getPageQuery().getUserId();
		String sql = "select o.* from `order" + (userId % 10) + "` o where o.userId = " + userId +
					 " ORDER BY createtime LIMIT " + pageModel.getStartIndex() + "," + pageModel.getPageSize();
		pageModel.setList(orderMapper.queryOrderPageSplit(new SQLAdapter(sql)));
		
		return pageModel;
	}

	@Transactional
	@Override
	public void buyGoods(Order order, List<Ordergoods> OrdergoodsList, User user)
	{
		//未分表写法
		//orderMapper.insertOrder(order);
		
		//分表写法
		StringBuffer sql = new StringBuffer(128);
		sql.append(" insert into `order").append(order.getUserId() % 10).append("`(name, addr, mobile, totalprice, userId, createtime) ");
		sql.append(" VALUES('").append(order.getName()).append("','").append(order.getAddr()).append("','").append(order.getMobile()).append("',");
		sql.append(order.getTotalprice()).append(",").append(order.getUserId()).append(", SYSDATE())");
		SQLAdapter sqlAdapter = new SQLAdapter(sql.toString());
		orderMapper.insertOrderSplit(sqlAdapter);
		
		
		List<Integer> ids = new ArrayList<Integer>();
		for(Ordergoods o : OrdergoodsList)
		{
			o.setOrderId(sqlAdapter.getId());
			ids.add(o.getGoodsId());
		}
		ordergoodsMapper.insertOrdergoods(OrdergoodsList);
		
		goodsMapper.updateSellCount(OrdergoodsList);
		goodsMapper.updateStokc(OrdergoodsList);
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
	}
	
	@Transactional
	@Override
	/**
	 * 发布评论
	 * @param goodscomment
	 */
	public void publishComment(Goodscomment goodscomment, User user)
	{
		//未分表写法
		//goodscommentMapper.insertComment(goodscomment);
		
		//分表写法
		StringBuffer sql = new StringBuffer(128);
		sql.append(" INSERT INTO goodscomment").append(goodscomment.getGoodsId() % 10).append("(userId, goodsId, username, userlevel, score, content, createtime) ");
		sql.append(" VALUES(").append(goodscomment.getUserId()).append(",").append(goodscomment.getGoodsId()).append(",'").append(goodscomment.getUsername()).append("','");
		sql.append(goodscomment.getUserlevel()).append("',").append(goodscomment.getScore()).append(",'").append(goodscomment.getContent()).append("', SYSDATE()) ");
		goodscommentMapper.insertCommentSplit(new SQLAdapter(sql.toString()));
		
		
		goodsMapper.updateCommentCount(goodscomment.getGoodsId());
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
		//未分表写法
		//goodscommentMapper.updateComment(goodscomment);
		
		//分表写法
		StringBuffer sql = new StringBuffer(64);
		sql.append(" UPDATE goodscomment").append(goodscomment.getId() % 10);
		sql.append(" SET score = ").append(goodscomment.getScore()).append(", content = '").append(goodscomment.getContent());
		sql.append("' where id = ").append(goodscomment.getId());
		goodscommentMapper.updateCommentSplit(new SQLAdapter(sql.toString()));
	}
	
	@Override
	/**
	 * 添加商品标签
	 * @param tag
	 */
	public void addGoodsTag(GoodsTag tag)
	{
		//未分表写法
		//goodsTagMapper.insertTag(tag);
		
		//分表写法
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO goodstag").append(tag.getGoodsId() % 10);
		sql.append("(name, count, userId, goodsId) ");
		sql.append(" VALUES('").append(tag.getName()).append("',").append(tag.getCount()).
		append(",").append(tag.getUserId()).append(",").append(tag.getGoodsId()).append(") ");
		goodsTagMapper.insertTagSplit(new SQLAdapter(sql.toString()));
	}
	
	@Override
	/**
	 * 更新标签点击数
	 * @param id
	 */
	public void updateGoodsTag(Integer id)
	{
		//未分表写法
		//goodsTagMapper.updateTagCount(id);
		
		//分表写法
		Random rand = new Random();
		String sql = " update goodstag" + rand.nextInt(10) + " set count = count + 1 where id = " + id;
		goodsTagMapper.updateTagCountSplit(new SQLAdapter(sql));
	}
	
	@Transactional
	@Override
	/**
	 * 收藏商品
	 * @param goodsCollect
	 */
	public void collectGoods(GoodsCollect goodsCollect, User user)
	{
		//未分表写法
//		goodsCollectMapper.insertGoodsCollect(goodsCollect);
//		userMapper.updateCollectCount(goodsCollect.getUserId());
//		user.setCollectCount(user.getCollectCount() + 1);
		
		//分表写法
		StringBuffer sql = new StringBuffer(128);
		sql.append("INSERT INTO goodscollect").append((goodsCollect.getUserId() % 10));
		sql.append("(userId, goodsId, goodsname, goodsimg, goodsMarketprice, goodsSellprice, createtime) ");
		sql.append(" VALUES(").append(goodsCollect.getUserId()).append(",").append(goodsCollect.getGoodsId()).append(",'").
		append(goodsCollect.getGoodsname());
		sql.append("','").append(goodsCollect.getGoodsimg()).append("',").append(goodsCollect.getGoodsMarketprice()).append(",");
		sql.append(goodsCollect.getGoodsSellprice()).append(",SYSDATE()) ");
		goodsCollectMapper.insertGoodsCollectSplit(new SQLAdapter(sql.toString()));
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
		//未分表写法
		//scorelogMapper.insertScorelog(scorelog);
		
		//分表写法
		StringBuffer sql = new StringBuffer(128);
		sql.append(" INSERT INTO scorelog").append(user.getId() % 10).append("(info, score, userId, createtime)");
		sql.append(" values('").append(scorelog.getInfo()).append("',").append(scorelog.getScore()).append(",");
		sql.append(scorelog.getUserId()).append(", SYSDATE())");
		System.out.println(sql.toString());
		scorelogMapper.insertScorelogSplit(new SQLAdapter(sql.toString()));
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
		//未分表写法
		//scorelogs = scorelogMapper.queryScorelog(userId);
		
		//分表写法
		String sql = " SELECT * FROM `scorelog" + (userId % 10) + "` where userId = " + userId + " LIMIT 10 ";
		scorelogs = scorelogMapper.queryScorelogSplit(new SQLAdapter(sql));
		return scorelogs;
	}

	@Override
	public List<GoodsCollect> getGoodsCollect(Integer userId)
	{
		List<GoodsCollect> goodsCollects = null;
		
		//未分表写法
		//goodsCollects = goodsCollectMapper.queryGoodsCollect(userId);
		
		//分表写法
		String sql = "SELECT * FROM `goodscollect" + (userId % 10) + "` where userId = " + userId + " LIMIT 10";
		goodsCollects = goodsCollectMapper.queryGoodsCollectSplit(new SQLAdapter(sql));
		
		return goodsCollects;
	}
}
