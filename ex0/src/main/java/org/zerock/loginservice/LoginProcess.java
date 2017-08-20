package org.zerock.LoginService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.zerock.LogService.LogManage;

public class LoginProcess {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	
	private String user_ID;
	private String user_Password;
	private String user_Code;
	private int result = 0;
	public int loginprocess(String user_ID, String user_Password) throws Exception{
		this.user_ID = user_ID;
		this.user_Password = user_Password;
		return serverlogin();
	}
	
	@Test
	private int serverlogin() throws Exception{
		
		Statement stmt = null;
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql = "SELECT * FROM user_table1 where user_ID = '"+user_ID+"'";
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while (rs1.next()){
		       String savedpass  = rs1.getString("user_Password");
		       String savedcode = rs1.getString("user_Code");
		       if( user_Password.equals(savedpass)){
		    	   user_Code = savedcode;
		      	 result =  1;
		       }else{
		       	 result =  2;
		       }
		      }
		      
		    LogManage logService = new LogManage(user_Code, "저장", "로그인");
		    logService.LogSave();
		      
		    rs1.close();
		    stmt.close();
		      
		      con.close();
		      return result;
		   }catch(SQLException se){
			   System.out.println("error1");
		      se.printStackTrace();
		   }catch(Exception e){
			   System.out.println("error2");
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		    	  System.out.println("error3");
		      }
		   }
		return 3;
	}
	public String getCode(){
		return user_Code;		
	}
}
