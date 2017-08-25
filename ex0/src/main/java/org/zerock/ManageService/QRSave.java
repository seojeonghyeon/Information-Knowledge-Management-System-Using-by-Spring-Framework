package org.zerock.ManageService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.zerock.LogService.LogManage;

public class QRSave {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	
	private String user_Code;
	private String user_ID;
	
	String asset_class;
	String asset_number;
	String Maker;
	String asset_info;
	String qr_image;
	String assetCode="";
	int result =0;
	
	
	public QRSave(String user_Code, String asset_class,String asset_number, String Maker, String asset_info, String qr_image)throws Exception{
		this.user_Code = user_Code;
		this.asset_class = asset_class;
		this.asset_number = asset_number;
		this.Maker = Maker;
		this.asset_info = asset_info;
		this.qr_image = qr_image;
		saveprocess();
	}
	public int resultValue(){
		return result;
	}
	private void saveprocess() throws Exception{
		
		Statement stmt = null;
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		      
		      String assets_Bigname = "";
		      int Number = 0;
		      String Size = "100";
		      //해당 활동의 활동 번호를 가져와서 로그 테이블에 로그를 생성.
		      String sql = "SELECT * FROM assets_table where asset_Number = '"+asset_class+"'";
		      System.out.println(sql);
		      ResultSet rs1 = stmt.executeQuery(sql);
			  while (rs1.next()){
				  assets_Bigname  = rs1.getString("assets_Bigname");
			  }
			  System.out.println(assets_Bigname);
			  System.out.println(asset_class);
			  if(assets_Bigname == ""){
				  result =  5; //assets_Bigname is no register
			  }
			  if(asset_class.equals("1")){
				  sql = "SELECT * FROM book_table where book_Name = '"+asset_number+"'";
			      System.out.println(sql);
			      ResultSet rs2 = stmt.executeQuery(sql);
				  while (rs2.next()){
					  Number  = rs2.getInt("asset_Number");
				  }
				  System.out.println(Number);
				  if(Number == 0){
					  sql = "insert into book_table ( book_Name, book_Writer)";
				      sql += "values('"+asset_number+"','"+Maker+"')";
				      System.out.println(sql);
				      stmt.executeUpdate(sql);
				      
				      sql = "SELECT * FROM book_table where book_Name = '"+asset_number+"'";
				      System.out.println(sql);
				      ResultSet rs3 = stmt.executeQuery(sql);
					  while (rs3.next()){
						  Number  = rs3.getInt("asset_Number");
					  }
				      
				      LogManage logService = new LogManage(user_Code, "저장", "자산추가");
				      logService.LogSave();
				      rs3.close();
				  }
				  rs2.close();
			  }
			  if(asset_class.equals("2")){
				  sql = "SELECT * FROM food_table where food_Name = '"+asset_number+"'";
			      System.out.println(sql);
			      ResultSet rs2 = stmt.executeQuery(sql);
				  while (rs2.next()){
					  Number  = rs2.getInt("asset_Number");
				  }
				  if(Number == 0){
					  sql = "insert into food_table ( food_Name, food_Maker, food_Size )";
				      sql += "values('"+asset_number+"','"+Maker+"', '"+Size+"')";
				      System.out.println(sql);
				      stmt.executeUpdate(sql);
				      
				      sql = "SELECT * FROM food_table where food_Name = '"+asset_number+"'";
				      System.out.println(sql);
				      ResultSet rs3 = stmt.executeQuery(sql);
					  while (rs3.next()){
						  Number  = rs3.getInt("asset_Number");
					  }
				      
				      LogManage logService = new LogManage(user_Code, "저장", "자산추가");
				      logService.LogSave();
				      rs3.close();
				  }
				  rs2.close();
			  }
			  while(true){
				  //asset_Code 생성
				  int NumB = (int) (Math.random() * 9999) + 1;
				  assetCode = ""+NumB+"";
				  String temp = "";
				  sql = "SELECT * FROM qr_table where asset_Code = '"+assetCode+"'";
			      System.out.println(sql);
			      ResultSet rs4 = stmt.executeQuery(sql);
			      while (rs4.next()){
			    	  temp = rs4.getString("asset_Code");
				  }
			      if(temp==""){
			    	  break;
			      }
			      rs4.close();
			  }
			  sql = "insert into user_assets ( user_Code, manner_Number, asset_Code)";
		      sql += "values('"+user_Code+"','"+"1"+"', '"+assetCode+"')";
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
			  
			  sql = "insert into qr_table ( asset_Class, asset_Number, asset_Info, qr_Image, asset_Code )";
		      sql += "values('"+asset_class+"','"+Number+"', '"+asset_info+"', '"+qr_image+"', '"+assetCode+"')";
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
		      
		      LogManage logService = new LogManage(user_Code, "저장", "자산등록");
		      logService.LogSave();
			  
		      rs1.close();
		      stmt.close();
		      
		      con.close();
		      result = 1;
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
		result = 3;
	}
}
