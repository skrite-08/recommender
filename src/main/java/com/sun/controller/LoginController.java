package com.sun.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sun.model.User;
import com.sun.service.UserService;
import com.sun.utils.ReturnMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "login.domain")
	public ModelAndView login(HttpServletRequest request, User u) {
		ModelAndView modelAndView=new ModelAndView();
		User user=userService.findLogin(u);
		if(user==null) {
			modelAndView.setViewName("/WEB-INF/pages/login.jsp");
			return modelAndView;
		}else {
			ReturnMsg.msg(HttpServletResponse.SC_OK, JSONObject.toJSON(u).toString());
			request.getSession().setAttribute("user", u);
			int status=user.getStatus();
			if(status==1) {
				//modelAndView.setViewName("redirect:/WEB-INF/pages/home.jsp");
				modelAndView.setViewName("redirect:home.domain");
			}else {
				//modelAndView.setViewName("redirect:info.domain");
				modelAndView.setViewName("/WEB-INF/pages/info.jsp");
			}
		}
		return modelAndView;
	}
	
	
	

}
