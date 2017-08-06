package com.assets.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class testRDS {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://ec2-52-79-161-69.ap-northeast-2.compute.amazonaws.com:3306/assets_manage";
	private static final String USER = "root";
	private static final String PW = "000000";
	
	
	String user_ID;
	String id;
	String assetsName;
	String assetsContents;
	String idValue;
	

	
	@Test
	public int save() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      String sql;
		      sql = "insert into log_data (log_Number, user_Code, activity_Number, log_Time)";
		      sql += "values('', '"+"ABC001"+"','"+"1"+"',"+"'now')";
		      stmt.executeUpdate(sql);
		      stmt.close();
		      con.close();
		      System.out.println("Save the New Member!");
			  return 1;
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      System.out.println("error");
			  return 2;
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      System.out.println("error");
			  return 2;
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		   }
	}
}
