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
	
	//로그인
	@RequestMapping(value = "main/main", method = RequestMethod.POST)
	public String loginweb(Locale locale, Model model, @RequestParam("user_ID") String user_ID, @RequestParam("user_Password") String user_Password, HttpServletRequest request) throws Exception {
		
		System.out.println("ID : " + user_ID );
		System.out.println("Password : " + user_Password );
		LoginProcess clientuser = new LoginProcess();
		
		int returnValue = clientuser.loginprocess(user_ID, user_Password);
		String user_Code = clientuser.getCode();
		if(returnValue == 1){
			request.getSession().setAttribute("user_Code", user_Code);
        	return "main/main";
        }else{
        	return "<script type='text/javascript'>alert('Login Fail!');history.go(-1);</script>";
       }
	}
	
	//관리 페이지
	@RequestMapping(value = "/managePage", method = RequestMethod.POST)
	@ResponseBody 
	public String managepage(Locale locale, Model model) throws Exception {
		System.out.println("Manage Page is execute");
        return "managePage";
	}
	
	//POSTJSON
	@RequestMapping("/postAssets") 
	@ResponseBody 
	public JSONArray androidTestWithRequestAndResponse(HttpServletRequest request) throws Exception { 
		String user_ID = request.getParameter("userID");
        ModifyAssets clientuser = new ModifyAssets(user_ID, 1);
        JSONArray returnValue= clientuser.jsonCreate();
        return returnValue;
	}
	
	
	@RequestMapping("/jsontest") 
	@ResponseBody 
	public void jsonTest() throws Exception {
		String user_ID = "kim";
        ModifyAssets clientuser = new ModifyAssets(user_ID, 1);
        JSONArray returnValue= clientuser.jsonCreate();
	    System.out.println(returnValue);
	}
	
	@RequestMapping(value="board/listAll", method = RequestMethod.POST)
	public String board(Locale locale, Model model) {
		return "board/listAll";
	}
	
}
