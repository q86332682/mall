package com.mall.dao;

import java.util.List;

import com.mall.generator.dao.AdvertMapper;
import com.mall.generator.model.Advert;

/**
 * 自定义广告表操作Mapper
 * @author Administrator
 *
 */
public interface MyAdvertMapper
{
	/**
	 * 查询首页广告(主广告, 主广告下小广告, 热卖右侧广告查询)
	 * @return
	 */
	public List<Advert> queryHomeAdverts();
}
