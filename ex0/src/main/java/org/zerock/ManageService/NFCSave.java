package org.zerock.ManageService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class NFCSave {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root";
	private static final String PW = "000000";
	
	String user_Code;
	String user_ID;
	String nfc_Id;
	String nfc_Name;  //자산이름
	String nfc_Text;
	String result_String; //체크 박스
	int result =0;
	
	@Test
	public int NFCSave(String user_Code, String user_ID, String nfc_Id, String nfc_Name, String nfc_Text, String result_String) throws Exception{
		this.user_Code = user_Code;
		this.user_ID = user_ID;
		this.nfc_Id = nfc_Id;
		this.nfc_Name = nfc_Name;
		this.nfc_Text = nfc_Text;
		this.result_String = result_String;
		return SaveProcess();
	}
	private int SaveProcess() throws Exception{
		
		Statement stmt = null;
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		      System.out.println("Creating statement...");
		      stmt = con.createStatement();
		      String sql;
			    
		      sql = "insert into nfc_table2 (user_Code, asset_Code, nfc_Name, nfc_Text, result_String)";
		      sql += "values('"+user_Code+"','"+nfc_Id+"','"+user_ID+"','"+nfc_Name+"','"+nfc_Text+"', '"+result_String+"')";
		      System.out.println(sql);
		      stmt.executeUpdate(sql);
		      
		      stmt.close();
		      con.close();
		      System.out.println("Save the NFC data!");
		}
		
		return result;
	}
}
