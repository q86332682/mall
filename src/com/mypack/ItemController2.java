package com.mypack;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController2
{
	//@Autowired
	//private StudentMapper studentMapper;
	
	@RequestMapping("/queryItem2")
	public ModelAndView queryItem2(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception
	{
//		List<String> list = new ArrayList<String>();
//		list.add("aaa");
//		list.add("bbb");
//		list.add("ccc");
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("itemList", list);
//		modelAndView.setViewName("Test");
		
		ModelAndView modelAndView = new ModelAndView();
		//Student stu = studentMapper.findStuBySno("sn1");
		//modelAndView.addObject("stu", stu);
		modelAndView.setViewName("Test");
		return modelAndView;
	}
	
	@RequestMapping("/testJson")
	public @ResponseBody Student testJson()
	{
		Student stu = new Student();
		stu.setSNO("11111");
		stu.setSNAME("name");
		
		return stu;
	}
}
