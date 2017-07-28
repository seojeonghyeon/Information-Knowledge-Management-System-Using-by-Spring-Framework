package com.assets.manage;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping("/newMember") 
	@ResponseBody 
	public String androidTestWithRequestAndResponse(HttpServletRequest request) throws Exception { 
		String userID = request.getParameter("userID");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
        String homeAddress = request.getParameter("homeAddress");
        String homeNumber = request.getParameter("homeNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        newMember member = new newMember();
        String result = null;
        int returnValue = 0;
        
        //저장이 완료되면 1을 반환, 에러 시, 2반환.
        returnValue = member.savethedata(userID, pass, userName, userEmail, homeAddress, homeNumber, phoneNumber);
        //JSON 전송시 사용할 부분 Keep해둘것.
        //Map<String, String> result = new HashMap<String, String>();
        //result.put("data1", "메모에요");
        //result.put("data2", "두번째 메모입니다.");
        
        if(returnValue == 1){
        	return "100";
        }else{
        	return "200";
        }
	}
	@RequestMapping("/login")
	@ResponseBody 
	public String androidlogin(HttpServletRequest request) throws Exception { 
		String user_ID = request.getParameter("user_ID");
		String user_Password = request.getParameter("user_Password");
		
		login clientuser = new login();
		
		int returnValue = clientuser.loginprocess(user_ID, user_Password);
		
		if(returnValue == 1){
        	return "100";
        }else{
        	return "200";
       }
	}
	@RequestMapping("/assetssave")
	@ResponseBody 
	public String assetssave(HttpServletRequest request) throws Exception { 
		
		
		String user_ID = request.getParameter("user_ID");
		String idValue = request.getParameter("nfc_ID");
		String assetsName = request.getParameter("nfc_name");
		String assetsContents = request.getParameter("nfc_text");
		String id = request.getParameter("valid_code");
		
		
		assetsSave savedata = new assetsSave();
		
		int returnValue = savedata.process(user_ID, id, assetsName, assetsContents, idValue);
		
		if(returnValue == 1){
        	return "100";
        }else{
        	return "200";
       }
	}

}
