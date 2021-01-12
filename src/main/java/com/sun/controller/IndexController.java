package com.sun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	@RequestMapping(value = "index.domain",method = { RequestMethod.GET})
	public String index() {
		return "/WEB-INF/pages/login.jsp";
	}
	
	@RequestMapping(value = "signUp.domain",method = { RequestMethod.GET})
	public String signUp() {
		return "register";
	}
	
	@RequestMapping(value = "logout.domain")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("logout success");
//		return "index";
		return "redirect:index.domain";
	}
	
	@RequestMapping(value = "headerFrameLoad.domain",method = { RequestMethod.GET })
	public ModelAndView headerFrameLoad(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/WEB-INF/pages/headerFrame.jsp");
		
		return modelAndView;
	}

}
