package com.sun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sun.model.Major;
import com.sun.model.School;
import com.sun.model.User;
import com.sun.service.UserService;
import com.sun.utils.ReturnMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class InfoController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "info.domain",method = { RequestMethod.GET})
	public ModelAndView info() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("info");
		//获取学校信息
		List<School> schoolList=userService.getAllSchools();
		//这里简单的获取专业信息，这种方式并不好，这里只是简单的处理
		List<Major> majorList=userService.getAllMajors();
		
		modelAndView.addObject("schoolList", schoolList);
		modelAndView.addObject("majorList", majorList);
		return modelAndView;
	}
	
	@RequestMapping(value = "fillInfo.domain",method = { RequestMethod.POST})
	public ModelAndView fillInfo(HttpServletRequest request,User u,MultipartFile photo) {
		ModelAndView modelAndView=new ModelAndView();
		if(userService.isHasPrivilege(request) && userService.addInfo(request,u,photo)) {
			modelAndView.setViewName("redirect:home.domain");
		}else {
			modelAndView.setViewName("/WEB-INF/pages/login.jsp");
		}
		
		return modelAndView;
	}
	
	@PostMapping(value = "checkUserName.domain",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkUserName(HttpServletRequest request, String userName) {
		if(userService.isHasPrivilege(request)) {
			boolean isUsernameLegal=userService.checkUsernameLegal(request,userName);
			if(isUsernameLegal) {
				return ReturnMsg.msg(HttpServletResponse.SC_OK, "");
			}else {
				return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "请输入正确的姓名");
			}
		}else {
			return ReturnMsg.msg(HttpServletResponse.SC_BAD_REQUEST, "错误请求");
		}
		
		
	}

}
