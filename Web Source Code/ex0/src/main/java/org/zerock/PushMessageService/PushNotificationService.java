package org.zerock.PushMessageService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.*;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.zerock.ManageService.ModifyAssets;

import com.google.android.gcm.server.*;
import java.net.URL;

public class PushNotificationService {
	
	
	String user_ID;
	String user_Code;
	String user_Token;
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/assets_manage";
	private static final String USER = "root"; 
	private static final String PW = "000000";
	
	public final static String AUTH_KEY_FCM = "AAAA0KufHoc:APA91bEVBOOwjEKUwyCuN-yVvVNVodTaAL9kl_XdRl9hP8YaAynF6VRDjqq5HRL9ZCCrUIM7ZCYFogR0O6wBjOj_to0jsIVPsCZPWurY_tJ9qKeGNmPtIpuqk3wDeHRD4tHbLhHhz9Gu";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	
	static String msg = "자산의 유통기한이 얼마남지 않았어요! 지금 확인하세요~";
	
	ArrayList<String> users_Code = new ArrayList<String>();
	ArrayList<String> PostUser = new ArrayList<String>();
	static ArrayList<String> PostAsset = new ArrayList<String>();
	
	//스케줄링! 매일 8시에 Check!
	@Scheduled(cron="0 0 22 * * ?") 
	public void scheduleRun() throws Exception{
	 bringUserCode();
	 for(int i=0; i<users_Code.size(); i++)
		 CheckDatabase(users_Code.get(i));

	}
	
	//유저의 코드를 하나씩 가져옴.
	private void bringUserCode() throws Exception{
		Statement stmt = null;
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    String sql = "SELECT * FROM user_table1";
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while (rs1.next()){
		       users_Code.add(rs1.getString("user_Code"));
		    }
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
	
	//데이터 베이스 확인
	private int CheckDatabase(String user_Code) throws Exception{
		ArrayList<String> assets_Code = new ArrayList<String>();
		ArrayList<String> check_Data = new ArrayList<String>();
		
		Statement stmt = null;
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		    System.out.println("Creating statement...");
		    stmt = con.createStatement();
		    
		    //유저에 해당된 자산 코드 가져오기.
		    String sql = "SELECT * FROM user_assets where user_Code = '"+user_Code+"'";
		    System.out.println(sql);
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while (rs1.next()){
		       assets_Code.add(rs1.getString("asset_Code"));
		       System.out.println(rs1.getString("asset_Code"));
		    }
		    System.out.println(assets_Code.size());
		    //자산 코드에 해당하는 클래스가 2인지 확인하고 확인된 대상은 따로 담기.
		    for(int j=0; j<assets_Code.size();j++){
		    	System.out.println(j);
			    sql = "SELECT * FROM qr_table where asset_Code = '"+assets_Code.get(j)+"'";
			    System.out.println(sql);
			    ResultSet rs2 = stmt.executeQuery(sql);
			    while (rs2.next()){
			    	if(rs2.getString("asset_Class").equals("2")){
			    		check_Data.add(assets_Code.get(j));
			    		System.out.println(assets_Code.get(j));
			    	}
			    }
			    rs2.close();
		    }
		    System.out.println(assets_Code.size());
		    //따로 담은 데이터는 유통기한 확인하기.
		    for(int j=0; j<check_Data.size(); j++){
		    	sql = "SELECT * FROM qr_table where asset_Code = '"+check_Data.get(j)+"'";
		    	System.out.println(sql);
		    	ResultSet rs3 = stmt.executeQuery(sql);
			    while (rs3.next()){
			    	String[] array = rs3.getString("asset_Info").split("/");
			    	long result = calTheTime(array[3]);
			    	System.out.println(array[3]);
			    	System.out.println(result);
			    	if( result < 0){ //데이터 삭제
			    		sql = "Delete from user_assets Where asset_Code = "+check_Data.get(j);
			    		System.out.println(sql);
			    		stmt.executeQuery(sql);
					    sql = "Delete from qr_table Where asset_Code = "+check_Data.get(j);
					    System.out.println(sql);
					    stmt.executeQuery(sql);
			    	}else if( 0 < result && result < 2 ){ //푸쉬 알림 보내기
			    		PostAsset.add(check_Data.get(j));
			    		System.out.println(j);
			    		PushProcess(user_Code);
			    	}else if( 2 < result ){  //통과!
			    		System.out.println("really here??");
			    		continue;
			    	}
			    }
			    rs3.close();
		    	
		    }
		    rs1.close();
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
		return 0;
	}
	private long calTheTime(String A) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get ( cal.YEAR );
    	int month = cal.get ( cal.MONTH ) + 1 ;
    	int date = cal.get ( cal.DATE ) ;
    	String currentDay = ""+year+"."+month+"."+date+"";
    	java.util.Date BeginDate = formatter.parse(currentDay);
        java.util.Date endDate = formatter.parse(A);
        long diff = endDate.getTime() - BeginDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffDays;
	}
	
	public void PushProcess(String user_Code) throws Exception{
	ArrayList<String> token = new ArrayList<String>();    //token값을 ArrayList에 저장
    Statement stmt = null;
	Class.forName(DRIVER);
	
	try(Connection con = DriverManager.getConnection(URL, USER, PW)){
		System.out.println(con);
	    System.out.println("Creating statement...");
	    stmt = con.createStatement();
		    String sql = "SELECT * FROM user_token where user_Code = '"+user_Code+"'";
		    System.out.println(sql);
		    ResultSet rs1 = stmt.executeQuery(sql);
	        //모든 등록ID를 리스트로 묶음
	        while(rs1.next()){
	        	System.out.println("here??");
	        	System.out.println(rs1.getString("user_Token"));
	            token.add(rs1.getString("user_Token"));
	        }
	        rs1.close();
        stmt.close();
        con.close();
        
       for(int i = 0; i < token.size(); i++){
    	   System.out.println(i);
    	   pushFCMNotification(token.get(i), i);
       }
        
    }catch (Exception e) {
        e.printStackTrace();
    }
    
	}
	public static void pushFCMNotification(String userDeviceIdKey, int k)
            throws Exception {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        JSONObject info = new JSONObject();

        info.put("body", PostAsset.get(k)+msg); // Notification body
        
        json.put("notification", info);
        json.put("to", userDeviceIdKey.trim()); // deviceID
        System.out.println(json);
        try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())){
//혹시나 한글 깨짐이 발생하면 
//try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

            wr.write(json.toString());
            wr.flush();
        }catch(Exception e){
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }
}

