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
import org.zerock.ManageService.QRSave;
import org.zerock.PushMessageService.TokenRegist;
import org.zerock.RegisterService.RegisterProcess;
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

@RequestMapping(value = "regist", method = RequestMethod.GET)
public String registerUser(Locale locale, Model model, @RequestParam("user_Code") String user_Code, HttpSession session, HttpServletRequest request) {
	session = request.getSession();
	session.setAttribute("user_Code", user_Code);
	return "regist";
}

@RequestMapping(value = "board/deleteProcess", method = RequestMethod.POST)
public String deleteProcess(Locale locale, Model model, @RequestParam("asset_Code") String asset_Code, HttpSession session, HttpServletRequest request) throws Exception {
	String user_Code = (String) session.getAttribute("user_Code");
	ModifyAssets clientuser = new ModifyAssets(user_Code);
	clientuser.deleteAssets(asset_Code);
	return "board/managePage";
}


@RequestMapping(value="board/listAll", method = RequestMethod.POST)
public String board(Locale locale, Model model) {
	return "board/listAll";
}
@RequestMapping(value="board/listAllapp", method = RequestMethod.GET)
public String appboard(Locale locale, Model model,@RequestParam("user_ID") String user_ID, HttpSession session, HttpServletRequest request) throws Exception {
	ModifyAssets clientuser = new ModifyAssets(user_ID, 1);
	String user_Code = clientuser.getuser_Code();
	session = request.getSession();
	session.setAttribute("user_ID", user_ID);
	session.setAttribute("user_Code", user_Code);
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
	
@RequestMapping(value="logData")
public String log(Locale locale, Model model) {
	return "log/log";
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
	
	//Token 등록
	@RequestMapping(value ="/RegistToken", method = RequestMethod.GET)
	@ResponseBody 
	public String RegistToken(HttpServletRequest request) throws Exception { 
		String user_Token = request.getParameter("Token");
		String user_ID;
		String[] array = user_Token.split("/");
		user_Token = array[0];
		user_ID = array[1];
		TokenRegist clientuser = new TokenRegist(user_ID, user_Token);
		int returnValue = clientuser.getResult();
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
	
	
	//회원가입
	@RequestMapping(value = "retu", method = RequestMethod.POST)
	public String RegisterService(Locale locale, Model model, @RequestParam("user_Code") String user_Code, @RequestParam("user_Address") String user_Address, @RequestParam("user_Email") String user_Email,@RequestParam("user_Name") String user_Name, @RequestParam("user_ID") String user_ID,@RequestParam("user_rePassword") String user_rePassword, @RequestParam("user_Password") String user_Password, HttpSession session, HttpServletRequest request) throws Exception {
		
		if(user_ID != "" && user_Password != ""){
			System.out.println("ID : " + user_ID );
			System.out.println("Password : " + user_Password );
			System.out.println("Code : " + user_Code );
			System.out.println("Address : " + user_Address );
			System.out.println("Email : " + user_Email );
			RegisterProcess clientuser = new RegisterProcess(user_ID, user_Password, user_Name, user_Email, user_Address, user_Code);
			int resultNumber = clientuser.getResult();
			if(resultNumber == 1){
				return "home";
			}else{
				return "home";
			}
		}else{
			return "home";
		}
	}

	//QRcode Save
	@RequestMapping(value = "qrcode/qr_saveService", method = RequestMethod.POST)
	public String qrsave(Locale locale, Model model, @RequestParam("asset_class") String asset_class, @RequestParam("asset_number") String asset_number, @RequestParam("Maker") String Maker, @RequestParam("asset_info") String asset_info, @RequestParam("qr_image") String qr_image, HttpSession session, HttpServletRequest request) throws Exception {
		if(asset_class != "" && asset_number != ""){
			System.out.println("ID : " + asset_class );
			System.out.println("Password : " + asset_number );
			session = request.getSession();
			String user_Code = (String) session.getAttribute("user_Code");
			System.out.println(user_Code);
			QRSave qr_asset = new QRSave(user_Code, asset_class, asset_number, Maker, asset_info, qr_image);
			
			int returnValue = qr_asset.resultValue();
			if(returnValue == 1){
				System.out.println("Succesful save");
	        	return "board/managePage";
	        }else{
	        	return "main/main";
	       }
		}else{
			return "main/main";
		}
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
	@RequestMapping(value="/jsonService",produces="application/json;charset=utf-8", method = RequestMethod.GET) 
	public String jsondata(@RequestParam("user_ID") String user_ID, Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
		session.setAttribute("user_ID", user_ID);
		
	    return "JSONdata";
	}
	
	
}
