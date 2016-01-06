package com.myshop.util;

/**
 * 项目配置工具类
 * @author Administrator
 *
 */
public class ConfigUtil
{
	public static final String TABLE = "table";									//普通表
	public static final String SPLIT_TABLE = "splitTable";						//拆分表
	
	/*
	 * 表的模式:
	 * 1.普通的表table。
	 * 2.拆分的表splitTable
	 */
	public static String TABLE_MODEL = TABLE;
	
	/*
	 * 拆分表的数量
	 */
	public static final int SPLIT_NUM = 10;
	
	/*
	 * 是否使用缓存
	 */
	public static final boolean USE_CACHE = true;
	
	/*
	 * 缓存页数
	 */
	public static final int CACHE_PAGESIZE = 50;
	
	/*
	 * 批量插入数据库的数据量
	 */
	public static final int BATCH_INSERT_NUM = 1000;
}
