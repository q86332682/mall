package com.myshop.test;

import redis.clients.jedis.Jedis;

import com.myshop.util.RedisUtil;

public class Test
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Jedis jedis = RedisUtil.getJedis();
		System.out.println(jedis.hget("GoodsDetail:5", "name"));
		RedisUtil.returnResource(jedis);
	}
}
