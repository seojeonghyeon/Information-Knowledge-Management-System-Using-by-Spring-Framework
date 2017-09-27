package org.zerock.loginservice;

public class LoginVO {

	private String user_ID;
	private String user_Password;
	
	public void setUserID(String user_ID) {
		this.user_ID = user_ID;
	}
	
	public String getUserID() {
		return user_ID;
	}
	
	public void setUserPassword(String user_Password) {
		this.user_Password = user_Password;
	}
	
	public String getUserPassword() {
		return user_Password;
	}
}
