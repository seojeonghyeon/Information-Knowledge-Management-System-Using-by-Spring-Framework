package org.zerock.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;


public class BringAssets {
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	String user_ID;
	String user_Code;
	ArrayList<Integer> assets_Manner;
	ArrayList<String> assets_Codes;
	ArrayList<String> assets_Class;
	ArrayList<String> assets_Number;
	ArrayList<String> assets_Info;
	ArrayList<String> assets_Image;
	
	JSONArray jArr;
	JSONObject jObj;
	
	
	public BringAssets(String user_ID) throws Exception{
		this.user_ID = user_ID;
		Statement stmt = null;
		Class.forName(DRIVER);
			
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql1 = "SELECT * FROM user_table1 where user_ID = '"+user_ID+"'";
		    ResultSet rs1 = stmt.executeQuery(sql1);
		    while (rs1.next()){
		       user_Code  = rs1.getString("user_Code");
		    }
		    rs1.close();
		    
		    String sql2 = "SELECT * FROM user_assets where user_Code = '"+user_Code+"'";
		    ResultSet rs2 = stmt.executeQuery(sql2);
		    while (rs2.next()){
		    	//1.QR, 2.QR+NFC
		    	assets_Manner.add(rs2.getInt("manner_Number"));
		    	
		        assets_Codes.add(rs2.getString("asset_Code"));
		    }
		    rs2.close();
		    
		    for(int i = 0; i<assets_Manner.size(); i++){
			    if(assets_Manner.get(i) == 1){
				    String sql3 = "SELECT * FROM qr_table where user_Code = '"+user_Code+"'";
				    ResultSet rs3 = stmt.executeQuery(sql3);
				    while (rs3.next()){
				    	
				    	if(rs3.getInt("asset_Class")==1){
				    		assets_Class.add("도서");
				    		if(rs3.getInt("asset_Number")==1)
					    		assets_Number.add("스프링프로젝트");
					    	else
					    		assets_Number.add("토비의 스프링");
				    	}
				    	else{
				    		assets_Class.add("식료품");
				    		if(rs3.getInt("asset_Number")==1)
					    		assets_Number.add("우유");
					    	else
					    		assets_Number.add("치즈 케이크");
				    	}
				    	assets_Info.add(rs3.getString("asset_Info"));
				    	//Bring Image
				        assets_Codes.add(rs2.getString("asset_Code"));
				    }
				    rs3.close();
			      }
		      }
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
		
		
	}
	public JSONArray jsonCreate(){
		
		jArr= new JSONArray();
		try {
			
			for(int i=0; i<assets_Manner.size(); i++){
				jObj = new JSONObject();
				jObj.put("자산코드", assets_Codes.get(i));
				jObj.put("자산정보", ""+assets_Class.get(i)+assets_Number.get(i)+"");
				jObj.put("자산내용", ""+assets_Info.get(i));
				jArr.put(jObj);
				jObj = null;
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return jArr;
	}
}
