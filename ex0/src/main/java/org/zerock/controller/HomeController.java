package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import javax.servlet.http.HttpSession;

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
import org.zerock.ManageService.ModifyAssets;
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
	
@RequestMapping(value = "/", method = RequestMethod.GET)
public String home(Locale locale, Model model) {
	return "home";
}
@RequestMapping(value = "register", method = RequestMethod.GET)
public String registerUser(Locale locale, Model model) {
	return "register";
}


@RequestMapping(value="board/listAll", method = RequestMethod.POST)
public String board(Locale locale, Model model) {
	return "board/listAll";
}


@RequestMapping(value="userinfo/modifyuser", method = RequestMethod.POST)
public String modify(Locale locale, Model model) {
	return "userinfo/modifyuser";
}


@RequestMapping(value="qrcode/qrcode", method = RequestMethod.POST)
public String qrcode(Locale locale, Model model) {
	return "qrcode/qrcode";
}
	
	
	@RequestMapping("/login")
	@ResponseBody 
	public String androidlogin(HttpServletRequest request) throws Exception { 
		String user_ID = request.getParameter("user_ID");
		String user_Password = request.getParameter("user_Password");
		
		LoginProcess clientuser = new LoginProcess();
		
		int returnValue = clientuser.loginprocess(user_ID, user_Password);
		
		if(returnValue == 1){
        	return "100";
        }else{
        	return "200";
       }
	}
	
	//관리 페이지
	@RequestMapping(value = "board/managePage")
	public String managepage(Locale locale, Model model) {
		System.out.println("Manage Page is execute");
        return "board/managePage";
	}
	
	
	//로그인
		@RequestMapping(value = "main", method = RequestMethod.POST)
		public String loginweb(Locale locale, Model model, @RequestParam("user_ID") String user_ID, @RequestParam("user_Password") String user_Password, HttpSession session, HttpServletRequest request) throws Exception {
			if(user_ID != "" && user_Password != ""){
				System.out.println("ID : " + user_ID );
				System.out.println("Password : " + user_Password );
				LoginProcess clientuser = new LoginProcess();
				
				int returnValue = clientuser.loginprocess(user_ID, user_Password);
				String user_Code = clientuser.getCode();
				if(returnValue == 1){
					session = request.getSession();
					session.setAttribute("user_Code", user_Code);
					session.setAttribute("user_ID", user_ID);
					System.out.println(session.getAttribute("user_Code"));
		        	return "main/main";
		        }else{
		        	return "home";
		       }
			}else{
				return "home";
			}
		}
	
	@RequestMapping(value="/jsonPost",produces="application/json;charset=utf-8", method = RequestMethod.GET) 
	public String jsonTest(@RequestParam("user_ID") String user_ID, Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
		session.setAttribute("user_ID", user_ID);
		
	    return "assets_Data";
	}
	
	
	
}
