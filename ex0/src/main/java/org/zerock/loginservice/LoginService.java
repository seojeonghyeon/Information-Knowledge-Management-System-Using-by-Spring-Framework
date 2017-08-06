package org.zerock.loginservice;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
@Service
public class LoginService {
	
	@Inject
	  private LoginDAO dao;
	
	public void webLogin(LoginVO login) throws Exception  {
		dao.login(login);
	}
	public int phoneLogin(String user_ID, String user_Password) throws Exception{
		PhoneLogin login = new PhoneLogin();
		int result = login.loginprocess(user_ID, user_Password);
		return result;
	}
}
