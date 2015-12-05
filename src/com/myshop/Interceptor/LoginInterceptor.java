package com.myshop.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myshop.model.User;

public class LoginInterceptor implements HandlerInterceptor
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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception
	{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null)
		{
			if(request.getRequestURI().endsWith("goCartPage.action") || request.getRequestURI().endsWith("goMyOrderPage.action"))
			{
				LOG.info(request.getRequestURI());
				response.sendRedirect("/myshop/home/goLoginPage.action");
				return false;
			}
		}
		
		return true;
	}
}
