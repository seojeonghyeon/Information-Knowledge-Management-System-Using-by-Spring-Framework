package org.zerock.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.loginservice.LoginService;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Inject
	private LoginService service;
	
	@RequestMapping("/phone")
	@ResponseBody 
	public String androidlogin(HttpServletRequest request) throws Exception { 
		String user_ID = request.getParameter("user_ID");
		String user_Password = request.getParameter("user_Password");
		
		int returnValue = service.phoneLogin(user_ID, user_Password);
		
		if(returnValue == 1){
        	return "100";
        }else{
        	return "200";
       }
	}
}
