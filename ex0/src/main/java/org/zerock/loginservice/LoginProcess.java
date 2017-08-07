package org.zerock.LoginService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class LoginProcess {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	
	String user_ID;
	String user_Password;
	String user_Code;
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
		    ResultSet rs = stmt.executeQuery(sql);
		    while (rs.next()){
		       String savedpass  = rs.getString("user_Password");
		       String savedcode = rs.getString("user_Code");
		       if( user_Password.equals(savedpass)){
		    	   user_Code = savedcode;
		      	 return 1;
		       }else{
		       	 return 2;
		       }
		      }
		      rs.close();
		      stmt.close();
		      con.close();
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
	public String getID(){
		return user_ID;		
	}
}
