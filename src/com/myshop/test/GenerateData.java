package com.myshop.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateData
{
	private static final File basedir = new File("C:\\Users\\Administrator\\Desktop");
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		generateData(10000000);
	}
	
	/**
	 * 生成数据
	 * @param num 生成数量
	 */
	public static void generateData(int num)
	{
		//generateUserData(num);
		//generateScoreLog(num);
		//generateOrder(num);
		//generateOrderGoods(num);
		//generateHotsearch(num);
		//generateGoodstag(num);
		//generateGoodsComment(num);
		//generateGoodscollect(num);
		//generateGoods(num);
		//generateGoodsDesc(num);
		//generateBrand(100);
	}
	
	/**
	 * 生成用户数据
	 * @param num 生成数量
	 */
	public static void generateUserData(int num)
	{
		File file = new File(basedir, "user.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String username = "user" + (i + 1);
				String password = "86332682";
				String email = "email";
				String addr = "addr";
				String mobile = "mobile";
				pw.println(username + "," + password + "," + email + "," + addr + "," + mobile);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成积分日志数据
	 * @param num 生成数量
	 */
	public static void generateScoreLog(int num)
	{
		File file = new File(basedir, "scorelog.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String info = "info" + (i + 1);
				Integer score = 0;
				Integer userId = i + 1;
				pw.println(info + "," + score + "," + userId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成订单数据
	 * @param num 生成数量
	 */
	public static void generateOrder(int num)
	{
		File file = new File(basedir, "order.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "orderName" + (i + 1);
				String addr = "orderAddr" + (i + 1);
				String mobile = "13401974865";
				String payWay = "支付宝";
				Integer userId = (i + 1);
				pw.println(name + "," + addr + "," + mobile + "," + payWay + "," + userId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成订单商品数据
	 * @param num 生成数量
	 */
	public static void generateOrderGoods(int num)
	{
		File file = new File(basedir, "ordergoods.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "Java 编程词典";
				String marketPrice = "150";
				String sellPrice = "120";
				String number = "1";
				Integer orderId = i + 1;
				Integer goodsId = i + 1;
				pw.println(name + "," + marketPrice + "," + sellPrice + "," + number + "," + orderId + "," + goodsId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成热门搜索数据
	 * @param num 生成数量
	 */
	public static void generateHotsearch(int num)
	{
		File file = new File(basedir, "hotsearch.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "hot" + (i + 1);
				String count = "1";
				pw.println(name + "," + count);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成商品标签
	 * @param num 生成数量
	 */
	public static void generateGoodstag(int num)
	{
		File file = new File(basedir, "goodstag.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "标签测试" + (i + 1);
				String count = "1";
				Integer userId = i + 1;
				Integer goodsId = i + 1;
				pw.println(name + "," + count + "," + userId + "," + goodsId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成商品评论
	 * @param num 生成数量
	 */
	public static void generateGoodsComment(int num)
	{
		File file = new File(basedir, "goodscomment.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				Integer userId = i + 1;
				Integer goodsId = i + 1;
				String username = "user" + (i + 1);
				String userlevel = "普通会员";
				String score = "5";
				String content = "很好";
				pw.println(userId + "," + goodsId + "," + username + "," + userlevel + "," + score + "," + content);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成商品收藏
	 * @param num 生成数量
	 */
	public static void generateGoodscollect(int num)
	{
		File file = new File(basedir, "goodscollect.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				Integer userId = i + 1;
				Integer goodsId = i + 1;
				String goodsname = "Java 编程词典";
				String goodsimg = "201004201340260341.jpg";
				String goodsMarketprice = "150";
				String goodsSellprice = "120";
				pw.println(userId + "," + goodsId + "," + goodsname + "," + goodsimg + "," + goodsMarketprice + "," + goodsSellprice);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	/**
	 * 生成商品数据
	 * @param num 生成数量
	 */
	public static void generateGoods(int num)
	{
		File file = new File(basedir, "goods.dat");
		PrintWriter pw = null;
		Random rand = new Random();
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "商品" + (i + 1);
				String img = "201004201340260341.jpg";
				String desc = "descssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
				String marketprice = "150";
				String sellprice = "120";
				Integer categoryId = rand.nextInt(349);
				categoryId = categoryId == 0 ? 1 : categoryId;
				Integer brandId = rand.nextInt(101);
				brandId = brandId == 0 ? 1 : brandId;
				pw.println(name + "," + img + "," + desc + "," + marketprice + "," + sellprice + "," + categoryId + "," + brandId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	public static void generateGoodsDesc(int num)
	{
		File file = new File(basedir, "goodsdesc.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "速度-尺寸-操作温度|规格-上架时间-材质-型号-系列";
				String val = "0-0-0|0-0-0-0-0";
				Integer goodsId = i + 1;
				pw.println(name + "," + val + "," + goodsId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
	
	public static void generateBrand(int num)
	{
		File file = new File(basedir, "brand.dat");
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(file);
			for(int i = 0;i < num;i++)
			{
				String name = "品牌" + (i + 1);
				String logo = "logo";
				String url = "url";
				String isRecommend = "1";
				pw.println(name + "," + logo + "," + url + "," + isRecommend);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pw != null)
			{
				pw.close();
			}
		}
	}
}
