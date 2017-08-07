package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.LoginService.LoginProcess;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.service.BoardService;


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerUser(Locale locale, Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	@ResponseBody 
	public String main(@RequestParam("user_ID") String user_ID, @RequestParam("user_Password") String user_Password, HttpServletRequest request) throws Exception {
		
		System.out.println("ID : " + user_ID );
		System.out.println("Password : " + user_Password );
		LoginProcess clientuser = new LoginProcess();
		
		int returnValue = clientuser.loginprocess(user_ID, user_Password);
		
		if(returnValue == 1){
			request.getSession().setAttribute("user_ID", user_ID);
        	return "main/main";
        }else{
        	return "<script type='text/javascript'>alert('Login Fail!');history.go(-1);</script>";
       }
	}

	@RequestMapping(value="board/listAll", method = RequestMethod.POST)
	public String board(Locale locale, Model model) {
		return "board/listAll";
	}
	
}
