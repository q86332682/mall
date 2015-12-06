package com.myshop.Interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理点击数重复更新拦截器
 * @author Administrator
 *
 */
public class ClickCountInterceptor implements HandlerInterceptor
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception
	{
		HttpSession session = arg0.getSession();
		String clickId = (String) session.getAttribute("clickId");
		
		if(arg0.getRequestURI().indexOf("goGoodsDetailPage.action") != -1)
		{
			String id = arg0.getParameter("id");
			if(id != null && !id.equals(clickId))
			{
				arg0.setAttribute("submit", "true");
				session.setAttribute("clickId", id);
			}
		}
		else if(!"XMLHttpRequest".equals(arg0.getHeader("X-Requested-With")))
		{
			session.removeAttribute("clickId");
		}
		
		return true;
	}
}
