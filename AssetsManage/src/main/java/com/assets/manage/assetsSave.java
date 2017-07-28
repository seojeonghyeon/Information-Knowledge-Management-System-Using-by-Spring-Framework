package com.assets.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class assetsSave {
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root";
	private static final String PW = "000000";
	
	
	String user_ID;
	String id;
	String assetsName;
	String assetsContents;
	String idValue;
	
	public int process(String user_ID, String id, String assetsName, String assetsContents, String idValue) throws Exception{
		this.user_ID = user_ID;
		this.id = id;
		this.assetsName = assetsName;
		this.assetsContents = assetsContents;
		this.idValue = idValue;
		return save();
	}
	
	@Test
	private int save() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      String sql;
		      sql = "insert into assets_info (id, userDivision, assetsDivision, assetsName, assetsContents, idValue)";
		      sql += "values('', '"+user_ID+"','"+id+"','"+assetsName+"', '"+assetsContents+"', '"+idValue+"')";
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
