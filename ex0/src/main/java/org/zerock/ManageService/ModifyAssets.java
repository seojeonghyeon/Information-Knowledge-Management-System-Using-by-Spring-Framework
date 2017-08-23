package org.zerock.ManageService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zerock.LogService.LogManage;

public class ModifyAssets {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	
	private String user_Code;
	private String user_ID;
	
	JSONArray jArr;
	JSONObject jObj;
	
	private ArrayList<Integer> asset_ClassNumber;
	private ArrayList<String> asset_Class;
	private ArrayList<Integer> asset_NumNumber;
	private ArrayList<String> asset_Number;
	private ArrayList<String> asset_Code;
	private ArrayList<String> asset_Info;
	private ArrayList<String> qr_Image;
	
	private ArrayList<String> nfc_Id;
	private ArrayList<String> nfc_Name;
	private ArrayList<String> nfc_Text;
	private ArrayList<String> result_String;
	
	
	public ModifyAssets(String user_ID, int num) throws Exception{
		this.user_ID = user_ID;
		Statement stmt = null;
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql = "SELECT * FROM user_table1 where user_ID = '"+user_ID+"'";
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while (rs1.next()){
		       user_Code = rs1.getString("user_Code");
		    }
		    
		    rs1.close();
				stmt.close();
				con.close();
				setAssets();
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
		
	}
	
	public ModifyAssets(String user_Code) throws Exception{
		this.user_Code = user_Code;
		setAssets();
	}
	public String getuser_Code(){
		return user_Code;
	}
	public ArrayList<String> getAssetsClass(){
		return asset_Class;
	}
	public ArrayList<String> getAssetsNumber(){
		return asset_Number;
	}
	public ArrayList<String> getAssetsCode(){
		return asset_Code;
	}
	public ArrayList<String> getAssetsInfo(){
		return asset_Info;
	}
	public ArrayList<String> getAssetsImage(){
		return qr_Image;
	}
	

	public ArrayList<String> getnfc_Id(){
		return nfc_Id;
	}
	public ArrayList<String> getnfc_Name(){
		return nfc_Name;
	}
	public ArrayList<String> getnfc_Text(){
		return nfc_Text;
	}
	public ArrayList<String> getresult_String(){
		return result_String;
	}
	public void deleteAssets(String assetCode) throws Exception {
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql = "Delete from user_assets where asset_Code="+assetCode;
		    System.out.println(sql);
		    stmt.executeQuery(sql);
		    
		    sql = "Delete from qr_table where asset_Code="+assetCode;
		    System.out.println(sql);
		    stmt.executeQuery(sql);
		    LogManage logService = new LogManage(user_Code, "저장", "자산삭제");
		    logService.LogSave();
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
	}
	private void setAssets() throws Exception {
		asset_Code = new ArrayList<String>();
		asset_ClassNumber = new ArrayList<Integer>();
		asset_Class = new ArrayList<String>();
		asset_NumNumber = new ArrayList<Integer>();
		asset_Number = new ArrayList<String>();
		asset_Info = new ArrayList<String>();
		qr_Image = new ArrayList<String>();
		
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql = "SELECT * FROM user_assets where user_Code = '"+user_Code+"'";
		    System.out.println(sql);
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while (rs1.next()){
		       String savedcode  = rs1.getString("asset_Code");
		       System.out.println(savedcode);
		       asset_Code.add(savedcode);
		      }
		      rs1.close();
		      for(int i = 0; i < asset_Code.size(); i++){
		    	  sql = "SELECT * FROM qr_table where asset_Code = '"+asset_Code.get(i)+"'";
		    	  System.out.println(sql);
		    	  ResultSet rs2 = stmt.executeQuery(sql);
				    while (rs2.next()){
				    	System.out.println(rs2.getInt("asset_Class"));
				    	asset_ClassNumber.add(rs2.getInt("asset_Class"));
				    	asset_NumNumber.add(rs2.getInt("asset_Number"));
				       String savedinfo  = rs2.getString("asset_Info");
				       String savedimage  = rs2.getString("qr_Image");
				       asset_Info.add(savedinfo);
				       qr_Image.add(savedimage);
				    }
				    rs2.close();
		      }
		      
		      for(int i=0; i< asset_ClassNumber.size(); i++){
		    	  sql = "SELECT * FROM assets_table where asset_Number = '"+asset_ClassNumber.get(i)+"'";
		    	  System.out.println(sql);
		    	  ResultSet rs3 = stmt.executeQuery(sql);
				    while (rs3.next()){
				    	asset_Class.add(rs3.getString("assets_Bigname"));
				    }
				    rs3.close();
				    if(asset_ClassNumber.get(i)==1){
				    	sql = "SELECT * FROM book_table where asset_Number = '"+asset_NumNumber.get(i)+"'";
				    	System.out.println(sql);
				    	ResultSet rs4 = stmt.executeQuery(sql);
					    while (rs4.next()){
					    	asset_Number.add(rs4.getString("book_Name")+"/"+rs4.getString("book_Writer"));
					    }
					    rs4.close();
				    }
				    else{
				    	sql = "SELECT * FROM food_table where asset_Number = '"+asset_NumNumber.get(i)+"'";
					    ResultSet rs4 = stmt.executeQuery(sql);
					    while (rs4.next()){
					    	asset_Number.add(rs4.getString("food_Name")+"/"+rs4.getString("food_Maker")+"/"+rs4.getString("food_Size"));
					    }
					    rs4.close();
				    }
		      }
		      
		      LogManage logService = new LogManage(user_Code, "저장", "자산조회");
		      logService.LogSave();
		      
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
	}
	public void NFCload() throws Exception{
		nfc_Id = new ArrayList<String>();
		nfc_Name = new ArrayList<String>();
		nfc_Text = new ArrayList<String>();;
		result_String = new ArrayList<String>();
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql = "SELECT * FROM nfc_table2 where user_Code = '"+user_Code+"'";
		    System.out.println(sql);
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while (rs1.next()){
		       nfc_Id.add(rs1.getString("nfc_Id"));
		       nfc_Name.add(rs1.getString("nfc_Name"));
		       nfc_Text.add(rs1.getString("nfc_Text"));
		       result_String.add(rs1.getString("result_String"));
		    }
		    LogManage logService = new LogManage(user_Code, "저장", "NFC조회");
		    logService.LogSave();
		}
	}
	public JSONArray jsonCreate(){
		
		jArr= new JSONArray();
		try {
			for(int i=0; i<asset_Class.size(); i++){
				jObj = new JSONObject();
				jObj.put("ver", ""+asset_Info.get(i));
				jObj.put("name", ""+asset_Class.get(i)+"/"+asset_Number.get(i)+"");
				jObj.put("api", asset_Code.get(i));
				jArr.put(jObj);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return jArr;
	}
}
