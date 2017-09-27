package org.zerock.loginservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class PhoneLogin {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "jD5nmnxjvekn";
	
	String user_ID;
	String user_Password;
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
		    String sql = "SELECT * FROM user_data where userDivision = '"+user_ID+"'";
		    ResultSet rs = stmt.executeQuery(sql);
		    while (rs.next()){
		       String savedpass  = rs.getString("pass");
		       if( user_Password.equals(savedpass)){
		      	 return 1;
		       }else{
		       	 return 2;
		       }
		      }
		      rs.close();
		      stmt.close();
		      con.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		   }
		return 3;
	}
}

