package org.zerock.RegisterService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class RegisterProcess {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root";
	private static final String PW = "000000";
	
	String user_ID;
	String user_Password;
	String user_Name;
	String user_Email;
	String user_Address;
	String user_Code;
	int result;
	
	public RegisterProcess(String user_ID, String user_Password, String user_Name, String user_Email, String user_Address, String user_Code) throws Exception {
		this.user_ID = user_ID;
		this.user_Password = user_Password;
		this.user_Name = user_Name;
		this.user_Email = user_Email;
		this.user_Address =user_Address;
		this.user_Code = user_Code;
		this.result = savethedata();
	}
	public int getResult(){
		return result;
	}
	@Test
	private int savethedata() throws Exception{
			Statement stmt = null;
			Class.forName(DRIVER);
			try(Connection con = DriverManager.getConnection(URL, USER, PW)){
				System.out.println(con);
			      System.out.println("Creating statement...");
			      stmt = con.createStatement();
			      String sql;
			      String activity_Number = "";
			      
			      //넘어온 회원가입 데이터를 사용자 테이블에 데이터를 저장.
			      sql = "insert into user_table1 (user_ID, user_Password, user_Code, state_Number)";
			      sql += "values('"+user_ID+"','"+user_Password+"','"+user_Code+"', '"+"1"+"')";
			      System.out.println(sql);
			      stmt.executeUpdate(sql);
			      
			      sql = "insert into user_table2 (user_Code, user_Name, user_Email, user_Address)";
			      sql += "values('"+user_Code+"','"+user_Name+"','"+user_Email+"', '"+user_Address+"')";
			      System.out.println(sql);
			      stmt.executeUpdate(sql);
			      
			      
			      //해당 활동의 활동 번호를 가져와서 로그 테이블에 로그를 생성.
			      sql = "SELECT * FROM activity_contents where activity_Contents = '"+"회원가입"+"'";
			      System.out.println(sql);
				  ResultSet rs = stmt.executeQuery(sql);
				  while (rs.next()){
				    	activity_Number  = rs.getString("activity_Number");
				  }
				  rs.close();
			      
			      sql = "insert into log_data (user_Code, activity_Number, log_Time)";
			      sql += "values('"+user_Code+"','"+activity_Number+"', now())";
			      System.out.println(sql);
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
