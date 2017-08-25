package org.zerock.PushMessageService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.zerock.ManageService.ModifyAssets;

public class TokenRegist {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root";
	private static final String PW = "000000";
	private static final String GOOGLE_API_KEY = "AAAAoJ8vlMs:APA91bHu4H6BeRmZC2jiG-dAg_vjOxhjrtak-nhhSrs74OLXFlJj_lNjRwynitmszhiNTLeywRkwwOzH7V4d3TD8OP-fTBVEZjwY-frjHFh9GvQ9gHeH_yRAQ3nzBAMAtpmWxa-AmH_X"; 
	
	String user_Code;
	String user_ID;
	String user_Token;
	int result = 0;
	
	public TokenRegist(){
		
	}
	public TokenRegist(String user_ID, String user_Token) throws Exception{
		this.user_ID= user_ID;
		Modify_userCode();
		this.user_Token = user_Token;
		this.result = RegistData();
	}
	private void Modify_userCode() throws Exception{
		ModifyAssets clientuser = new ModifyAssets(user_ID, 1);
		this.user_Code = clientuser.getuser_Code();
	}
	public int getResult(){
		return result;
	}
	
	@Test
	private int RegistData() throws Exception{
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      String sql;
		      sql = "insert into user_token (user_Code, user_Token)";
		      sql += "values('"+user_Code+"','"+user_Token+"')";
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
		      stmt.close();
		      con.close();
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
