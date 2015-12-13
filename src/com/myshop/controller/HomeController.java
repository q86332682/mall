package com.myshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myshop.model.Brand;
import com.myshop.model.Category;
import com.myshop.model.Goods;
import com.myshop.model.Goodscomment;
import com.myshop.model.Hotsearch;
import com.myshop.model.Order;
import com.myshop.model.Ordergoods;
import com.myshop.model.PageModel;
import com.myshop.model.User;
import com.myshop.service.GoodsService;
import com.myshop.service.UserService;

@Controller
@RequestMapping("/home")
/**
 * 商城主页控制器
 * @author Administrator
 *
 */
public class HomeController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final String SEARCH_GOODS = "搜索商品";
	private final String NEW_GOODS = "新品上市";
	private final String SELL_HOT_GOODS = "热销商品";
	private final String RECOMMEND_GOODS = "推荐商品";
	private final String POP_GOODS = "人气商品";
	private final String CATEGORY_GOODS = "分类商品";
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/searchGoods")
	public String searchGoods(Model model, Goods goods, PageModel<Goods> pageModel)
	{
		LOG.info("搜索商品");
		pageModel.setPageQuery(goods);
		PageModel<Goods> resultPageModel = goodsService.searchGoodsList(pageModel);
		model.addAttribute("resultPageModel", resultPageModel);
		return "Test";
	}
	
	@RequestMapping("/loadHotSearchlist")
	@ResponseBody
	/**
	 * 载入热门搜索商品列表
	 * @return
	 */
	public List<Hotsearch> loadHotSearchlist()
	{
		List<Hotsearch> hotSearchlist = goodsService.getHotsearchList();
		return hotSearchlist;
	}
	
	@RequestMapping("/goHomePage")
	public String goHomePage(Model model)
	{
		LOG.info("去主页");
		
		//List<Hotsearch> hotSearchlist = goodsService.getHotsearchList();
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		List<Category> categorylist = goodsService.getCategorylist();
		List<Category> recommendCategory = goodsService.getRecommendCategory();
		List<Brand> recommendBrand = goodsService.getRecommendBrand();
		
		//model.addAttribute("hotSearchlist", hotSearchlist);
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("recommendCategory", recommendCategory);
		model.addAttribute("recommendBrand", recommendBrand);
		
		return "home";
	}
	
	@RequestMapping("/goRegisterPage")
	public String goRegisterPage()
	{
		return "register";
	}
	
	@RequestMapping("/goLoginPage")
	public String goLoginPage()
	{
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session)
	{
		session.invalidate();
		return goHomePage(model);
	}
	
	@RequestMapping("/goCartPage")
	public String goCartPage(HttpServletRequest req, Goods goods)
	{
		HttpSession session = req.getSession();
		List<Ordergoods> list = (List<Ordergoods>) session.getAttribute("catrlist");
		if(list == null)
			list = new ArrayList<Ordergoods>();
		if(goods.getId() != null)
		{
			boolean flag = true;
			
			for(Ordergoods g : list)
			{
				if(g.getGoodsId().equals(goods.getId()))
				{
					g.setNum(g.getNum() + 1);
					flag = false;
					break;
				}
			}
			
			if(flag)
			{
				goods = goodsService.getGoodsById(goods.getId(), req);
				Ordergoods ordergoods = new Ordergoods(goods);
				list.add(0, ordergoods);
			}
		}
		
		if(list.size() > 4)
			list.remove(list.size() - 1);
		
		float cartTotalprice = 0;
		if(list != null)
			for(Ordergoods g : list)
				cartTotalprice += g.getSellprice() * g.getNum();
		session.setAttribute("cartTotalprice", cartTotalprice);
		session.setAttribute("catrlist", list.size() > 0 && list.get(0) != null ? list : null);		
		return "cart_list";
	}
	
	@RequestMapping("/goMyOrderPage")
	public String goMyOrderPage(Model model, HttpSession session, Order order, PageModel<Order> pageModel)
	{
		if(order.getUserId() == null)
		{
			User user = (User) session.getAttribute("user");
			order.setUserId(user.getId());
		}
		
		pageModel.setPageQuery(order);
		PageModel<Order> resultPageModel = userService.getMyOrderList(pageModel);
		model.addAttribute("resultPageModel", resultPageModel);
		model.addAttribute("userlevelList", userService.getUserlevelList());
		model.addAttribute("scorelog", userService.getScorelog(order.getUserId()));
		model.addAttribute("goodsCollect", userService.getGoodsCollect(order.getUserId()));
		return "order_list";
	}
	
	@RequestMapping("/goGoodsListPage")
	public String goGoodsListPage(Model model, String menuName, Goods search, PageModel<Goods> pageModel)
	{
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		
		
		
		String menuImg = null;
		PageModel<Goods> resultPageModel = null;
		
		if(SEARCH_GOODS.equals(menuName))
		{
			menuImg = "04.gif";
			pageModel.setPageQuery(search);
			resultPageModel = goodsService.searchGoodsList(pageModel);
		}
		if(NEW_GOODS.equals(menuName))
		{
			menuImg = "01.gif";
			resultPageModel = goodsService.getNewGoods(pageModel);
		}
		else if(SELL_HOT_GOODS.equals(menuName))
		{
			menuImg = "03.gif";
			resultPageModel = goodsService.getGoodsBySellhot(pageModel);
		}
		else if(RECOMMEND_GOODS.equals(menuName))
		{
			menuImg = "06.gif";
			resultPageModel = goodsService.getGoodsByRecommend(pageModel);
		}
		else if(POP_GOODS.equals(menuName))
		{
			menuImg = "07.gif";
			resultPageModel = goodsService.getGoodsByPopRank(pageModel);
		}
		else if(CATEGORY_GOODS.equals(menuName))
		{
			menuImg = "02.gif";
			pageModel.setPageQuery(search);
			resultPageModel = goodsService.getGoodsByCategory(pageModel);
		}

		model.addAttribute("menuImg", menuImg);
		model.addAttribute("menuName", menuName);
		model.addAttribute("search", search);
		model.addAttribute("resultPageModel", resultPageModel.getList().size() > 0 && resultPageModel.getList().get(0) != null ? 
				resultPageModel : null);
		LOG.info("goNewGoodsPage");
		return "goods_list";
	}
	
	@RequestMapping("/goGoodsDetailPage")
	public String goGoodsDetailPage(Model model, HttpServletRequest req, Integer id, PageModel<Goodscomment> pageModel)
	{
		model.addAttribute("goods", goodsService.getGoodsById(id, req));
		Goodscomment pageQuery = new Goodscomment();
		pageQuery.setGoodsId(id);
		pageModel.setPageQuery(pageQuery);
		model.addAttribute("commentList", goodsService.getCommentList(pageModel));
		
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		
		return "product_select";
	}
	
	@RequestMapping("/preciseSearch")
	public String preciseSearch(Model model, Goods preciseSearch, PageModel<Goods> pageModel)
	{
		List<Goods> goodslist1 = goodsService.getGoodsByPopRank();
		List<Goods> goodslist2 = goodsService.getGoodsByRecommend();
		List<Goods> goodslist3 = goodsService.getGoodsBySellhot();
		model.addAttribute("goodslist1", goodslist1);
		model.addAttribute("goodslist2", goodslist2);
		model.addAttribute("goodslist3", goodslist3);
		
		pageModel.setPageQuery(preciseSearch);
		PageModel<Goods> resultPageModel = goodsService.preciseSearch(pageModel);
		model.addAttribute("menuImg", "04.gif");
		model.addAttribute("resultPageModel", resultPageModel.getList().size() > 0 && resultPageModel.getList().get(0) != null ? 
				resultPageModel : null);
		return "goods_list";
	}
	
	@RequestMapping("/testPageModel")
	public void testPageModel()
	{
		Order order = new Order();
		order.setUserId(1);
		PageModel<Order> pageModel = new PageModel<Order>();
		pageModel.setPageNow(1);
		pageModel.setPageSize(5);
		pageModel.setPageQuery(order);
		PageModel<Order> resultPageModel = userService.getMyOrderList(pageModel);
		
		LOG.info("testPageModel");
	}
}
