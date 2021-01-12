package com.sun.utils;

import com.sun.model.User;

import javax.servlet.http.HttpServletRequest;


public class Request {
	
	public static User getUserFromHttpServletRequest(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}

}
