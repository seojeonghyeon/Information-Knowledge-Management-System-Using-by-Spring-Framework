package org.zerock.LogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LogManage {
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	
	private ArrayList<Integer> number;
	private ArrayList<Integer> activity_Number;
	private ArrayList<String> activity_Contents;
	private ArrayList<String> activity_Time;
	
	
	String user_Code = "";
	String activity_Name = "";
	String Value = "";
	int result = 0;
	public LogManage(String user_Code, String activity_Name, String Value) throws Exception{
		this.user_Code = user_Code;
		this.activity_Name = activity_Name;
		this.Value = Value;
		if(activity_Name.equals("저장")){
			result = LogSave();
		}else if(activity_Name.equals("조회")){
			result = LogLoad();
		}
	}
	
	public int LogSave() throws Exception{
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    
		    String activity_Number = "";
		      //해당 활동의 활동 번호를 가져와서 로그 테이블에 로그를 생성.
		      String sql = "SELECT * FROM activity_contents where activity_Contents = '"+Value+"'";
		      System.out.println(sql);
		      ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()){
			    	activity_Number  = rs.getString("activity_Number");
			  }
		      sql = "insert into log_data (user_Code, activity_Number, log_Time)";
		      sql += "values('"+user_Code+"','"+activity_Number+"', now())";
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
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
		    
		return result;
	}
	
	public int LogLoad() throws Exception{
		number = new ArrayList<Integer>();
		activity_Number = new ArrayList<Integer>();
		activity_Contents = new ArrayList<String>();
		activity_Time = new ArrayList<String>();
		
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    
		    String sql = "SELECT * FROM log_data where user_Code = '"+user_Code+"'";
		    System.out.println(sql);
		    ResultSet rs1 = stmt.executeQuery(sql);
			while (rs1.next()){
			    	number.add(rs1.getInt("log_Number"));
			    	activity_Number.add(rs1.getInt("activity_Number"));
			    	activity_Time.add(rs1.getString("log_Time"));
			}
			for(int i=0; i<activity_Number.size(); i++){
				sql = "SELECT * FROM activity_contents where activity_Number = '"+activity_Number.get(i)+"'";
			    System.out.println(sql);
			    ResultSet rs2 = stmt.executeQuery(sql);
				while (rs2.next()){
					activity_Contents.add(rs2.getString("activity_Contents"));
				}
			}
		    
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
		
		return result;
	}
	
	public ArrayList<Integer> getnumbers(){
		return number;
	}
	public ArrayList<String> getactivity_Contents(){
		return activity_Contents;
	}
	public ArrayList<String> activity_Time(){
		return activity_Time;
	}
}
